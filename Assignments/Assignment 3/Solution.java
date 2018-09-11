
/**
 * @Author: HaardTrivedi
 * @StudentNumber:300021545
 * @Course: ITI 1121 - C
 * @Assignment: 3
 */

public class Solution {

    private Cube[] cubes;
    private static int numberOfCalls = 0;

    public Solution(Cube cubes[]) {
        this.cubes = new Cube[cubes.length];
        for (int i = 0; i < cubes.length; i++) {
            this.cubes[i] = cubes[i];
        }
    }

    public Solution(Solution other, Cube c) {
        if (c == null) {
            throw new IllegalStateException();
        } else if (other == null) {

            this.cubes = new Cube[]{c.copy()};

        } else {
            this.cubes = new Cube[other.size() + 1];
            for (int i = 0; i < other.size(); i++) {
                this.cubes[i] = other.cubes[i];
            }
            this.cubes[other.size()] = c;
        }
    }

    public int size() {
        return cubes.length;
    }

    public Cube getCube(int pos) {
        return cubes[pos];
    }

    public boolean isValid() {
        numberOfCalls++;
        for (int i = 0; i < size() - 1; i++) {
            for (int j = 1; j < size(); j++) {
                if (i != j) {
                    if (cubes[i].getFront() == cubes[j].getFront() || cubes[i].getRight() == cubes[j].getRight() || cubes[i].getBack() == cubes[j].getBack() || cubes[i].getLeft() == cubes[j].getLeft()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValid(Cube next) {
        if (isValid() == false) {
            return false;
        } else {
            for (int i = 0; i < size(); i++) {
                if (cubes[i].getFront() == next.getFront() || cubes[i].getRight() == next.getRight() || cubes[i].getBack() == next.getBack() || cubes[i].getLeft() == next.getLeft()) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public String toString() {
        String cube = "[";

        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) {
                cube += cubes[i].toString();
            } else {
                cube += cubes[i].toString() + ", ";
            }
        }
        return cube + "]";
    }

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public void resetNumberOfCalls() {
        numberOfCalls = 0;
    }

}
