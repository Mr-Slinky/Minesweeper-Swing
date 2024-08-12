package minesweeper.gui.grid;

/**
 * The {@code CellObserver} interface defines the contract for observing and
 * responding to changes in a {@link Cell}. Implementing classes are notified
 * whenever a cell's state changes, such as when a cell is clicked or flagged.
 * <p>
 * This interface is typically used to decouple the game's logic and UI
 * components, allowing for flexible responses to cell interactions without
 * tightly coupling the observer and the observed cell.
 * </p>
 *
 * @see minesweeper.gui.grid.Cell
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public interface CellObserver {

    // ---------------------------- API Methods ----------------------------- //
    /**
     * Notifies the observer that the state of a {@link Cell} has changed.
     * Implementing classes should define how they respond to this update, which
     * may involve updating game logic, UI components, or other related
     * behaviours.
     * <p>
     * The method provides information about whether the state change was
     * triggered by a right-click, which can influence the observer's response
     * (e.g., flagging a cell).
     * </p>
     *
     * @param cell the {@link Cell} whose state has changed
     * @param rightClick {@code true} if the state change was triggered by a
     * right-click, {@code false} otherwise
     */
    void notifyCellUpdate(Cell cell, boolean rightClick);
}