package minesweeper.gui.grid;

import static minesweeper.gui.grid.CellState.FLAGGED;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import minesweeper.util.GameColor;
import static minesweeper.gui.grid.CellState.PRESSED;
import minesweeper.util.GameIcon;
import minesweeper.util.Palette;

/**
 * The {@code Cell} class represents a single cell in a Minesweeper grid. It is
 * a graphical component that extends {@link javax.swing.JLabel} and displays
 * the state of a cell in the game. A cell can be in different states: blank,
 * flagged, or pressed. When pressed, the cell can reveal whether it contains a
 * mine or a number indicating the count of neighbouring mines.
 * <p>
 * The class handles the visual representation and interactions of a cell,
 * including mouse events and state changes. It supports observer notification
 * to inform other components about state changes.
 * <p>
 * Each cell can have a unique colour, and its appearance changes based on its
 * state. For example, flagged cells have a different background colour and
 * border compared to blank or pressed cells.
 * <p>
 * Usage example:
 * <pre>
     Cell cell = new Cell(new GameColor(Color.GRAY));
     cell.setHasMine(true);
     cell.setState(CellState.PRESSED);
     cell.update();
 </pre>
 * <p>
 * Note: This class relies on external classes like {@link minesweeper.util.GameColor},
 * {@link minesweeper.util.GameIcon}, and {@link minesweeper.util.Palette} for
 * colour management and icon handling.
 * </p>
 *
 * @see javax.swing.JLabel
 * @see minesweeper.util.GameColor
 * @see minesweeper.util.GameIcon
 * @see minesweeper.util.Palette
 * @see minesweeper.gui.grid.CellState
 * @see minesweeper.gui.grid.CellObserver
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Kheagen Haskins
 */
public class Cell extends JLabel {

    // ------------------------------ Fields -------------------------------- //
    private Border borderDefault;
    private Border borderPressed;
    private Border borderFlagged;

    private CellState state;
    private GameColor gColor;
    private GameColor altColor;

    private CellObserver observer;
    private Image flag = GameIcon.FLAG_16.getIcon().getImage();

    private boolean hasMine;

    // --------------------------- Constructors ----------------------------- //
    /**
     * Constructs a new {@code Cell} with the specified colour. This constructor
     * initializes the cell with a default state and sets up the visual
     * properties and event handling.
     *
     * @param color the primary {@link GameColor} used for the cell's appearance
     */
    public Cell(GameColor color) {
        this.gColor = color;
        this.altColor = new GameColor(Palette.PRIMARY_1);
        this.state = CellState.DEFAULT;
        this.hasMine = false;

        initBorders();

        setDoubleBuffered(true);
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setBorder(borderDefault);
        setBackground(gColor.getColor());

        addMouseListener(new CellMouseEventHandler(this));
    }

    // ------------------------------ Getters ------------------------------- //
    /**
     * Returns the current state of the cell.
     *
     * @return the {@link CellState} representing the cell's current state
     */
    public CellState getState() {
        return state;
    }

    /**
     * Checks if the cell is currently in the pressed state.
     *
     * @return {@code true} if the cell is pressed, {@code false} otherwise
     */
    public boolean isPressed() {
        return state == CellState.PRESSED;
    }

    /**
     * Checks if the cell is currently flagged.
     *
     * @return {@code true} if the cell is flagged, {@code false} otherwise
     */
    public boolean isFlagged() {
        return state == CellState.FLAGGED;
    }

    /**
     * Checks if the cell has an observer registered.
     *
     * @return {@code true} if the cell has an observer, {@code false} otherwise
     */
    public boolean hasObserver() {
        return observer != null;
    }

    /**
     * Checks if the cell contains a mine.
     *
     * @return {@code true} if the cell has a mine, {@code false} otherwise
     */
    public boolean hasMine() {
        return hasMine;
    }

    // ------------------------------ Setters ------------------------------- //
    /**
     * Sets the state of the cell.
     *
     * @param state the new {@link CellState} to set
     */
    public void setState(CellState state) {
        this.state = state;
    }

    /**
     * Registers an observer for this cell. The observer will be notified of
     * state changes.
     *
     * @param observer the {@link CellObserver} to register
     */
    public void setObserver(CellObserver observer) {
        this.observer = observer;
    }

    /**
     * Sets the primary colour of the cell and updates its appearance
     * accordingly.
     *
     * @param color the new {@link Color} for the cell
     */
    public void setColor(Color color) {
        this.gColor = new GameColor(color);

        setDefaultBorder(gColor);
        setPressedBorder(gColor);
        update(color, borderDefault);
    }

