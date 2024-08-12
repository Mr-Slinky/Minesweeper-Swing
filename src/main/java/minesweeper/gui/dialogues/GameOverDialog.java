package minesweeper.gui.dialogues;

import java.awt.Frame;
import javax.swing.JDialog;

/**
 * The {@code GameOverDialog} class represents a custom dialog window displayed
 * when the game ends, indicating whether the player has won or lost. This
 * dialog blocks user interaction with other windows until it is closed,
 * providing a modal interface to present the game over message.
 * <p>
 * The dialog is designed to be undecorated and non-resizable, ensuring a
 * consistent and focused presentation of the game over information. It uses a
 * {@link GOContentPane} as its content pane, which provides the layout and
 * styling for the message and buttons.
 * </p>
 *
 * @see javax.swing.JDialog
 * @see minesweeper.gui.dialogues.GOContentPane
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen
 */
public class GameOverDialog extends JDialog {

    // --------------------------- Constructors ----------------------------- //
    /**
     * Constructs a new {@code GameOverDialog} with the specified owner, title,
     * and message. The dialog is modal, blocking user interaction with other
     * windows until it is closed. It is also undecorated and non-resizable,
     * providing a clean and consistent look.
     *
     * @param owner the parent frame of the dialog
     * @param title the title of the dialog window
     * @param message the game over message to display in the dialog
     */
    public GameOverDialog(Frame owner, String title, String message) {
        super(owner, title, true); // true = blocks user interaction until closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);

        setContentPane(new GOContentPane(this, message));
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Displays a {@code GameOverDialog} with the specified owner, title, and
     * message. This static method creates a new instance of
     * {@code GameOverDialog}, sets it visible, and ensures it blocks
     * interaction with other windows until closed.
     *
     * @param owner the parent frame of the dialog
     * @param title the title of the dialog window
     * @param message the game over message to display in the dialog
     */
    public static void showMessageDialog(Frame owner, String title, String message) {
        GameOverDialog dialog = new GameOverDialog(owner, title, message);
        dialog.setVisible(true);
    }

}