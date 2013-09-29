package in.binarycodes.lib.util.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class StringUtil {

	private StringUtil() {
		super();
	}

	public static String multiStringGenerator(int count, String string) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < count; i++) {
			buffer.append(string);
		}
		return buffer.toString();
	}

	public static String splitCamelCase(String string) {
		String result = null;
		if (hasData(string)) {
			result = string.trim().replaceAll(
					String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])",
							"(?<=[^A-Z])(?=[A-Z])",
							"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");

		}
		return capitalize(result);
	}

	public static String capitalize(String string) {
		String result = null;
		if (hasData(string)) {
			if (string.length() > 1) {
				result = string.substring(0, 1).toUpperCase()
						.concat(string.substring(1));
			} else {
				result = string.substring(0, 1).toUpperCase();
			}
		}
		return result;
	}

	public static boolean isStringNullOrEmpty(final String stringData) {
		return stringData == null || "".equalsIgnoreCase(stringData);
	}

	public static boolean isStringNullOrEmpty(final String... stringData) {
		boolean check = false;
		if (stringData != null) {
			check = true;
			for (int ii = 0; check && ii < stringData.length; ii++) {
				check = check && isStringNullOrEmpty(stringData[ii]);
				if (!check) {
					break;
				}
			}
		}
		return check;
	}

	public static boolean hasData(final String stringData) {
		return !isStringNullOrEmpty(stringData);
	}

	public static boolean hasData(final String... stringData) {
		boolean check = false;
		if (stringData != null) {
			check = true;
			for (int ii = 0; check && ii < stringData.length; ii++) {
				check = check && hasData(stringData[ii]);
				if (!check) {
					break;
				}
			}
		}
		return check;
	}

	public static List<Integer> stringToIntegerList(final String stringData,
			final String separator, final boolean isRegex) {
		List<Integer> listData = new ArrayList<Integer>();
		String mySeprator = isRegex ? separator : Pattern.quote(separator);
		if (StringUtil.hasData(stringData, mySeprator)) {
			String[] list = stringData.split(mySeprator);
			for (String string : list) {
				listData.add(Integer.valueOf(string));
			}
		}
		return listData;
	}
}
