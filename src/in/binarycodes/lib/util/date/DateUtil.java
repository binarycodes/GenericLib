package in.binarycodes.lib.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private DateUtil() {
		super();
	}

	public static String utilDatetoString(Date date, DateFormat format) {
		return format.getDateFormat().format(date);
	}

	public static String utilDatetoString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static Date stringToUtilDate(String stringDate, DateFormat format) {
		Date returnDate;
		try {
			returnDate = format.getDateFormat().parse(stringDate);
		} catch (ParseException pe) {
			returnDate = null;
		}
		return returnDate;
	}

	public static Date stringToUtilDate(String stringDate, String format) {
		Date returnDate;
		try {
			returnDate = new SimpleDateFormat(format).parse(stringDate);
		} catch (ParseException pe) {
			returnDate = null;
		}
		return returnDate;
	}

	public static Date combineDateTime(Date date, Date time) {
		Calendar dateCal = Calendar.getInstance();
		dateCal.clear();
		dateCal.setTime(date);

		Calendar timeCal = Calendar.getInstance();
		dateCal.clear();
		dateCal.setTime(time);

		dateCal.set(Calendar.HOUR, timeCal.get(Calendar.HOUR));
		dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
		dateCal.set(Calendar.AM_PM, timeCal.get(Calendar.AM_PM));
		dateCal.set(Calendar.MILLISECOND, timeCal.get(Calendar.MILLISECOND));

		return dateCal.getTime();
	}
}
