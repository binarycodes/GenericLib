package in.binarycodes.test.util.generic;

import in.binarycodes.lib.util.generic.StringUtil;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCamelCaseToPrintable() {
		Assert.assertEquals("Employee Name",
				StringUtil.splitCamelCase("employeeName"));
	}

	@Test
	public void testStringToIntegerList() {
		List<Integer> dataList = StringUtil.stringToIntegerList(
				"10+|+20+|+10+|+0+|+-10+|+130", "+|+", false);
		Assert.assertEquals(10, dataList.get(0).intValue());
		Assert.assertEquals(20, dataList.get(1).intValue());
		Assert.assertEquals(10, dataList.get(2).intValue());
		Assert.assertEquals(0, dataList.get(3).intValue());
		Assert.assertEquals(-10, dataList.get(4).intValue());
		Assert.assertEquals(130, dataList.get(5).intValue());
	}

}
