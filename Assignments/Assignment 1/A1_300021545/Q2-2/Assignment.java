
/**
 * The class  <b>Assignment</b> is used to test our LinearRegression class.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */
public class Assignment extends java.lang.Object{

    /**
     * Random generator
     */
    private static java.util.Random generator = new java.util.Random();

    /**
     * In this first method, we are simply using sample points that are on a
     * straight plane. We will use the plane z= x + 2x. In his method, 1) we
     * create an instance of LinearRegression. 2) we add 2,000 samples from the
     * plane z= x + 2x as follows: add the sample [(i, 2i), 5i] for 0<=i<=999
     * add the sample [(2i, i), 4i] for 0<=i<=999 3) we iterate gradient descent
     * 10,000, printing out the current hypothesis and the current cost every
     * 1,000 iterations, using a step alpha of 0.000000003
     */
    private static void setPlane() {
        LinearRegression plane = new LinearRegression(2, 2000);
        for (double i = 0; i < 1000; i++) {
            double[] x = new double[2];
            double[] y = new double[2];
            x[0] = i;
            x[1] = 2 * i;
            y[0] = 2 * i;
            y[1] = i;
            plane.addSample(x, 5 * i);
            plane.addSample(y, 4 * i);
        }
        for (int i = 0; i <= 10; i++) {
            System.out.println(plane.currentHypothesis());
            System.out.println("Current cost:" + Double.toString(plane.currentCost()));
            plane.gradientDescent(0.000000003, 1000);

        }
    }

    /**
     * In this second method, we will select a plane at random. 1) we select a
     * line z = ax + by + c, with a, b and c randomly selected between -100 and
     * +100 2) we add 5000 samples randomly selected on the plane with x and y
     * both randomly selected between 50 and 4000. For each sample we add a
     * "noise" randomly selected between -20 and +20 (that is, for each randomly
     * selected x and y we add the sample [ (x,y), ax+by+c+noise). where "noise"
     * is randomly selected between -20 and 20 4) we iterate gradient descent
     * (find a number of iterations, and a step alpha that seems to work,
     * regularly printing the target, the current hypothesis and the current
     * cost)
     */
    private static void randomPlane() {
        LinearRegression curve = new LinearRegression(2, 5000);
        double a = generator.nextDouble() * 200 - 100;
        double b = generator.nextDouble() * 200 - 100;
        double c = generator.nextDouble() * 200 - 100;
        for (double i = 0; i < 1000; i++) {
            double randx = generator.nextDouble() * 3950 - 50;
            double randy = generator.nextDouble() * 3950 - 50;
            double noise = generator.nextDouble() * 40 - 20;

            double[] x = new double[2];
            x[0] = randx;
            x[1] = 2 * randy;
            double y = (a * randx) + (b * randy) + c + noise;
            curve.addSample(x, y);
        }
        for (int i = 0; i <= 10; i++) {
            System.out.println(curve.currentHypothesis());
            System.out.println("Current cost:" + Double.toString(curve.currentCost()));
            System.out.println("Aiming for:"+Double.toString(c)+Double.toString(a)+"*x_1"+Double.toString(b)+"*x_2");
            curve.gradientDescent(0.0000001, 100000);
        }
    }

    public static void main(String[] args) {
        StudentInfo.display();
        System.out.println("randomPlane");
        setPlane();
        randomPlane();

    }

}
