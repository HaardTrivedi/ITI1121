
/**
 * The class  <b>LinearRegression</b> implements gradient descent for multiple
 * variables
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class LinearRegression {

    /**
     * Number of features (usually "n" in literature)
     */
    private int nbreOfFeatures;

    /**
     * Number of samples (usually "m" in literature)
     */
    private int nbreOfSamples;

    /**
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
    private double[][] samplesMatrix;

    /**
     * the nbreOfSamples Matrix of samples target values
     */
    private double[] samplesValues;

    /**
     * the nbreOfFeatures Matrix theta of current hypothesis function
     */
    private double[] theta;

    /**
     * number of samples received so far
     */
    private int currentNbreOfSamples;

    /**
     * a place holder for theta during descent calculation
     */
    private double[] tempTheta;

    /**
     * counts how many iterations have been performed
     */
    private int iteration;

    /**
     * Object's constructor. The constructor initializes the instance variables.
     * The starting hypothesis is theta[i]=0.0 for all i
     *
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
     *
     */
    public LinearRegression(int n, int m) {
        nbreOfFeatures = n + 1;
        nbreOfSamples = m;
        currentNbreOfSamples = 0;
        iteration = 0;
        samplesMatrix = new double[nbreOfSamples][nbreOfFeatures];
        samplesValues = new double[nbreOfSamples];
        theta = new double[nbreOfFeatures];

    }

    LinearRegression(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Add a new sample to samplesMatrix and samplesValues
     *
     * @param x the vectors of samples
     * @param y the corresponding expected value
     *
     */
    public void addSample(double[] x, double y) {
        samplesMatrix[currentNbreOfSamples][0] = 1;

        for (int i = 1; i < nbreOfFeatures; i++) {
            samplesMatrix[currentNbreOfSamples][i] = x[i - 1];
        }
        samplesValues[currentNbreOfSamples] = y;
        currentNbreOfSamples++;
    }

    /**
     * Returns the current hypothesis for the value of the input
     *
     * @param x the input vector for which we want the current hypothesis
     *
     * @return h(x)
     */
    private double hypothesis(double[] x) {
        double hypothesis = 0;
        for (int i = 0; i < nbreOfFeatures; i++) {
            hypothesis += theta[i] * x[i];
        }
        return hypothesis;
    }

    /**
     * Returns a string representation of hypothesis function
     *
     * @return the string "theta0 x_0 + theta1 x_1 + .. theta_n x_n"
     */
    public String currentHypothesis() {
        String hypothesis = "";
        for (int i = 0; i < nbreOfFeatures; i++) {
            if (i == nbreOfFeatures - 1) {
                hypothesis += Double.toString(theta[i]) + "x_" + Integer.toString((int) i);
            } else {
                hypothesis += Double.toString(theta[i]) + "x_" + Integer.toString((int) i) + "+";
            }
        }
        return hypothesis;
    }

    /**
     * Returns the current cost
     *
     * @return the current value of the cost function
     */
    public double currentCost() {
        double cost = 0;
        for (int i = 0; i < nbreOfSamples; i++) {
            cost += Math.pow((hypothesis(samplesMatrix[i]) - samplesValues[i]), 2);
        }
        return cost / (double) nbreOfSamples;
    }

    /**
     * runs several iterations of the gradient descent algorithm
     *
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */
    public void gradientDescent(double alpha, int numberOfSteps) {
        if (iteration < 10000) {
            for (int h = 0; h < numberOfSteps; h++) {
                double[] tempTheta = new double[nbreOfFeatures];
                for (int i = 0; i < nbreOfSamples; i++) {
                    for (int j = 0; j < nbreOfFeatures; j++) {
                        tempTheta[j] += ((hypothesis(samplesMatrix[i]) - samplesValues[i]) * samplesMatrix[i][j]);
                    }
                }
                for (int k = 0; k < nbreOfFeatures; k++) {
                    theta[k] -= ((2 * alpha * tempTheta[k]) / (double) nbreOfSamples);
                }
                iteration++;
            }
            
        }
    }

    /**
     * Getter for theta
     *
     * @return theta
     */
    public double[] getTheta() {
        return theta;
    }

    /**
     * Getter for iteration
     *
     * @return iteration
     */
    public int getIteration() {
        return iteration;
    }

}
