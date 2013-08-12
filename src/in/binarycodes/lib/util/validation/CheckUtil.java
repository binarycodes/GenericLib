package in.binarycodes.lib.util.validation;

public final class CheckUtil {

	private CheckUtil() {
		super();
	}

	public static boolean isNumeric(String data) {
		return data.matches("-?\\d+(\\.\\d+)?");
	}
}
