package in.binarycodes.test.util.generic;

import in.binarycodes.lib.util.generic.ListUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUtilTest {
	private List<Integer> listData;

	@Before
	public void setUp() throws Exception {
		listData = new ArrayList<Integer>();
		listData.add(10);
		listData.add(20);
		listData.add(10);
		listData.add(0);
		listData.add(-10);
		listData.add(130);
	}

	@Test
	public void testDistinct() {
		List<Integer> uniqueList = ListUtil.distinct(listData);
		Assert.assertEquals(5, uniqueList.size());
		Assert.assertEquals(uniqueList.indexOf(10), uniqueList.lastIndexOf(10));
	}

	@Test
	public void testListToString() {
		Assert.assertEquals("10+|+20+|+10+|+0+|+-10+|+130",
				ListUtil.listToString(listData, "+|+"));
	}

	@Test
	public void testListToCSV() {
		Assert.assertEquals("10,20,10,0,-10,130", ListUtil.listToCSV(listData));
	}

}
