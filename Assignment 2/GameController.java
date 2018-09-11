
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

/**
 * The class <b>GameController</b> is the controller of the game. It is a
 * listener of the view, and has a method <b>play</b> which computes the next
 * step of the game, and updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameController implements ActionListener {

    // ADD YOUR INSTANCE VARIABLES HERE
    private GameModel model;
    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's
     * view and the game's model instances
     *
     * @param width the width of the board on which the game will be played
     * @param height the height of the board on which the game will be played
     * @param numberOfMines the number of mines hidden in the board
     */
    public GameController(int width, int height, int numberOfMines) {
        model = new GameModel(width, height, numberOfMines);
        view = new GameView(model, this);
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof DotButton) {
            play(((DotButton) (e.getSource())).getRow(), ((DotButton) (e.getSource())).getRow());

        } else if (e.getSource() instanceof JButton) {

            JButton button = (JButton) (e.getSource());

            if (button.getText().equals("Quit")) {
                System.exit(0);
            } else if (button.getText().equals("Reset")) {
                reset();
            }
        }

    }

    /**
     * resets the game
     */
    private void reset() {
        model.reset();
        view.update();
    }

    /**
     * <b>play</b> is the method called when the user clicks on a square. If
     * that square is not already clicked, then it applies the logic of the game
     * to uncover that square, and possibly end the game if that square was
     * mined, or possibly uncover some other squares. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives to options: start a new game, or exit
     *
     * @param width the selected column
     * @param heigth the selected line
     */
    private void play(int width, int heigth) {
        DotInfo dot = new DotInfo(width, heigth);
        if (model.get(width, heigth).hasBeenClicked() == false && model.get(width, heigth).isCovered() == true) {
            model.get(width, heigth).click();
            model.get(width, heigth).uncover();
            model.step();
            view.update();

            if (model.isMined(width, heigth) == true) {
                model.uncoverAll();
                view.update();
                JFrame frame = new JFrame();
                Object[] options = {"Quit", "Play Again"};
                int endGame = JOptionPane.showOptionDialog(frame, "Aouch, you lost in " + Integer.toString(model.getNumberOfSteps()) + " steps!\nWould you like to play again?", "Boom!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (endGame == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    reset();
                }

            } else if (model.getNeighbooringMines(width, heigth) == 0) {
                clearZone(dot);
                view.update();

            } else if (model.isFinished() == true) {
                JFrame frame = new JFrame();
                Object[] options = {"Quit", "Play Again"};
                int endGame = JOptionPane.showOptionDialog(frame, "Congratulations, you won in " + Integer.toString(model.getNumberOfSteps()) + " steps!\nWould you like to play again?", "Won", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (endGame == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    reset();
                }
            } else if (model.isBlank(width, heigth) == true) {
            for (int i = model.get(width, heigth).getY() - 1; i <= model.get(width, heigth).getY() + 1; i++) {
                for (int j = model.get(width, heigth).getX() - 1; j <= model.get(width, heigth).getX() + 1; j++) {
                    if (j == -1 || j == model.getWidth() || i == -1 || i == model.getHeight()) {
                        continue;
                    } else if (j == model.get(width, heigth).getY() && j == model.get(width, heigth).getX()) {
                        continue;
                    } else {
                        model.get(j, i).uncover();
                        model.get(j, i).click();
                    }
                }
            }
            view.update();
        }
        } 
    }

    /**
     * <b>clearZone</b> is the method that computes which new dots should be
     * ``uncovered'' when a new square with no mine in its neighborood has been
     * selected
     *
     * @param initialDot the DotInfo object corresponding to the selected
     * DotButton that had zero neighbouring mines
     */
    private void clearZone(DotInfo initialDot) {
        //Stack-Based-clearZone(initialDot).
        //create an empty stack.
        //push the initial dot to the stack
        //While the stack is not empty Do
        //remove a dot d from stack
        //For all dot n neighboring d
        //If n is not covered then
        //uncover it
        //if n has no neighbouring mines
        //push n onto the Stack
        //End if
        //End If
        //End For
        //End While
        GenericArrayStack<DotInfo> myStack = new GenericArrayStack<DotInfo>(9);
        myStack.push(initialDot);

        while (myStack.isEmpty() != true) {
            DotInfo d = myStack.pop();
            int dx = d.getX();
            int dy = d.getY();

            for (int i = dx - 1; i < dx + 1; i++) {
                for (int j = dy - 1; j < dy + 1; j++) {

                    if (model.get(i, j).isCovered() == true) {
                        model.get(i, j).uncover();

                        if (model.getNeighbooringMines(i, j) == 0) {
                            myStack.push(model.get(i, j));
                        }
                    }
                }
            }
        }

    }

}
