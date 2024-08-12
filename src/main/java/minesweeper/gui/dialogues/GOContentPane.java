package minesweeper.gui.dialogues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import minesweeper.GameManager;
import minesweeper.util.GameFont;
import minesweeper.util.Palette;

/**
 * The {@code GOContentPane} class represents the content pane for the game over
 * dialog in the Minesweeper game. It provides a user interface for displaying a
 * game over message and offering options to restart the game or exit. The
 * design includes custom styling for buttons and labels, ensuring a consistent
 * visual appearance that matches the game's theme.
 * <p>
 * This class uses {@link JPanel} as its base and is composed of several key
 * components:
 * <ul>
 * <li>A message label to display the game over text.</li>
 * <li>Buttons for restarting the game and exiting the application.</li>
 * </ul>
 * The {@code GOContentPane} is designed to be used within a {@link JDialog} and
 * interacts with the {@link GameManager} to control the game flow based on user
 * actions.
 * </p>
 *
 * @see javax.swing.JPanel
 * @see javax.swing.JDialog
 * @see minesweeper.GameManager
 * @see minesweeper.util.GameFont
 * @see minesweeper.util.Palette
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public class GOContentPane extends JPanel {

    // ------------------------------ Fields -------------------------------- //
    /**
     * The default font used for the game over message and buttons, derived from
     * the {@link GameFont#RAINYHEARTS}.
     */
    private static final Font DEFAULT_FONT = GameFont.RAINYHEARTS.getFont();

    /**
     * The parent dialog containing this content pane.
     */
    private JDialog parent;

    /**
     * The label displaying the game over message.
     */
    private JLabel lblMessage;

    /**
     * The button to restart the game.
     */
    private JButton btnRestart;

    /**
     * The button to exit the game.
     */
    private JButton btnExit;

    /**
     * The font used for the game over message.
     */
    private Font messageFont;

    /**
     * The font used for the buttons.
     */
    private Font buttonFont;

    /**
     * The background color of the buttons.
     */
    private Color btnBackground;

    /**
     * The foreground color of the buttons, ensuring high contrast.
     */
    private Color btnForeground;

    // --------------------------- Constructors ----------------------------- //
    /**
     * Constructs a new {@code GOContentPane} with the specified parent dialog
     * and game over message text. This constructor sets up the UIManager
     * properties for button selection color, initializes fonts and colors, and
     * lays out the components within the panel.
     *
     * @param parent the parent {@link JDialog} that contains this content pane
     * @param gameOverText the text to display as the game over message
     */
    public GOContentPane(JDialog parent, String gameOverText) {
        UIManager.put("Button.select", Palette.PRIMARY_2);

        this.parent = parent;
        this.messageFont = DEFAULT_FONT.deriveFont(62f);
        this.buttonFont = DEFAULT_FONT.deriveFont(20f);
        this.btnBackground = Palette.PRIMARY_1;
        this.btnForeground = Palette.getHighContrastAgainst(btnBackground);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 200));
        setSize(getPreferredSize());
        setBackground(Palette.PRIMARY_3);
        setBorder(BorderFactory.createLineBorder(Palette.PRIMARY_2, 2));
        initComponents(gameOverText);
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Initializes the components of the content pane, including the game over
     * message label and the buttons. This method sets up the layout and event
     * listeners for the buttons.
     *
     * @param message the game over message to display
     */
    private void initComponents(String message) {
        btnRestart = createJButton("Restart");
        btnExit = createJButton("Exit Game");
        lblMessage = createLabel(message);

        btnRestart.addActionListener(ev -> {
            parent.dispose();
            GameManager.restart();
        });

        btnExit.addActionListener(ev -> {
            parent.dispose();
            GameManager.endGame();
        });

        JPanel bottomPanel = new JPanel(true);
        bottomPanel.setBackground(Palette.PRIMARY_3);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(btnRestart);
        bottomPanel.add(Box.createVerticalStrut(5));
        bottomPanel.add(btnExit);

        add(lblMessage, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a {@link JButton} with the specified text and custom styling.
     * This method sets the button's background and foreground colors, font, and
     * other properties.
     *
     * @param text the text to display on the button
     * @return a styled {@link JButton} instance
     */
    private JButton createJButton(String text) {
        JButton btn = new JButton(text);

        btn.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 12));
        btn.setFocusPainted(false);
        btn.setBackground(btnBackground);
        btn.setForeground(btnForeground);
        btn.setPreferredSize(new Dimension(Short.MAX_VALUE, 30));
        btn.setMaximumSize(btn.getPreferredSize());
        btn.setFont(buttonFont);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    /**
     * Creates a {@link JLabel} with the specified message and custom styling.
     * This method sets the label's font, text alignment, and foreground color.
     *
     * @param message the message to display on the label
     * @return a styled {@link JLabel} instance
     */
    private JLabel createLabel(String message) {
        JLabel lbl = new JLabel(message);

        lbl.setHorizontalAlignment(JLabel.CENTER);
        lbl.setVerticalAlignment(JLabel.CENTER);
        lbl.setForeground(Palette.LIGHT);
        lbl.setFont(messageFont);

        return lbl;
    }

}