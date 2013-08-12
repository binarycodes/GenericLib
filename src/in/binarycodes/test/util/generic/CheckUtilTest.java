package in.binarycodes.test.util.generic;

import in.binarycodes.lib.util.validation.CheckUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsNumeric() {
		Assert.assertTrue(CheckUtil.isNumeric("345.34"));
		Assert.assertTrue(CheckUtil.isNumeric("-34.534"));
		Assert.assertTrue(CheckUtil.isNumeric("-34534"));
		Assert.assertTrue(CheckUtil.isNumeric("34534"));
		Assert.assertFalse(CheckUtil.isNumeric("3453sas4"));
	}

}
