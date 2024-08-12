package minesweeper.gui;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import minesweeper.GameManager;

/**
 * The {@code GameFrame} class is a singleton implementation of a {@link JFrame}
 * that serves as the main window for the Minesweeper game. It provides a
 * consistent and controlled environment for displaying the game's user
 * interface and managing the application's window settings.
 * <p>
 * The class ensures that only one instance of the game window exists at any
 * time, providing global access through a static method. It integrates the
 * game's main interface, managed by {@link GameManager}, into the window's
 * content pane.
 * </p>
 * <p>
 * Key features of the {@code GameFrame} include:
 * <ul>
 * <li>Singleton pattern to ensure a single instance of the main game
 * window.</li>
 * <li>Automatic setup of the game's main interface as the content pane.</li>
 * <li>Centralised control over window behaviours, such as close operations and
 * positioning.</li>
 * </ul>
 * </p>
 *
 * @see javax.swing.JFrame
 * @see minesweeper.GameManager
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public class GameFrame extends JFrame {

    /**
     * The single instance of {@code GameFrame}, ensuring a singleton pattern.
     */
    private static final GameFrame instance = new GameFrame();

    /**
     * Returns the singleton instance of the {@code GameFrame}. This method
     * provides global access to the main game window, ensuring that only one
     * instance exists throughout the application's lifecycle.
     *
     * @return the singleton {@code GameFrame} instance
     */
    public static GameFrame getGameFrame() {
        return instance;
    }

    /**
     * Private constructor for {@code GameFrame}, enforcing the singleton
     * pattern. Initialises the frame by setting the game's main interface as
     * the content pane, packing the frame to fit the preferred size of its
     * components, and centring the window on the screen. The frame is set to
     * close the application upon window close.
     *
     * @throws HeadlessException if the system does not support a display screen
     */
    private GameFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(GameManager.getMainInterface());
        pack();
        setLocationRelativeTo(null);
    }

}