package minesweeper.util;

import java.awt.Color;

/**
 * The {@code Palette} class provides a set of predefined colours that can be
 * used throughout the application. These colours are intended to create a
 * consistent and visually appealing theme for the Minesweeper game. The class
 * includes both primary and secondary colours, as well as light and dark
 * shades.
 * <p>
 * Each colour is represented as a {@link java.awt.Color} object and is publicly
 * accessible as a static final field. The colour names and their hexadecimal
 * values are carefully chosen to convey specific hues and tones.
 * </p>
 * <p>
 * The colours included in this palette are:
 * <ul>
 * <li><b>PRIMARY_1:</b> Claret, a deep red colour.</li>
 * <li><b>PRIMARY_2:</b> Gamboge, a rich orange/yellow colour.</li>
 * <li><b>PRIMARY_3:</b> Prussian Blue, a dark royal blue colour.</li>
 * <li><b>LIGHT:</b> Timberwolf, a shiny silver colour.</li>
 * <li><b>DARK:</b> Rich black colour.</li>
 * </ul>
 * </p>
 * <p>
 * Usage example:
 * <pre>
 * Color backgroundColor = Palette.PRIMARY_1;
 * </pre>
 * </p>
 *
 * @author Kheagen Haskins
 * @version 1.0
 * @since 2024-07-23
 */
public class Palette {

    // ------------------------------ Fields -------------------------------- //
    /**
     * The {@code PRIMARY_1} colour is Claret, which is a deep red hue. This
     * colour can be used for primary elements in the application that require a
     * bold and striking appearance. The hexadecimal value for this colour is
     * {@code 0x840032}.
     */
    public static final Color PRIMARY_1 = new Color(0x840032);

    /**
     * The {@code PRIMARY_2} colour is Gamboge, a rich orange/yellow hue. It can
     * be used for elements that need to draw attention with a warm and vibrant
     * colour. The hexadecimal value for this colour is {@code 0xE59500}.
     */
    public static final Color PRIMARY_2 = new Color(0xE59500);

    /**
     * The {@code PRIMARY_3} colour is Prussian Blue, a dark royal blue hue.
     * This colour is suitable for elements that require a strong, elegant, and
     * professional look. The hexadecimal value for this colour is
     * {@code 0x002642}.
     */
    public static final Color PRIMARY_3 = new Color(0x002642);

    /**
     * The {@code LIGHT} colour is Timberwolf, which is a shiny silver hue. It
     * can be used for lighter elements or backgrounds that need a subtle and
     * sophisticated appearance. The hexadecimal value for this colour is
     * {@code 0xE5DADA}.
     */
    public static final Color LIGHT = new Color(0xE5DADA);

    /**
     * The {@code DARK} colour is Rich Black, a deep and intense black hue. This
     * colour is ideal for elements that need a strong contrast or a sleek and
     * modern look. The hexadecimal value for this colour is {@code 0x02040F}.
     */
    public static final Color DARK = new Color(0x02040F);

    /**
     * Determines whether black or white provides higher contrast against the
     * specified colour. The calculation is based on the luminance of the
     * colour, using appropriate weightings on its RGB components.
     *
     * @param color the colour against which to determine the higher contrast
     * @return {@link java.awt.Color#WHITE} if the luminance is less than 128,
     * otherwise {@link java.awt.Color#BLACK}
     */
    public static Color getHighContrastAgainst(Color color) {
        // Extract RGB components
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        // Calculate luminance using the formula
        // Luminance = 0.299*R + 0.587*G + 0.114*B
        double luminance = (0.299 * red) + (0.587 * green) + (0.114 * blue);

        // Return black or white based on luminance
        return luminance < 128 ? Color.WHITE : Color.BLACK;
    }

}
