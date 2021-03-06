package in.binarycodes.lib.util.generic;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BeanUtil {

	private BeanUtil() {
		super();
	}

	public static <T> List<Map<String, String>> beanPropertiesList(
			Class<T> clazz, List<T> beanList, boolean humanize) {
		List<Map<String, String>> propertiesMapList = new ArrayList<Map<String, String>>();
		if (ListUtil.hasData(beanList)) {
			for (T bean : beanList) {
				propertiesMapList.add(beanProperties(clazz, bean, humanize));
			}
		}
		return propertiesMapList;
	}

	public static <T> Map<String, String> beanProperties(Class<?> clazz, T obj,
			boolean humanize) {
		return beanProperties(clazz, obj, humanize, null);
	}

	private static <T> Map<String, String> beanProperties(Class<?> clazz,
			T obj, boolean humanize, String outerClassName) {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		if (obj != null && clazz != null) {
			try {
				PropertyDescriptor[] propertyDescriptors = Introspector
						.getBeanInfo(clazz, Object.class)
						.getPropertyDescriptors();
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					Method method = propertyDescriptor.getReadMethod();
					String name = propertyDescriptor.getName();
					String simpleClassName = obj.getClass().getSimpleName();
					simpleClassName = simpleClassName.substring(0, 1)
							.toLowerCase().concat(simpleClassName.substring(1));
					if (StringUtil.hasData(outerClassName)) {
						simpleClassName = outerClassName.concat(".").concat(
								simpleClassName);
					}
					Object value = method.invoke(obj, new Object[] {});
					propertiesMap.put(
							humanize ? StringUtil.splitCamelCase(name)
									: simpleClassName.concat(".").concat(name),
							value == null ? "" : value.toString());
					if (propertyDescriptor.getPropertyType().getPackage()
							.getImplementationVendor() == null) {
						propertiesMap.putAll(beanProperties(
								propertyDescriptor.getPropertyType(), value,
								humanize, simpleClassName));
					}
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return propertiesMap;
	}
}
