package minesweeper;

import minesweeper.gui.GameFrame;
import minesweeper.gui.MainInterface;
import java.awt.EventQueue;
import javax.swing.JPanel;
import minesweeper.gui.dialogues.GameOverDialog;
import minesweeper.gui.grid.Difficulty;

/**
 * The {@code GameManager} class serves as the central control point for
 * managing the Minesweeper game. It is responsible for initialising the game,
 * handling major game state changes, and interacting with the user interface.
 * This class ensures that the game's logic and UI are properly coordinated.
 * <p>
 * The main responsibilities of the {@code GameManager} include:
 * <ul>
 * <li>Starting the game and setting up the main interface.</li>
 * <li>Restarting the game when requested.</li>
 * <li>Displaying the game over dialog with the appropriate message based on the
 * player's performance.</li>
 * <li>Ending the game and disposing of resources.</li>
 * </ul>
 * <p>
 * This class uses the {@link MainInterface} to interact with the game's UI
 * components and manage the game's difficulty and grid settings.
 * </p>
 *
 * @see minesweeper.gui.MainInterface
 * @see minesweeper.gui.dialogues.GameOverDialog
 * @see minesweeper.gui.grid.Difficulty
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public class GameManager {
    
    /**
     * Starts the Minesweeper game. This method is the entry point of the
     * application and is responsible for initialising the game's graphical user
     * interface. It runs the game in the Event Dispatch Thread to ensure thread
     * safety in Swing applications.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> GameFrame.getGameFrame().setVisible(true));
    }
    
    // ------------------------------ Fields -------------------------------- //
    /**
     * The main user interface of the game, initialised with the intermediate
     * difficulty level and a cell size of 25. This interface handles the main
     * game board and user interactions.
     */
    private static final MainInterface mainInterface = new MainInterface(Difficulty.INTERMEDIATE, 25);

    // ------------------------------ Getters ------------------------------- //
    /**
     * Returns the main interface of the game. This method provides global
     * access to the {@link JPanel} that contains the game's main user interface
     * elements.
     *
     * @return the main interface {@link JPanel} of the game
     */
    public static JPanel getMainInterface() {
        return mainInterface;
    }

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Restarts the game by reinitialising the main interface. This method
     * clears the current game state and sets up a new game with the same
     * settings as the previous one.
     */
    public static void restart() {
        mainInterface.restart();
    }

    /**
     * Displays the game over dialog with a message indicating whether the
     * player has won or lost. This method is called when the game reaches an
     * end condition, either by winning or losing.
     *
     * @param hasWon {@code true} if the player has won the game, {@code false}
     * if the player has lost
     */
    public static void showGameOver(boolean hasWon) {
        GameOverDialog.showMessageDialog(
                GameFrame.getGameFrame(),
                "Game Over", // Title for the dialog
                hasWon ? "You Win!" : "You Lose!" // Message based on the game outcome
        );
    }

    /**
     * Ends the game by disposing of the main window. This method is typically
     * called when the game needs to be closed or exited, releasing any
     * resources held by the main interface.
     */
    public static void endGame() {
        GameFrame.getGameFrame().dispose();
        System.exit(0);
    }
    
}