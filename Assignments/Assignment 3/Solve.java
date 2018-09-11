
/**
 * @Author: HaardTrivedi
 * @StudentNumber:300021545
 * @Course: ITI 1121 - C
 * @Assignment: 3
 */
public class Solve {

    private static Cube c1 = new Cube(new Color[]{Color.BLUE, Color.GREEN, Color.WHITE, Color.GREEN, Color.BLUE, Color.RED});
    private static Cube c2 = new Cube(new Color[]{Color.WHITE, Color.GREEN, Color.BLUE, Color.WHITE, Color.RED, Color.RED});
    private static Cube c3 = new Cube(new Color[]{Color.GREEN, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.RED});
    private static Cube c4 = new Cube(new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.GREEN, Color.WHITE, Color.WHITE});

    public static Queue<Solution> generateAndTest() {

        CircularQueue<Solution> q = new CircularQueue<>(50000);
        Solution result;

        for (int i = 0; i < 24; i++) {
            if (c1.hasNext() == true) {
                c1.next();
            } else {
                c1.reset();
                c1.next();
            }

            for (int j = 0; j < 24; j++) {
                if (c2.hasNext() == true) {
                    c2.next();
                } else {
                    c2.reset();
                    c2.next();
                }

                for (int k = 0; k < 24; k++) {
                    if (c3.hasNext() == true) {
                        c3.next();
                    } else {
                        c3.reset();
                        c3.next();
                    }

                    for (int m = 0; m < 24; m++) {
                        if (c4.hasNext() == true) {
                            c4.next();
                        } else {
                            c4.reset();
                            c4.next();
                        }

                        result = new Solution(new Cube[]{c1, c2, c3, c4});
                        if (i == 0 && j == 0 && k == 0 && m == 0) {
                            result.resetNumberOfCalls();
                        }

                        if (result.isValid() == true) {
                            q.Enqueue(result);
                        }

                        if (i == 23 && j == 23 && k == 23 && m == 23) {
                            System.out.println(result.getNumberOfCalls());
                        }

                    }

                }
            }

        }

        return q;
    }

    public static Queue<Solution> breadthFirstSearch() {
        CircularQueue<Solution> open = new CircularQueue<>(50000);
        CircularQueue<Solution> solutions = new CircularQueue<>(50000);
        Solution array = new Solution(new Cube[]{c1, c2, c3, c4});
        int count=0;
        
        while (c1.hasNext()) {
            c1.next();
            Cube cube = c1.copy();
            Solution temp = new Solution(new Cube[]{cube});
            open.Enqueue(temp);
        }

        while (!open.isEmpty()) {
            c2.reset();
            c3.reset();
            c4.reset();
            
            Solution current = open.Dequeue();
            current.resetNumberOfCalls();
            
            for (int i = 0; i < 24; i++) {
                if (array.getCube(current.size()).hasNext()) {
                    array.getCube(current.size()).next();                    
                }else{
                    array.getCube(current.size()).reset();
                    array.getCube(current.size()).next();
                } 
                
                if (current.isValid(array.getCube(current.size()))) {
                    current = new Solution(current, array.getCube(current.size()));
                    if (current.size() == 5) {
                        System.out.println(current);
                        solutions.Enqueue(current);
                    } else {
                        open.Enqueue(current);
                    }
                    count+=current.getNumberOfCalls();
                }
            }

        }
        System.out.println(count);
        return solutions;

    }

    public static void main(String[] args) {
        long start, stop;

        System.out.println("generateAndTest:");
        start = System.currentTimeMillis();
        generateAndTest();
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " milliseconds");

        System.out.println("\nbreadthFirstSearch:");
        start = System.currentTimeMillis();
        breadthFirstSearch();
        stop = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (stop - start) + " milliseconds");

    }
}
