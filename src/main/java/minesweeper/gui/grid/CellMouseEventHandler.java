package minesweeper.gui.grid;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 * The {@code CellMouseEventHandler} class handles mouse events for a
 * {@link Cell} in the Minesweeper game. This class extends {@link MouseAdapter}
 * to provide custom behaviour for mouse interactions with a cell, including
 * mouse enter, exit, and press events.
 * 
 * <p>
 * This handler is responsible for updating the cell's visual state when the
 * mouse hovers over it and notifying the cell's observer when the cell is
 * pressed.
 * </p>
 *
 * @see java.awt.event.MouseAdapter
 * @see java.awt.event.MouseEvent
 * @see javax.swing.SwingUtilities
 * @see minesweeper.gui.grid.Cell
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen
 */
public class CellMouseEventHandler extends MouseAdapter {

    // ------------------------------ Fields -------------------------------- //
    /**
     * The {@link Cell} associated with this event handler.
     */
    private Cell cell;

    // --------------------------- Constructors ----------------------------- //
    /**
     * Constructs a new {@code CellMouseEventHandler} for the specified
     * {@link Cell}.
     *
     * @param cell the {@link Cell} to associate with this event handler
     */
    public CellMouseEventHandler(Cell cell) {
        this.cell = cell;
    }

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Invoked when the mouse enters the cell area. Triggers a hover effect on
     * the cell by changing its background colour.
     *
     * @param ev the {@link MouseEvent} that occurred
     */
    @Override
    public void mouseEntered(MouseEvent ev) {
        cell.triggerHover(true);
    }

    /**
     * Invoked when the mouse exits the cell area. Removes the hover effect by
     * resetting the cell's background colour.
     *
     * @param ev the {@link MouseEvent} that occurred
     */
    @Override
    public void mouseExited(MouseEvent ev) {
        cell.triggerHover(false);
    }

    /**
     * Invoked when the mouse is pressed on the cell. Notifies the cell's
     * observer if the cell is not already pressed. The right-click status is
     * determined to distinguish between left and right mouse button actions.
     *
     * @param ev the {@link MouseEvent} that occurred
     */
    @Override
    public void mousePressed(MouseEvent ev) {
        if (cell.isPressed()) {
            return; // Cells cannot be unpressed once pressed
        }

        if (cell.hasObserver()) {
            cell.notifyObserver(SwingUtilities.isRightMouseButton(ev));
        }
    }

}