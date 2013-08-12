package in.binarycodes.lib.util.generic;

public final class StringUtil {

	private StringUtil() {
		super();
	}

	public static boolean isStringNullOrEmpty(final String stringData) {
		return stringData == null || "".equalsIgnoreCase(stringData);
	}

	public static boolean stringHasData(final String stringData) {
		return !isStringNullOrEmpty(stringData);
	}
}
