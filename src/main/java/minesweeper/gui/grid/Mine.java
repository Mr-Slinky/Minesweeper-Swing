package minesweeper.gui.grid;

import java.awt.Graphics2D;
import java.awt.Image;
import minesweeper.util.GameIcon;

/**
 * The {@code Mine} class handles the graphical representation of a mine in the
 * Minesweeper game. This class provides methods for painting a mine image on a
 * cell when the cell is revealed to contain a mine.
 * <p>
 * The mine image is cached for efficient rendering and is centred within the
 * cell.
 * </p>
 *
 * @see minesweeper.gui.grid.Cell
 * @see minesweeper.util.GameIcon
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public class Mine {

    // ------------------------------ Fields -------------------------------- //
    /**
     * The size of the cell in pixels. Used to calculate the positioning of the
     * mine image.
     */
    private static int cellSize;

    /**
     * The x-coordinate for drawing the mine image, centred within the cell.
     */
    private static int x;

    /**
     * The y-coordinate for drawing the mine image, centred within the cell.
     */
    private static int y;

    /**
     * The image representation of a mine, loaded from the game icons.
     */
    private static Image image = GameIcon.MINE_16.getIcon().getImage();

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Paints the mine image on the specified {@link Cell} using the provided
     * {@link Graphics2D} context. This method ensures the mine image is centred
     * within the cell. If the cell size has not been set, it calculates the
     * necessary dimensions for rendering.
     *
     * @param cell the {@link Cell} to paint the mine on
     * @param g the {@link Graphics2D} context used for drawing
     */
    public static void paint(Cell cell, Graphics2D g) {
        if (cellSize <= 0) {
            calculateCache(cell);
        }

        g.drawImage(image, x, y, null);
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Calculates and caches the necessary coordinates and size for centring the
     * mine image within a cell. This method is called once to optimise
     * rendering by avoiding repeated calculations.
     *
     * @param cell the {@link Cell} for which to calculate the centring
     * coordinates
     */
    private static void calculateCache(Cell cell) {
        x = (cell.getWidth() / 2) - (image.getWidth(cell) / 2);
        y = (cell.getHeight() / 2) - (image.getWidth(cell) / 2);
    }

}