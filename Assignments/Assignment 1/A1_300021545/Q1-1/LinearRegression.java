/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HaardTrivedi
 */
/**
 * The class  <b>LinearRegression</b> implements gradient descent with 1
 * variable.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression {

    /**
     * Number of samples (usually "m" in literature)
     */
    private int nbreOfSamples;

    /**
     * the sample vector
     */
    private double[] samples;

    /**
     * the samples target values
     */
    private double[] samplesValues;

    /**
     * the current hypothesis function: theta0 + theta1 x
     */
    private double theta0, theta1;

    /**
     * used to ensure that the object is ready
     */
    private int currentNbreOfSamples;

    /**
     * counts how many iterations have been performed
     */
    private int iteration;

    /**
     * Object's constructor. The constructor initializes the instance variables.
     * The starting hypothesis is y = 0;
     *
     *
     * @param m the number of samples that we will have
     *
     */
    public LinearRegression(int m) {
        samples = new double[m];
        samplesValues = new double[m];
        nbreOfSamples = m;
        iteration = 0;
        theta0 = 0;
        theta1 = 0;
        currentNbreOfSamples = 0;
    }

    /**
     * Adds a new sample to sample and to samplesValues. This method must be
     * iteratively called with all the samples before the gradient descent can
     * be started
     *
     * @param x the new sample
     * @param y the corresponding expected value
     *
     */
    public void addSample(double x, double y) {
        samples[currentNbreOfSamples] = x;
        samplesValues[currentNbreOfSamples] = y;
        currentNbreOfSamples++;
    }

    /**
     * Returns the current hypothesis for the value of the input
     *
     * @param x the input for which we want the current hypothesis
     *
     * @return theta0 + theta1 x
     */
    private double hypothesis(double x) {
        return (theta0 + (theta1 * x));
    }

    /**
     * Returns a string representation of hypothesis function
     *
     * @return the string "theta0 + theta1 x"
     */
    public String currentHypothesis() {
        return "Current Hypothesis: " + Double.toString(getTheta0()) + "+" + Double.toString(getTheta1()) + "x";
    }

    /**
     * Returns the current cost
     *
     * @return the current value of the cost function
     */
    public double currentCost() {
        double[] x = getSamples();
        double[] y = getSamplesValues();
        double cost = 0;
        for (int i = 0; i < nbreOfSamples; i++) {
            cost = (cost + Math.pow(hypothesis(x[i]) - y[i], 2));
        }
        cost = (1 / (double) nbreOfSamples) * cost;
        return cost;
    }

    /**
     * runs several iterations of the gradient descent algorithm
     *
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */
    public void gradientDescent(double alpha, int numberOfSteps) { //where is that used?
        if (iteration < 5000) {
            for (int h = 0; h < numberOfSteps; h++) {
                double hypo0 = 0;
                double hypo1 = 0;
                for (int i = 0; i < nbreOfSamples; i++) {
                    hypo0 += (hypothesis(samples[i]) - samplesValues[i]);
                    hypo1 += ((hypothesis(samples[i]) - samplesValues[i]) * samples[i]);
                }

                theta0 -= ((2 * alpha * hypo0) / (double) nbreOfSamples);
                theta1 -= ((2 * alpha * hypo1) / (double) nbreOfSamples);
                iteration++;
            }
        }

    }

    /**
     * Getter for theta0
     *
     * @return theta0
     */
    public double getTheta0() {
        return theta0;
    }

    /**
     * Getter for theta1
     *
     * @return theta1
     */
    public double getTheta1() {
        return theta1;
    }

    /**
     * Getter for samples
     *
     * @return samples
     */
    public double[] getSamples() {
        return samples;
    }

    /**
     * Getter for getSamplesValues
     *
     * @return getSamplesValues
     */
    public double[] getSamplesValues() {
        return samplesValues;
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
