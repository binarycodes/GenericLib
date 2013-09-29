package in.binarycodes.test.util.generic;

import in.binarycodes.lib.util.generic.SpreadSheetUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

public class SpreadSheetUtilTest {

	private List<Employee> employeeList;
	private Workbook workbook;

	@Before
	public void setUp() throws Exception {
		employeeList = new ArrayList<Employee>();
		for (int i = 1; i <= 200; i++) {
			Employee emp = new Employee(i, "Sujoy K" + i);
			employeeList.add(emp);
		}
	}

	@Test
	public void testDoWork() {
		Map<Integer, String> allowList = new HashMap<Integer, String>() {
			private static final long serialVersionUID = -4643843988955990728L;
			{
				put(0, "employeeId");
			}
		};
		workbook = SpreadSheetUtil.prepareWorkbook("SAMPLE", employeeList,
				Employee.class, true, allowList);
		try {
			FileOutputStream out = SpreadSheetUtil.writeWorkBook(
					"/home/sujoy/randomtrash/ABC.xls", workbook);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
