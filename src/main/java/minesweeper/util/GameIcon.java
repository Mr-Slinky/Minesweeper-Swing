package minesweeper.util;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * The {@code GameIcon} enum is responsible for managing icons used in the
 * Minesweeper application. Each enum constant represents a different icon that
 * can be loaded and used throughout the application.
 * <p>
 * This class provides methods to retrieve icons from resource files located in
 * the {@code /icons/} directory.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * ImageIcon flagIcon = GameIcon.FLAG_32.getIcon();
 * </pre>
 * </p>
 * <p>
 * Note: Ensure the icon files are properly placed in the specified resource
 * path for the icons to be loaded correctly.
 * </p>
 *
 * @see javax.swing.ImageIcon
 * @see java.net.URL
 *
 * @since 1.0
 * @version 1.0
 *
 * @autor Kheagen Haskins
 */
public enum GameIcon {

    // ---------------------------- Constants ------------------------------- //
    /**
     * Enum constant for the 32x32 flag icon.
     */
    FLAG_32("flag-32"),
    /**
     * Enum constant for the 16x16 flag icon.
     */
    FLAG_16("flag-16"),
    /**
     * Enum constant for the 32x32 mine icon.
     */
    MINE_32("mine-32"),
    /**
     * Enum constant for the 16x16 mine icon.
     */
    MINE_16("mine-16");

    // ------------------------------ Fields -------------------------------- //
    /**
     * The {@code ImageIcon} object associated with this enum constant.
     */
    private final ImageIcon icon;

    // --------------------------- Constructors ----------------------------- //
    /**
     * Private constructor for the {@code IconManager} enum. Initializes the
     * {@code icon} field by retrieving the icon from the specified file name.
     *
     * @param filename the name of the icon file (without the extension)
     */
    private GameIcon(String filename) {
        this.icon = getIconFromPath("/icons/" + filename + ".png");
    }

    // ------------------------------ Getters ------------------------------- //
    /**
     * Returns the {@code ImageIcon} associated with this enum constant.
     *
     * @return the {@code ImageIcon} object
     */
    public ImageIcon getIcon() {
        return icon;
    }

    // -------------------------- Helper Methods ---------------------------- //
    /**
     * Retrieves an icon from the specified path.
     * <p>
     * This method attempts to load an icon from the given resource path and
     * returns the resulting {@code ImageIcon} object.
     * </p>
     * <p>
     * If the icon cannot be loaded because the resource is not found, an
     * {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @param path the resource path to the icon file
     * @return the loaded {@code ImageIcon} object
     * @throws IllegalArgumentException if the icon file is not found
     */
    private ImageIcon getIconFromPath(String path) {
        URL url = GameIcon.class.getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Invalid icon path: " + path);
        }

        return new ImageIcon(url);
    }

}
