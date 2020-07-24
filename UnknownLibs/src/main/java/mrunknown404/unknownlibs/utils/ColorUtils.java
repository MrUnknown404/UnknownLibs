package mrunknown404.unknownlibs.utils;

import org.lwjgl.util.Color;

/**A bunch of random color utilities
 * @since 1.0.0
 * @author -Unknown-
 */
public class ColorUtils {
	/**@since 1.0.0
	 * Adds color/formatting to the given string {@link ColorCodes}, {@link FormatCodes}
	 * @param str String to modify
	 * @return The given string with color/formatting
	 */
	public static String addColor(String str) {
		char[] b = str.toCharArray();
		for (int i = 0; i < b.length - 1; i++) {
			if (b[i] == '&' && getAllCodes().indexOf(b[i + 1]) > -1) {
				b[i] = '\u00A7';
			}
		}
		
		return new String(b);
	}
	
	/**@since 1.0.0
	 * @return A string containing all color/formatting codes
	 */
	public static String getAllCodes() {
		return ColorCodes.getAllColorCodes() + FormatCodes.getAllFormatCodes();
	}
	
	/**@since 1.0.0
	 * @return A string containing all rainbow color codes in order
	 */
	public static String getRainbowCodes() {
		return "4c6e2ab319d5";
	}
	
	/**@since 1.0.0
	 * Checks if the given string is a valid hex color
	 * @param hexColor String to check for valid hex color
	 * @return true if the given string is valid hex color, otherwise false
	 */
	public static boolean isValidHexColor(String hexColor) {
		if (!hexColor.startsWith("#")) {
			return false;
		}
		
		hexColor = hexColor.substring(1);
		if (hexColor.length() == 0 || (hexColor.charAt(0) != '-' && Character.digit(hexColor.charAt(0), 16) == -1)) {
			return false;
		}
		if (hexColor.length() == 1 && hexColor.charAt(0) == '-') {
			return false;
		}
		
		for (int i = 1; i < hexColor.length(); i++) {
			if (Character.digit(hexColor.charAt(i), 16) == -1) {
				return false;
			}
		}
		
		return true;
	}
	
	/**@since 1.0.0
	 * Checks & converts the given hex color to a {@link Color}
	 * @param colorStr
	 * @return If the provided hex color is valid, returns the given string converted into {@link Color}, otherwise returns black
	 */
	public static Color hex2Color(String colorStr) {
		if (!isValidHexColor(colorStr)) {
			return new Color();
		}
		
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
	}
	
	/**@since 1.0.5
	 * Converts the given RGBA integers to a single integer (Used for GUIs)
	 * @param r The red value (0-255)
	 * @param g The green value (0-255)
	 * @param b The blue value (0-255)
	 * @param a The alpha value (0-255)
	 * @return
	 */
	public static int rgbaToInt(int r, int g, int b, int a) {
		return (a << 24) + (r << 16) + (g << 8) + (b);
	}
	
	/**<pre> String code <br>
	 * DARK_RED    ("4")
	 * RED         ("c")
	 * GOLD        ("6")
	 * YELLOW      ("e")
	 * DARK_GREEN  ("2")
	 * GREEN       ("a")
	 * AQUA        ("b")
	 * DARK_AQUA   ("3")
	 * DARK_BLUE   ("1")
	 * BLUE        ("9")
	 * LIGHT_PURPLE("d")
	 * DARK_PURPLE ("5")
	 * WHITE       ("f")
	 * GRAY        ("7")
	 * DARK_GRAY   ("8")
	 * BLACK       ("9")
	 * </pre>
	 * @since 1.0.0 */
	public enum ColorCodes {
		/** Color Code : 4 */ DARK_RED    ("4"),
		/** Color Code : c */ RED         ("c"),
		/** Color Code : 6 */ GOLD        ("6"),
		/** Color Code : e */ YELLOW      ("e"),
		/** Color Code : 2 */ DARK_GREEN  ("2"),
		/** Color Code : a */ GREEN       ("a"),
		/** Color Code : b */ AQUA        ("b"),
		/** Color Code : 3 */ DARK_AQUA   ("3"),
		/** Color Code : 1 */ DARK_BLUE   ("1"),
		/** Color Code : 9 */ BLUE        ("9"),
		/** Color Code : d */ LIGHT_PURPLE("d"),
		/** Color Code : 5 */ DARK_PURPLE ("5"),
		/** Color Code : f */ WHITE       ("f"),
		/** Color Code : 7 */ GRAY        ("7"),
		/** Color Code : 8 */ DARK_GRAY   ("8"),
		/** Color Code : 9 */ BLACK       ("9");
		
		public final String code;
		
		private ColorCodes(String code) {
			this.code = code;
		}
		
		/**@since 1.0.0
		 * @return A string containing all color codes
		 */
		public static String getAllColorCodes() {
			StringBuilder sb = new StringBuilder();
			for (ColorCodes c : values()) {
				sb.append(c.code);
			}
			
			return sb.toString();
		}
	}
	
	
	
	/**<pre> String code <br>
	 * BOLD         ("l")
	 * STRIKETHROUGH("m")
	 * UNDERLINE    ("n")
	 * ITALIC       ("o")
	 * OBFUSCATED   ("k")
	 * RESET        ("r")
	 * </pre>
	 * @since 1.0.0 */
	public enum FormatCodes {
		/** Color Code : l */ BOLD         ("l"),
		/** Color Code : m */ STRIKETHROUGH("m"),
		/** Color Code : n */ UNDERLINE    ("n"),
		/** Color Code : o */ ITALIC       ("o"),
		/** Color Code : k */ OBFUSCATED   ("k"),
		/** Color Code : r */ RESET        ("r");
		
		public final String code;
		
		private FormatCodes(String code) {
			this.code = code;
		}
		
		/**@since 1.0.0
		 * @return A string containing all formatting codes
		 */
		public static String getAllFormatCodes() {
			StringBuilder sb = new StringBuilder();
			for (FormatCodes f : values()) {
				sb.append(f.code);
			}
			
			return sb.toString();
		}
	}
}
