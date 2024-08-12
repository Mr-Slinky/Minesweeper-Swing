package minesweeper.gui.grid;

/**
 * The {@code Difficulty} enum represents the different difficulty levels
 * available in the Minesweeper game. Each difficulty level specifies the size
 * of the grid (rows and columns) and the number of mines.
 * <p>
 * The predefined difficulty levels are:
 * <ul>
 * <li>{@link #BEGINNER} - A 9x9 grid with 10 mines, suitable for new
 * players.</li>
 * <li>{@link #INTERMEDIATE} - A 16x16 grid with 40 mines, offering a moderate
 * challenge.</li>
 * <li>{@link #EXPERT} - A 30x16 grid with 99 mines, designed for experienced
 * players seeking a high level of difficulty.</li>
 * </ul>
 * </p>
 * <p>
 * The {@code Difficulty} enum provides methods to retrieve the number of rows,
 * columns, and mines for each level.
 * </p>
 *
 * @see minesweeper.gui.grid.Cell
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public enum Difficulty {
    
    /**
     * A beginner level with a 9x9 grid and 10 mines.
     */
    BEGINNER(9, 9, 10),
    
    /**
     * An intermediate level with a 16x16 grid and 40 mines.
     */
    INTERMEDIATE(16, 16, 40),
    
    /**
     * An expert level with a 30x16 grid and 99 mines.
     */
    EXPERT(30, 16, 99);

    private final int rows;
    private final int cols;
    private final int mines;

    /**
     * Constructs a {@code Difficulty} enum constant with the specified number
     * of rows, columns, and mines.
     *
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     * @param mines the number of mines in the grid
     */
    private Difficulty(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    /**
     * Returns the number of rows in the grid for this difficulty level.
     *
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the grid for this difficulty level.
     *
     * @return the number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the number of mines in the grid for this difficulty level.
     *
     * @return the number of mines
     */
    public int getMines() {
        return mines;
    }

}