
/**
 * The class  <b>Assignment</b> is used to test our LinearRegression class.
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class Assignment extends java.lang.Object {

    /**
     * Random generator
     */
    private static java.util.Random generator = new java.util.Random();

    /**
     * In this first method, we are simply using sample points that are on a
     * straight plane. We will use the plane z= x + 2x. In this method, 1) we
     * create an instance of LinearRegression. 2) we add 2,000 samples from the
     * plane z= x + 2x as follows: add the sample [(i, 2i), 5i] for 0<=i<=999
     * add the sample [(2i, i), 4i] for 0<=i<=999 3) we iterate gradient descent
     * 10,000, printing out the current hypothesis and the current cost every
     * 1,000 iterations, using a step alpha of 0.000000003
     */
    private static void setPlane() {
        LinearRegression straight = new LinearRegression(2,2000);
        for (double i=0;i<1000;i++){
            double[] x=new double[2];double[] y=new double[2];
            x[0]=i;x[1]=2*i;y[0]=2*i;y[1]=i;
            straight.addSample(x, 5*i);
            straight.addSample(y, 4*i);
        }
        for (int i = 0; i <= 10; i++) {
            System.out.println(straight.currentHypothesis());
            System.out.println("Current cost:" + Double.toString(straight.currentCost()));
            straight.gradientDescent(0.000000003, 1000);

        }
    }

    public static void main(String[] args) {

        StudentInfo.display();
        System.out.println("setPlane");
        setPlane();

    }

}
