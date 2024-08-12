package minesweeper.gui.grid;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ThreadLocalRandom.current;
import javax.swing.JPanel;
import minesweeper.GameManager;
import minesweeper.util.GameColor;
import minesweeper.util.GameFont;
import minesweeper.util.Palette;

/**
 * The {@code GameGrid} class represents the Minesweeper game grid and manages
 * the cells within it. This class extends {@link JPanel} and implements
 * {@link CellObserver} to handle cell updates and interactions.
 * <p>
 * The grid is initialised based on a specified {@link Difficulty} level,
 * determining the number of rows, columns, and mines. The {@code GameGrid}
 * handles user interactions, including cell clicks and flagging, and updates
 * the game state accordingly.
 * </p>
 * <p>
 * Key responsibilities of the {@code GameGrid} include:
 * <ul>
 * <li>Initialising the grid of {@link Cell} objects based on the difficulty
 * level.</li>
 * <li>Handling user interactions, such as clicks and flags, and updating the
 * cell states.</li>
 * <li>Managing game logic, including mine placement and determining win/loss
 * conditions.</li>
 * </ul>
 * </p>
 * <p>
 * The class also provides methods for configuring the appearance of the grid,
 * such as setting cell colours.
 * </p>
 *
 * @see minesweeper.gui.grid.Cell
 * @see minesweeper.gui.grid.CellObserver
 * @see minesweeper.gui.grid.Difficulty
 * @see javax.swing.JPanel
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Kheagen Haskins
 */
public class GameGrid extends JPanel implements CellObserver {

// ------------------------------ Fields -------------------------------- //
    /**
     * The default colour for cells in the grid, defined by
     * {@link Palette#PRIMARY_3}.
     */
    public static final Color DEFAULT_CELL_COLOR = Palette.PRIMARY_3;

    /**
     * The number of rows in the game grid, determined by the selected
     * difficulty.
     */
    private final int rows;

    /**
     * The number of columns in the game grid, determined by the selected
     * difficulty.
     */
    private final int cols;

    /**
     * The total number of mines in the game grid, determined by the selected
     * difficulty.
     */
    private final int mineCount;

    /**
     * The current number of flagged cells in the game grid.
     */
    private int flagCount;

    /**
     * The current number of pressed cells in the game grid. Initially set to 0.
     */
    private int pressedCount = 0;

    /**
     * A flag indicating whether the first cell click has occurred. Used to
     * ensure the first click does not result in a loss.
     */
    private boolean firstClick = false;

    /**
     * The cached width of a cell. Used for performance optimisation in
     * rendering.
     */
    private int cw;

    /**
     * The cached height of a cell. Used for performance optimisation in
     * rendering.
     */
    private int ch;

    /**
     * A flag indicating whether the cache values (cw, ch) are valid.
     */
    private boolean cacheValid = false;

    /**
     * The primary colour used for cells in the grid, initially set to
     * {@link #DEFAULT_CELL_COLOR}.
     */
    private GameColor cellColor = new GameColor(DEFAULT_CELL_COLOR);

    /**
     * The font used for displaying numbers and other text in the cells.
     */
    private Font font = GameFont.RAINYHEARTS.getFont().deriveFont(Font.BOLD, 16f);

    /**
     * The 2D array representing the grid of cells in the game.
     */
    private Cell[][] grid;

// --------------------------- Constructors ----------------------------- //
    /**
     * Constructs a new {@code GameGrid} based on the specified
     * {@link Difficulty} level. Initialises the grid with the appropriate
     * number of rows, columns, and mines, and sets up the layout and visual
     * properties.
     *
     * @param difficulty the {@link Difficulty} level used to configure the grid
     * size and mine count
     */
    public GameGrid(Difficulty difficulty) {
        super(true);
        
        rows = difficulty.getRows();
        cols = difficulty.getCols();
        mineCount = difficulty.getMines();

        setLayout(new GridLayout(rows, cols, 1, 1));
        setBackground(cellColor.getDarker());
        configComponentListener();

        initCells();
    }

// ------------------------------ Getters ------------------------------- //
    /**
     * Returns the number of rows in the game grid.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the game grid.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the current number of flagged cells in the game grid.
     *
     * @return the number of flagged cells
     */
    public int getFlagCount() {
        return flagCount;
    }

    /**
     * Returns the current number of pressed cells in the game grid.
     *
     * @return the number of pressed cells
     */
    public int getPressedCount() {
        return pressedCount;
    }
// ------------------------------ Setters ------------------------------- //

    /**
     * Sets the primary colour for all cells in the game grid. This method
     * updates the colour of each cell in the grid and changes the background
     * colour of the grid to a darker shade of the provided colour.
     *
     * @param cellColor the new {@link Color} to set for the cells
     */
    public void setCellColor(Color cellColor) {
        this.cellColor = new GameColor(cellColor);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].setColor(cellColor);
            }
        }

        setBackground(this.cellColor.getDarker());
    }

