package minesweeper.gui.grid;

/**
 * The {@code CellState} enum represents the various states a cell can have in
 * the Minesweeper game. Each state defines a distinct visual and functional
 * behaviour for the cell.
 * <p>
 * The possible states are:
 * <ul>
 * <li>{@link #DEFAULT} - The cell is unpressed and has no special
 * markings.</li>
 * <li>{@link #PRESSED} - The cell has been clicked, potentially revealing
 * content such as a mine or a number.</li>
 * <li>{@link #FLAGGED} - The cell has been flagged by the player, indicating a
 * suspected mine location.</li>
 * </ul>
 * <p>
 * These states help manage the game's logic and UI updates, ensuring that cells
 * behave correctly based on their state.
 * </p>
 *
 * @see minesweeper.gui.grid.Cell
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public enum CellState {

    /**
     * The default state of a cell, unpressed and without any special markings.
     * This is the initial state for all cells at the start of the game.
     */
    DEFAULT,
    
    /**
     * The state of a cell that has been clicked by the player. In this state,
     * the cell may reveal a mine, a number indicating the count of neighbouring
     * mines, or be empty if no mines are adjacent.
     */
    PRESSED,
    
    /**
     * The state of a cell that has been flagged by the player. This state
     * indicates that the player suspects a mine is located in this cell. It
     * visually marks the cell and prevents it from being accidentally clicked.
     */
    FLAGGED
    
}