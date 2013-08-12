package in.binarycodes.test.util.date;

import in.binarycodes.lib.util.date.DateFormat;
import in.binarycodes.lib.util.date.DateUtil;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 4);

		Assert.assertEquals("04-Jan-2013",
				DateUtil.utilDatetoString(cal.getTime(), DateFormat.D_Mon_Y));
		Assert.assertEquals("2013-01-04",
				DateUtil.utilDatetoString(cal.getTime(), DateFormat.Y_MM_DD));
	}
}
