
import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. It
 * stores the following information: - the state of all the ``dots'' on the
 * board (mined or not, clicked or not, number of neighbooring mines...) - the
 * size of the board - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough
 * appropriate Getters. The controller can also update the model through
 * Setters. Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameModel {

    private int width, height, mines, steps;
    private DotInfo[][] DotInfoArray;
    private Random generator;

    /**
     * Constructor to initialize the model to a given size of board.
     *
     * @param width the width of the board
     *
     * @param heigth the heigth of the board
     *
     * @param numberOfMines the number of mines to hide in the board
     */
    public GameModel(int width, int height, int numberOfMines) {
        this.width = width;
        this.height = height;
        this.mines = numberOfMines;
        generator = new Random();
        reset();

    }

    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up .
     */
    public void reset() {
        steps = 0;
        mines = 0;
        //generator = new Random();

        DotInfoArray = new DotInfo[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                DotInfo dot = new DotInfo(i, j);
                DotInfoArray[i][j] = dot;
            }
        }

        for (int k = 0; k < mines; k++) {
            int w = generator.nextInt(width - 1);
            int h = generator.nextInt(height - 1);

            if (DotInfoArray[w][h].isMined() == false) {
                DotInfoArray[w][h].setMined();
            } 
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int neighbouringMines = 0;
                
                if (DotInfoArray[i][j].isMined() == false) {

                    for (int l = i - 1; l <= i + 1; l++) {

                        for (int m = j - 1; m <= j + 1; m++) {

                            if (l >= 0 && m >= 0 && l < width && m < height) {

                                if (DotInfoArray[l][m].isMined()) {

                                    neighbouringMines++;
                                }
                            }
                        }
                    }
                    DotInfoArray[i][j].setNeighbooringMines(neighbouringMines);
                }
            }
        }

    }

    /**
     * Getter method for the heigth of the game
     *
     * @return the value of the attribute heigthOfGame
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter method for the width of the game
     *
     * @return the value of the attribute widthOfGame
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns true if the dot at location (i,j) is mined, false otherwise
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */
    public boolean isMined(int i, int j) {

        return DotInfoArray[i][j].isMined();

    }

    /**
     * returns true if the dot at location (i,j) has been clicked, false
     * otherwise
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */
    public boolean hasBeenClicked(int i, int j) {

        return DotInfoArray[i][j].hasBeenClicked();
    }

    /**
     * returns true if the dot at location (i,j) has zero mined neighboor, false
     * otherwise
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */
    public boolean isBlank(int i, int j) {

        return DotInfoArray[i][j].getNeighbooringMines() == 0;

    }

    /**
     * returns true if the dot is covered, false otherwise
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */
    public boolean isCovered(int i, int j) {
        return DotInfoArray[i][j].isCovered()==false;

    }

    /**
     * returns the number of neighbooring mines of the dot at location (i,j)
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the number of neighbooring mines at location (i,j)
     */
    public int getNeighbooringMines(int i, int j) {

        return DotInfoArray[i][j].getNeighbooringMines();

    }

    /**
     * Sets the status of the dot at location (i,j) to uncovered
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     */
    public void uncover(int i, int j) {

        DotInfoArray[i][j].uncover();

    }

    /**
     * Sets the status of the dot at location (i,j) to clicked
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     */
    public void click(int i, int j) {

        DotInfoArray[i][j].click();

    }

    /**
     * Uncover all remaining covered dot
     */
    public void uncoverAll() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!DotInfoArray[i][j].hasBeenClicked()) {
                    DotInfoArray[i][j].uncover();
                }
            }
        }

    }

    /**
     * Getter method for the current number of steps
     *
     * @return the current number of steps
     */
    public int getNumberOfSteps() {
        return steps;
    }

    /**
     * Getter method for the model's dotInfo reference at location (i,j)
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     *
     * @return model[i][j]
     */
    public DotInfo get(int i, int j) {

        return DotInfoArray[i][j];

    }

    /**
     * The method <b>step</b> updates the number of steps. It must be called once
     * the model has been updated after the payer selected a new square.
     */
    public void step() {

        steps++;

    }

    /**
     * The metod <b>isFinished</b> returns true iff the game is finished, that
     * is, all the nonmined dots are uncovered.
     *
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished() {
        int count = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!DotInfoArray[i][j].isCovered()) {
                    count++;
                }
            }
        }
        if (width * height - mines == count) {
            return true;
        }
        return false;

    }

    /**
     * Builds a String representation of the model
     *
     * @return String representation of the model
     */
    public String toString() {
        String strGame = null;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (DotInfoArray[i][j].isCovered() == false) {
                    strGame += " * ";
                } else if (DotInfoArray[i][j].isMined() == true) {
                    strGame += " x ";
                } else {
                    strGame += DotInfoArray[i][j].getNeighbooringMines();
                }

            }
        }
        return strGame;
    }
}