// ---------------------------- API Methods ----------------------------- //
    /**
     * Notifies the {@code GameGrid} of a cell state update. This method is
     * called when a cell's state changes, such as being clicked or flagged. It
     * handles the game logic associated with the cell's state change, including
     * mine placement on the first click, updating flag counts, and checking for
     * game over conditions.
     * <p>
     * If the cell is being flagged or unflagged, the flag count is updated
     * accordingly. If a cell is pressed and contains a mine, the game ends. If
     * the correct number of non-mine cells are pressed, the game is won.
     * </p>
     *
     * @param cell the {@link Cell} that has been updated
     * @param rightClick {@code true} if the state change was triggered by a
     * right-click, {@code false} otherwise
     */
    @Override
    public void notifyCellUpdate(Cell cell, boolean rightClick) {
        int preFlagCount = flagCount; // flagCount pre-update

        if (!firstClick) { // ensure user cannot lose with one click
            initMines(cell);
            firstClick = true;
        }

        if (rightClick) {
            cell.setState(cell.isFlagged() ? CellState.DEFAULT : CellState.FLAGGED);
            enforceFlagCounter(cell);
            cell.update();
            return; // exits method
        }

        // Handling a left click
        floodFill(cell);
        if (cell.hasMine()) {
            handleMineClicked();
        } else if (pressedCount == (rows * cols) - mineCount) {
            GameManager.showGameOver(true);
        } // Checks if game is won
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Toggles and updates the state of empty cells. If a cell is not pressed or
     * flagged, this method recursively updates neighbouring cells. It also
     * updates the visual representation of the cell based on the number of
     * neighbouring mines.
     *
     * @param cell the {@link Cell} to update
     */
    private void floodFill(Cell cell) {
        if (cell.isPressed()) {
            return; // prevent infinite recursion
        }

        if (cell.isFlagged()) {
            flagCount++;
        }

        if (cell.hasMine()) {
            cell.update();
            return;
        }

        cell.setState(CellState.PRESSED);
        pressedCount++;

        Cell[] neighbours = getNeighboursOf(cell);
        int mCount = countMines(neighbours);
        if (mCount == 0) {
            for (Cell neighbour : neighbours) {
                floodFill(neighbour); // recurses here
            }
        } else {
            cell.setText(String.valueOf(mCount));
        }

        cell.update();
    }

    /**
     * Counts the number of mines in the given array of cells.
     *
     * @param cells an array of {@link Cell} objects
     * @return the number of mines found in the provided cells
     */
    private int countMines(Cell[] cells) {
        int m = 0; // mine count
        for (Cell nCell : cells) {
            if (nCell.hasMine()) {
                m++;
            }
        }
        return m;
    }

    /**
     * Initialises mines in the grid, ensuring that the first clicked cell does
     * not contain a mine. Mines are randomly placed throughout the grid.
     *
     * @param cell the {@link Cell} that was first clicked, which should not
     * contain a mine
     */
    private void initMines(Cell cell) {
        int rr, rc;
        for (int i = 0; i < mineCount; i++) {
            // ensures the random index has not already been selected
            do {
                rr = current().nextInt(rows);
                rc = current().nextInt(cols);
            } while (grid[rr][rc] == cell || grid[rr][rc].hasMine());
            grid[rr][rc].setHasMine(true);
        }
    }

    /**
     * Initialises the cells in the grid, creating and adding each {@link Cell}
     * to the grid layout.
     */
    private void initCells() {
        grid = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = createCell();
                add(grid[r][c]);
            }
        }
    }

    /**
     * Creates a new {@link Cell} with the current grid's cell colour and font.
     *
     * @return a newly created {@link Cell}
     */
    private Cell createCell() {
        Cell cell = new Cell(cellColor);
        cell.setForeground(Palette.PRIMARY_2);
        
        cell.setFont(font);
        cell.setObserver(this);
        return cell;
    }

    /**
     * Retrieves the neighbouring cells of a given cell.
     *
     * @param cell the {@link Cell} whose neighbours are to be found
     * @return an array of neighbouring {@link Cell} objects
     */
    private Cell[] getNeighboursOf(Cell cell) {
        List<Cell> cells = new ArrayList<>(8);

        if (!cacheValid) {
            cw = getWidth() / cols;
            ch = getHeight() / rows;
            cacheValid = true;
        }

        int row = cell.getY() / ch;
        int col = cell.getX() / cw;

        for (int r = row - 1; r <= row + 1; r++) {
            if (r < 0 || r >= rows) {
                continue;
            }

            for (int c = col - 1; c <= col + 1; c++) {
                if ((r == row && c == col) || (c < 0 || c >= cols)) {
                    continue;
                }

                cells.add(grid[r][c]);
            }
        }

        return cells.toArray(Cell[]::new);
    }

    /**
     * Ensures the flag counter is accurately maintained. This method checks if
     * the flagging operation is valid and adjusts the flag count accordingly.
     *
     * @param cell the {@link Cell} being flagged or unflagged
     */
    private void enforceFlagCounter(Cell cell) {
        if (!cell.isFlagged()) { // Cell is being unflagged
            flagCount--;
            return;
        }

        if (flagCount + 1 > mineCount) { // undo flag
            cell.setState(CellState.DEFAULT);
            return;
        }

        flagCount++;
    }

    /**
     * Configures a component listener to handle events such as component
     * resizing. This listener is used to invalidate the cache values when the
     * grid is resized.
     */
    private void configComponentListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                cacheValid = false;
            }
        });
    }

    /**
     * Handles the event when a mine is clicked. This method reveals all mines
     * in the grid and triggers the game over logic.
     */
    private void handleMineClicked() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c].hasMine()) {
                    grid[r][c].setState(CellState.PRESSED);
                    grid[r][c].update();
                }
            }
        }

        repaint();
        GameManager.showGameOver(false);
    }

}