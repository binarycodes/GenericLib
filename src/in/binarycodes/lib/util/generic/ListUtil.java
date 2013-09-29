package in.binarycodes.lib.util.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ListUtil {

	private ListUtil() {
		super();
	}

	public static <T1, T2> Map<T1, T2> zipListToMap(final List<T1> listKey,
			final List<T2> listValue) {
		Map<T1, T2> returnMap = new HashMap<T1, T2>();
		if (ListUtil.hasData(listKey)) {
			int keyLength = listKey.size();
			int valueLength = ListUtil.hasData(listValue) ? listValue.size()
					: 0;
			for (int ii = 0; ii < keyLength; ii++) {
				T2 data = ii < valueLength ? listValue.get(ii) : null;
				returnMap.put(listKey.get(ii), data);
			}
		}
		return returnMap;
	}

	public static <T> List<T> distinct(final List<T> listData) {
		final Set<T> hash = new HashSet<T>();
		hash.addAll(listData);
		return new ArrayList<T>(hash);
	}

	public static <T> String listToString(final List<T> listData,
			final String separator) {
		final String separatorString = StringUtil.hasData(separator) ? separator
				: "";
		final StringBuffer buffer = new StringBuffer();
		for (final T data : listData) {
			buffer.append(data.toString().concat(separatorString));
		}
		final int buffLength = buffer.length();
		return buffer.toString().substring(0,
				buffLength - separatorString.length());
	}

	public static <T> String listToCSV(final List<T> listData) {
		return listToString(listData, ",");
	}

	@SafeVarargs
	public static <T> List<T> makeList(T... data) {
		List<T> listData = new ArrayList<T>();
		if (data != null) {
			listData = Arrays.asList(data);
		}
		return listData;
	}

	public static <T> boolean equal(final List<T> listA, final List<T> listB) {
		boolean equal = false;
		boolean isListANull = listA == null;
		boolean isListBNull = listB == null;

		if (!isListANull && !isListBNull) {
			equal = listA.size() == listB.size();
			if (equal) {
				for (int ii = 0; ii < listA.size(); ii++) {
					equal = listA.get(ii).equals(listB.get(ii));
					if (!equal) {
						break;
					}
				}
			}
		} else {
			equal = isListANull == isListBNull;
		}

		return equal;
	}

	public static <T> boolean isListNullOrEmpty(final List<T> listData) {
		return listData == null || listData.size() == 0;
	}

	@SafeVarargs
	public static <T> boolean isListNullOrEmpty(final List<T>... listData) {
		boolean check = false;
		if (listData != null) {
			check = true;
			for (int ii = 0; check && ii < listData.length; ii++) {
				check = check && isListNullOrEmpty(listData[ii]);
				if (!check) {
					break;
				}
			}
		}
		return check;
	}

	public static <T> boolean hasData(final List<T> listData) {
		return !isListNullOrEmpty(listData);
	}

	@SafeVarargs
	public static <T> boolean hasData(final List<T>... listData) {
		boolean check = false;
		if (listData != null) {
			check = true;
			for (int ii = 0; check && ii < listData.length; ii++) {
				check = check && hasData(listData[ii]);
				if (!check) {
					break;
				}
			}
		}
		return check;
	}

}
