package minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import minesweeper.gui.grid.Difficulty;
import minesweeper.gui.grid.GameGrid;

/**
 * The {@code MainInterface} class serves as the main container panel for the
 * Minesweeper game. It integrates and manages all the components necessary for
 * the game's user interface, particularly the game grid. This class is
 * responsible for initializing the game grid based on the selected difficulty
 * and cell size, and provides methods for restarting the game.
 * <p>
 * The {@code MainInterface} ensures that the game components are properly laid
 * out and displayed, handling layout and component initialization tasks.
 * </p>
 *
 * @see minesweeper.gui.grid.GameGrid
 * @see minesweeper.gui.grid.Difficulty
 * @see javax.swing.JPanel
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public class MainInterface extends JPanel {

    /**
     * The minimum allowable size for each cell in the grid, ensuring that the
     * cells are not too small to interact with.
     */
    private static final int MINIMUM_CELL_SIZE = 20;

    /**
     * The {@link GameGrid} instance representing the Minesweeper grid.
     */
    private GameGrid grid;

    /**
     * The difficulty level of the game, determining the grid size and number of
     * mines.
     */
    private Difficulty difficulty;

    /**
     * Constructs the {@code MainInterface} with the specified difficulty level
     * and cell size. This constructor initializes the game grid and sets the
     * preferred size of the panel based on the number of rows and columns from
     * the difficulty level and the specified cell size.
     *
     * @param difficulty the {@link Difficulty} level of the game
     * @param cellSize the size of each cell in the grid, adjusted to not be
     * smaller than {@link #MINIMUM_CELL_SIZE}
     */
    public MainInterface(Difficulty difficulty, int cellSize) {
        super(new BorderLayout(), true);
        cellSize = Math.max(MINIMUM_CELL_SIZE, cellSize);

        this.difficulty = difficulty;

        int panelWidth = difficulty.getCols() * cellSize;
        int panelHeight = difficulty.getRows() * cellSize;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        initComponents();
    }

    /**
     * Returns the current game grid. This method provides access to the
     * {@link GameGrid} that represents the Minesweeper grid.
     *
     * @return the current {@link GameGrid}
     */
    public GameGrid getGameGrid() {
        return grid;
    }

    /**
     * Restarts the game by removing the current grid and creating a new one
     * with the same difficulty settings. This method resets the game state,
     * allowing the player to start a new game with the same configuration.
     */
    public void restart() {
        remove(grid);
        grid = new GameGrid(difficulty);
        revalidate();
        add(grid, BorderLayout.CENTER);
    }

    /**
     * Custom paint component method to handle additional graphics rendering.
     * This method can be overridden to add custom drawing to the main
     * interface.
     *
     * @param og the {@link Graphics} object used for drawing
     */
    @Override
    protected void paintComponent(Graphics og) {
        super.paintComponent(og);
        Graphics2D g = (Graphics2D) og;
        // Custom painting code can be added here
    }

    /**
     * Initializes the components of the main interface. This method sets up the
     * game grid and adds it to the panel.
     */
    private void initComponents() {
        grid = new GameGrid(difficulty);
        add(grid, BorderLayout.CENTER);
    }

}