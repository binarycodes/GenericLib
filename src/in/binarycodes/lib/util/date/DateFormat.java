package in.binarycodes.lib.util.date;

import java.text.SimpleDateFormat;

public enum DateFormat {
	D_Mon_Y("dd-MMM-yyyy"), //
	Y_MM_DD("yyyy-MM-dd");

	private SimpleDateFormat dateFormat;

	private DateFormat(String format) {
		dateFormat = new SimpleDateFormat(format);
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

}
