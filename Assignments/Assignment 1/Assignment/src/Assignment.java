import java.util.*;

/**
 * The class  <b>Assignment</b> is used to test our LinearRegression class. It
 * uses the provided class <b>Display</b> to show the results
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
     * In this second method, we will select a line at random. 1) we select a
     * line y = ax + b, with a randomly selected between -100 and +100 and b
     * randomly selected between -250 and +250 2) we add 500 samples randomly
     * selected on the line between -100 and +300. For each sample we add a
     * "noise" randomly selected between -1000 and +1000 (that is, for each
     * randomly selected x, we add the sample (x, ax+b+noise). where "noise" is
     * randomly selected between -1000 and 1000 3) We create an instance of
     * Display 4) we iterate gradient descent (find a number of iterations, a
     * number of updates to the instance of Display, and a step alpha that seems
     * to work
     */
    private static void randomLine() {
        double x, y, noise;
        LinearRegression line = new LinearRegression(500);
        Display graph = new Display(line);
        double a = generator.nextDouble() * 200 - 100;
        double b = generator.nextDouble() * 500 - 250;
        for (int i = 0; i < 500; i++) {
            x = (generator.nextDouble() * 400) - 100;
            noise = (generator.nextDouble() * 2000)-1000;
            y = (a * x) + b + noise;
            line.addSample(x, y);
        }
        graph.setTarget(a,b);
        for (int i = 0; i <= 50; i++) {
            System.out.println(line.currentHypothesis());
            System.out.println("Current cost:" + Double.toString(line.currentCost()));
            System.out.println("Aiming for:"+Double.toString(b)+Double.toString(a)+"*x_1");
            graph.update();
            line.gradientDescent(0.00000009, 100);

        }

    }

    public static void main(String[] args) {
        StudentInfo.display();
        System.out.println("randomLine");
        randomLine();

    }

}//I don't wanna talk about it
//It's not important