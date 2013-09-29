package in.binarycodes.test.util.generic;

import in.binarycodes.lib.util.generic.BeanUtil;
import in.binarycodes.lib.util.generic.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class BeanUtilTest {

	private Employee emp;
	private List<Employee> empList;

	@Before
	public void setUp() throws Exception {
		emp = new Employee(342161, "Sujoy Kumar Das");
		empList = new ArrayList<Employee>();
		for (int i = 0; i < 10; i++) {
			empList.add(new Employee(i, "Sujoy " + i));
		}

	}

	@Test
	public void testBeanPropertiesList() {
		List<Map<String, String>> propertiesMapList = BeanUtil
				.beanPropertiesList(Employee.class, empList);
		for (Map<String, String> map : propertiesMapList) {
			System.err.println(StringUtil.multiStringGenerator(80, "-"));
			for (Map.Entry<String, String> entrySet : map.entrySet()) {
				System.err.println(StringUtil.splitCamelCase(entrySet.getKey())
						+ " :: " + entrySet.getValue());
			}
			System.err.println(StringUtil.multiStringGenerator(80, "-"));
		}
	}

	@Test
	public void testBeanProperties() {
		Map<String, String> propertiesMap = BeanUtil.beanProperties(
				Employee.class, emp);
		for (Map.Entry<String, String> entrySet : propertiesMap.entrySet()) {
			System.err.println(StringUtil.splitCamelCase(entrySet.getKey())
					+ " :: " + entrySet.getValue());
		}
	}

}
