package in.binarycodes.lib.util.generic;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public final class SpreadSheetUtil {

	private SpreadSheetUtil() {
		super();
	}

	public static FileOutputStream writeWorkBook(final String filePath,
			final Workbook workbook) throws IOException {
		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);
		return out;
	}

	public static <T> Workbook prepareWorkbook(final String sheetName,
			List<T> beanList, Class<T> clazz, boolean humanize,
			Map<Integer, String> columnHeaderMap) {
		List<Map<String, String>> beanPropertyList = BeanUtil
				.beanPropertiesList(clazz, beanList, humanize);
		if (humanize) {
			columnHeaderMap = beanPropertyNames(clazz, humanize,
					columnHeaderMap);
		}
		return prepareWorkbook(sheetName, beanPropertyList, columnHeaderMap);
	}

	public static <T> Workbook prepareWorkbook(final String sheetName,
			List<T> beanList, Class<T> clazz, boolean humanize) {
		List<Map<String, String>> beanPropertyList = BeanUtil
				.beanPropertiesList(clazz, beanList, humanize);
		Map<Integer, String> columnHeaderMap = new HashMap<Integer, String>();
		Set<String> propertyList = beanPropertyList.get(0).keySet();
		int cellStart = 0;
		for (String headerName : propertyList) {
			columnHeaderMap.put(cellStart++, headerName);
		}
		return prepareWorkbook(sheetName, beanPropertyList, columnHeaderMap);
	}

	public static Workbook prepareWorkbook(final String sheetName,
			List<Map<String, String>> headerValueMapList,
			Map<Integer, String> columnHeaderMap) {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, sheetName);
		Row row = null;
		Cell cell = null;
		// create 2 cell styles
		CellStyle dataCell = workbook.createCellStyle();
		dataCell.setBorderBottom(CellStyle.BORDER_THIN);
		dataCell.setBorderTop(CellStyle.BORDER_THIN);
		dataCell.setBorderRight(CellStyle.BORDER_THIN);
		dataCell.setBorderLeft(CellStyle.BORDER_THIN);

		CellStyle headerCell = workbook.createCellStyle();
		headerCell.setBorderBottom(CellStyle.BORDER_THIN);
		headerCell.setBorderTop(CellStyle.BORDER_THIN);
		headerCell.setBorderRight(CellStyle.BORDER_THIN);
		headerCell.setBorderLeft(CellStyle.BORDER_THIN);
		// create 2 fonts objects - arial is the default font
		Font regular = workbook.createFont();
		Font header = workbook.createFont();

		regular.setFontHeightInPoints((short) 10);
		regular.setColor((short) 0);
		regular.setBoldweight(Font.BOLDWEIGHT_NORMAL);

		header.setFontHeightInPoints((short) 12);
		header.setColor((short) Font.COLOR_NORMAL);
		header.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// set cell stlye
		dataCell.setFont(regular);

		// set a thin border
		headerCell.setBorderBottom(CellStyle.BORDER_THIN);
		headerCell.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerCell.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE
				.getIndex());
		// set the cell format to text see DataFormat for a full list
		headerCell.setDataFormat(HSSFDataFormat.getBuiltinFormat("text"));

		// set the font
		headerCell.setFont(header);

		for (Map<String, String> headerValueMap : headerValueMapList) {
			int rownum = headerValueMapList.indexOf(headerValueMap);
			row = sheet.createRow(rownum + 1);

			for (Map.Entry<Integer, String> columnHeaders : columnHeaderMap
					.entrySet()) {
				Integer cellnum = columnHeaders.getKey();
				cell = row.createCell(cellnum);
				cell.setCellValue(headerValueMap.get(columnHeaders.getValue()));
				cell.setCellStyle(dataCell);
			}
		}

		row = sheet.createRow(0);
		for (Map.Entry<Integer, String> columnHeaders : columnHeaderMap
				.entrySet()) {
			cell = row.createCell(columnHeaders.getKey());
			cell.setCellValue(columnHeaders.getValue());
			cell.setCellStyle(headerCell);
			sheet.autoSizeColumn(columnHeaders.getKey());
		}

		return workbook;
	}

	public static <T> Map<Integer, String> beanPropertyNames(Class<T> clazz,
			boolean humanize, Map<Integer, String> allowList) {
		Map<Integer, String> propertyNameMap = new HashMap<Integer, String>();
		if (clazz != null) {
			try {
				PropertyDescriptor[] propertyDescriptors = Introspector
						.getBeanInfo(clazz, Object.class)
						.getPropertyDescriptors();
				for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
					String name = propertyDescriptor.getName();
					for (Map.Entry<Integer, String> allow : allowList
							.entrySet()) {
						if (name.equals(allow.getValue())) {
							propertyNameMap.put(allow.getKey(),
									humanize ? StringUtil.splitCamelCase(name)
											: name);
						}
					}
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return propertyNameMap;
	}
}
