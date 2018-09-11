
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It
 * extends
 * <b>JFrame</b> and lays out a matrix of <b>DotButton</b> (the actual game) and
 * two instances of JButton. The action listener for the buttons is the
 * controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class GameView extends JFrame {

    private DotButton[][] board;
    private GameModel gameModel;
    private JLabel label;
    private JButton reset, quit;

    private int width, height;

    /**
     * Constructor used for initializing the Frame
     *
     * @param gameModel the model of the game (already initialized)
     * @param gameController the controller
     */
    public GameView(GameModel gameModel, GameController gameController) {
        this.gameModel = gameModel;

        this.width = gameModel.getWidth();
        this.height = gameModel.getHeight();
        JFrame form = new JFrame();
        form.setTitle("MineSweeper it -- the ITI 1121 version");
        form.setSize(600, 500);
        form.setResizable(false);
        form.setLocationRelativeTo(null);

        //JLabel field = new JLabel("Test");
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        //panel.add(field, BorderLayout.NORTH);

        form.add(panel, BorderLayout.NORTH);
        form.add(panel2, BorderLayout.SOUTH);

        panel2.setSize(150, 35);
        panel.setLayout(new GridLayout(height, width, 0, 0));
        board = new DotButton[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new DotButton(i, j, 11);
                board[i][j].setPreferredSize((new Dimension(30, 30)));
                board[i][j].addActionListener(gameController);
                panel.add(board[i][j]);
            }
        }

        label = new JLabel("Number of steps: " + gameModel.getNumberOfSteps());
        reset = new JButton("Reset");
        quit = new JButton("Quit");
        panel2.add(label);
        panel2.add(reset);
        panel2.add(quit);
        reset.addActionListener(gameController);
        quit.addActionListener(gameController);

        form.setVisible(true);
        
    }

    /**
     * update the status of the board's DotButton instances based on the current
     * game model, then redraws the view
     */
    public void update() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j].setIconNumber(getIcon(i,j));

            }
        }
        label.setText("Number of steps: " + Integer.toString(gameModel.getNumberOfSteps()));
    }

    /**
     * returns the icon value that must be used for a given dot in the game
     *
     * @param i the x coordinate of the dot
     * @param j the y coordinate of the dot
     * @return the icon to use for the dot at location (i,j)
     */
    private int getIcon(int i, int j) {
        if (gameModel.get(i, j).isCovered() == false) {
            if (gameModel.hasBeenClicked(i,j) == true && gameModel.get(i, j).isMined() == true) {
                return 10;
            } else if (gameModel.isMined(i,j) == true) {
                return 9;
            } else {
                return gameModel.getNeighbooringMines(i, j);
            }
        } else {
            return 11;
        }

    }

}