    /**
     * Sets whether the cell contains a mine.
     *
     * @param hasMine {@code true} if the cell contains a mine, {@code false}
     * otherwise
     */
    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Changes the background colour of the cell when the mouse hovers over it.
     * This method updates the background colour based on the cell's current
     * state. If the cell is in the default state, the background colour will
     * switch between the default and highlight colours. If the cell is flagged,
     * it switches between the alternate default and highlight colours.
     *
     * @param mouseOver {@code true} if the mouse is hovering over the cell,
     * {@code false} otherwise
     */
    public void triggerHover(boolean mouseOver) {
        switch (state) {
            case DEFAULT:
                setBackground(mouseOver ? gColor.getHighlight() : gColor.getColor());
                break;
            case FLAGGED:
                setBackground(mouseOver ? altColor.getHighlight() : altColor.getColor());
                break;
        }
    }

    /**
     * Notifies this cell's observer about a state change. This allows the
     * observer to respond accordingly, such as updating the game logic or UI.
     * If no observer has been registered, this method does nothing.
     *
     * @param rightClick {@code true} if the state change was triggered by a
     * right-click, {@code false} otherwise
     */
    public void notifyObserver(boolean rightClick) {
        if (observer == null) {
            return;
        }

        observer.notifyCellUpdate(this, rightClick);
    }

    /**
     * Updates the visual representation of the cell based on its current state.
     * This method should be called after the cell's state changes to refresh
     * its appearance. The visual update includes changing the background colour
     * and border style depending on whether the cell is in the default,
     * pressed, or flagged state.
     * <p>
     * If the cell is pressed and contains a mine, the visual update for the
     * pressed state is skipped.
     * </p>
     */
    public void update() {
        switch (state) {
            case DEFAULT:
                update(gColor.getColor(), borderDefault);
                break;
            case PRESSED:
                if (hasMine) {
                    return; // skip pressed visual updates if mine
                }
                update(gColor.getColor(), borderPressed);
                break;
            case FLAGGED:
                update(altColor.getColor(), borderFlagged);
                break;
            default:
                throw new IllegalStateException("Unknown state: " + state);
        }

        repaint();
    }

    /**
     * Paints the component, including any custom graphics. This method is
     * overridden to provide custom rendering for the cell, such as displaying a
     * mine icon if the cell contains a mine and is pressed, or a flag icon if
     * the cell is flagged.
     * <p>
     * If the cell is flagged, a flag icon is drawn at the center of the cell.
     * If the cell contains a mine and is pressed, a mine icon is rendered using
     * the {@link Mine#paint} method.
     * </p>
     *
     * @param g the {@link Graphics} object used for drawing the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (hasMine && isPressed()) {
            Mine.paint(this, (Graphics2D) g);
        } else if (isFlagged()) {
            g.drawImage(flag, getWidth() / 2 - 8, getHeight() / 2 - 8, this);
        }
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Updates the cell's background colour and border based on the provided
     * parameters.
     *
     * @param c the {@link Color} to set as the cell's background
     * @param b the {@link Border} to set as the cell's border
     */
    private void update(Color c, Border b) {
        setBackground(c);
        setBorder(b);
    }

    /**
     * Initializes the default, pressed, and flagged borders for the cell. This
     * method sets up the visual styles for each state of the cell.
     */
    private void initBorders() {
        setDefaultBorder(gColor);
        setPressedBorder(gColor);
        setFlaggedBorder(altColor);
    }

    /**
     * Sets the default border for the cell, using a bevel border with a raised
     * style. The border colours are derived from the provided {@link GameColor}.
     *
     * @param color the {@link GameColor} to use for the default border
     */
    private void setDefaultBorder(GameColor color) {
        borderDefault = BorderFactory.createBevelBorder(
                BevelBorder.RAISED, color.getBrighter(), color.getDarker()
        );
    }

    /**
     * Sets the flagged border for the cell, using a bevel border with a raised
     * style. The border colours are derived from the provided {@link GameColor}.
     *
     * @param color the {@link GameColor} to use for the flagged border
     */
    private void setFlaggedBorder(GameColor color) {
        borderFlagged = BorderFactory.createBevelBorder(
                BevelBorder.RAISED, color.getBrighter(), color.getDarker()
        );
    }

    /**
     * Sets the pressed border for the cell, using a line border. The border
     * colour is derived from the provided {@link GameColor}.
     *
     * @param color the {@link GameColor} to use for the pressed border
     */
    private void setPressedBorder(GameColor color) {
        borderPressed = BorderFactory.createLineBorder(color.getDarker(), 1);
    }

}