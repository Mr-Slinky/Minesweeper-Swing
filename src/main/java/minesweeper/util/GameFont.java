package minesweeper.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/**
 * The {@code GameFont} enum is responsible for managing custom fonts used in
 * the application. Each enum constant represents a different font that can be
 * loaded and used throughout the application.
 * <p>
 * This class provides methods to retrieve and register custom fonts from
 * resource files located in the {@code /fonts/} directory.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * Font myFont = GameFont.DAYDREAM.getFont();
 * </pre>
 * </p>
 * <p>
 * It also defines a default font, {@code DEFAUL_FONT}, which can be used as a
 * fallback or standard font within the application.
 * </p>
 * <p>
 * Note: Ensure the font files are properly placed in the specified resource
 * path for the fonts to be loaded correctly.
 * </p>
 *
 * @see java.awt.Font
 * @see java.awt.GraphicsEnvironment
 * @see java.io.InputStream
 * @see java.lang.IllegalArgumentException
 * @see java.awt.FontFormatException
 * @see java.io.IOException
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Kheagen Haskins
 */
public enum GameFont {

    // ---------------------------- Constants ------------------------------- //
    /**
     * Enum constant for the "daydream" font.
     */
    DAYDREAM("daydream"),
    /**
     * Enum constant for the "kraash" font.
     */
    KRAASH("kraash"),
    /**
     * Enum constant for the "mariokart" font.
     */
    MARIO_KART("mariokart"),
    /**
     * Enum constant for the "namecat" font.
     */
    NAMECAT("namecat"),
    /**
     * Enum constant for the "rainyhearts" font.
     */
    RAINYHEARTS("rainyhearts"),
    /**
     * Enum constant for the "thickpixel" font.
     */
    THICK_PIXEL("thickpixel");

    // ------------------------------ Fields -------------------------------- //
    /**
     * The default font used in the application. It is derived from the
     * "rainyhearts" font, bold and size 15.
     */
    public static final Font DEFAUL_FONT = RAINYHEARTS.font.deriveFont(Font.BOLD, 15f);

    /**
     * The {@code Font} object associated with this enum constant.
     */
    private final Font font;

    // --------------------------- Constructors ----------------------------- //
    /**
     * Private constructor for the {@code FontManager} enum. Initializes the
     * {@code font} field by retrieving the font from the specified file name.
     *
     * @param filename the name of the font file (without the extension)
     */
    private GameFont(String filename) {
        font = retrieveFont("/fonts/" + filename + ".ttf");
    }

    // ------------------------------ Getters ------------------------------- //
    /**
     * Returns the {@code Font} associated with this enum constant.
     *
     * @return the {@code Font} object
     */
    public Font getFont() {
        return font;
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Retrieves and registers a font from the specified path.
     * <p>
     * This method attempts to load a font from the given resource path,
     * register it with the local graphics environment, and return the resulting
     * {@code Font} object.
     * </p>
     * <p>
     * If the font cannot be loaded due to a format issue, I/O error, or if the
     * resource is not found, an error message is printed to the standard error
     * stream and {@code null} is returned.
     * </p>
     *
     * @param path the resource path to the font file
     * @return the loaded {@code Font} object, or {@code null} if loading fails
     * @throws IllegalArgumentException if the font file is not found
     * @throws FontFormatException if the font file is not in the correct format
     * @throws IOException if an I/O error occurs during font loading
     */
    private static Font retrieveFont(String path) {
        try {
            InputStream in = GameFont.class.getResourceAsStream(path);
            if (in == null) {
                throw new IllegalArgumentException("Font file not found: " + path);
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, in);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
            return customFont;
        } catch (FontFormatException | IOException | IllegalArgumentException ex) {
            System.err.println("Could not load font " + path + "\t" + ex.getMessage());
            return null;
        }
    }

}