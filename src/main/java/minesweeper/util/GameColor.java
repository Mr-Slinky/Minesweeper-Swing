package minesweeper.util;

import java.awt.Color;

/**
 * The {@code GameColor} class is a utility class for managing and manipulating colors in the Minesweeper game.
 * It extends the functionality of the {@link Color} class by providing additional properties for color
 * variations such as highlight, brighter, and darker shades.
 * <p>
 * This class is used to enhance the visual representation of game elements by providing consistent color
 * themes and variations.
 * </p>
 * 
 * @see java.awt.Color
 * 
 * @since 1.0
 * @version 1.0
 * 
 * @autor Kheagen Haskins
 */
public class GameColor {

    // ------------------------------ Fields -------------------------------- //

    /**
     * The base color used for this GameColor instance.
     */
    private Color color;

    /**
     * A highlight variation of the base color, used for emphasis.
     */
    private Color highlight;

    /**
     * A brighter version of the base color.
     */
    private Color brighter;

    /**
     * A darker version of the base color.
     */
    private Color darker;

    // --------------------------- Constructors ----------------------------- //

    /**
     * Constructs a new {@code GColor} object based on the specified {@link Color}.
     * This constructor initializes the base color and computes its brighter, darker,
     * and highlight variations.
     *
     * @param color the base {@link Color} for this GColor
     */
    public GameColor(Color color) {
        this.color = color;
        this.brighter = color.brighter();
        this.darker = color.darker();
        this.highlight = getHighlight(color);
    }

    // ------------------------------ Getters ------------------------------- //

    /**
     * Returns the base color of this GameColor instance.
     *
     * @return the base {@link Color}
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the highlight variation of the base color.
     *
     * @return the highlight {@link Color}
     */
    public Color getHighlight() {
        return highlight;
    }

    /**
     * Returns the brighter version of the base color.
     *
     * @return the brighter {@link Color}
     */
    public Color getBrighter() {
        return brighter;
    }

    /**
     * Returns the darker version of the base color.
     *
     * @return the darker {@link Color}
     */
    public Color getDarker() {
        return darker;
    }

    // -------------------------- Helper Methods ---------------------------- //

    /**
     * Calculates a highlight color based on the given base color.
     * This method increases the intensity of the red, green, and blue components
     * by a fixed amount, ensuring the resulting color is a lighter shade.
     *
     * @param c the base {@link Color}
     * @return the highlight {@link Color}
     */
    private Color getHighlight(Color c) {
        int i = 15; // intensity
        return new Color(
                Math.min(255, c.getRed() + i),
                Math.min(255, c.getGreen() + i),
                Math.min(255, c.getBlue() + i)
        );
    }
}