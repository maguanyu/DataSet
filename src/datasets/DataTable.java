package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataTable {

	private List<DataColumn> dataColumns;

	private String tableName;

	private int rowNum;

	private int columnNum;

	private int cursorPosition;

	private List<DataRow> dataRows;

	public DataTable() {

	}

	public boolean next() {
		if (cursorPosition < dataRows.size()) {
			cursorPosition++;
			return true;
		}

		return false;
	}

	public DataRow getDataRow(int rowIndex) {
		if (dataRows == null || rowIndex < 0 || rowNum < rowIndex) {
			return null;
		}
		return dataRows.get(rowIndex);
	}

	public boolean addDataRow(DataRow dataRow) {
		if (dataRows == null) {
			return false;
		}
		return dataRows.add(dataRow);
	}

	public boolean insertAt(DataRow dataRow, int index) {
		if (dataRows == null) {
			return false;
		}

		dataRows.add(index, dataRow);
		return true;
	}

	public boolean removeDataRow(int index) {
		if (dataRows == null || index < 0 || rowNum < index) {
			return false;
		}
		dataRows.remove(index);
		return true;
	}

	public boolean clear() {
		if (dataRows == null) {
			return false;
		}
		dataRows.clear();
		rowNum = 0;
		return true;
	}

	public String getFieldName(int fieldIndex) {

		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}

		return dataColumns.get(fieldIndex).getColumnName();
	}

	public int getFieldIndex(String fieldName) {
		if (dataColumns == null || fieldName.isEmpty() || fieldName == null) {
			return -1;
		}

		for (DataColumn dataColumn : dataColumns) {
			if (dataColumn.getColumnName().equals(fieldName)) {
				return dataColumn.getIndex();
			}

		}
		return -1;
	}

	public Class<?> getFieldClass(int fieldIndex) {
		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}
		return dataColumns.get(fieldIndex).getDataType();
	}

	// public static DataTable importTxt(String filePath, itemDividerType
	// divider) {
	// File f = new File(filePath);
	// DataTable outTable = new DataTable();
	// if (!f.exists()) {
	// return outTable;
	// }
	//
	// String tbName = f.getName().toLowerCase().replace(".csv", "");
	// tbName = tbName.replace(".txt", "");
	// outTable.setTableName(tbName);
	//
	// HashMap[] nameTypes = getFieldNameType(filePath, divider);
	// HashMap<Integer, String> names = nameTypes[0];
	// HashMap<Integer, fieldType> types = nameTypes[1];
	// for (int i = 0; i < names.size(); i++) {
	// outTable.addField(names.get(i), types.get(i));
	// }
	//
	// try {
	// FileReader fr = new FileReader(filePath);
	// BufferedReader br = new BufferedReader(fr);
	// String readLines = br.readLine();
	//
	// String[] linesContent;
	// while ((readLines = br.readLine()) != null) {
	// outTable.addRecord();
	// linesContent = split(readLines, divider.toString());
	// for (int j = 0; j < linesContent.length; j++) {
	// switch (outTable.getField(j).getType()) {
	// case Integer:
	// outTable.getField(j).set(outTable.getRecordCount() - 1,
	// Integer.parseInt(linesContent[j].trim()));
	// break;
	// case Double:
	// outTable.getField(j).set(outTable.getRecordCount() - 1,
	// Double.parseDouble(linesContent[j].trim()));
	// break;
	// case String:
	// outTable.getField(j).set(outTable.getRecordCount() - 1, linesContent[j]);
	// break;
	// }
	// }
	// }
	//
	// br.close();
	// fr.close();
	// } catch (Exception e) {
	// Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, e);
	// }
	//
	// return outTable;
	// }

	public enum itemDividerType {

		Tab, Comma, Semicolon, Space;

		public static itemDividerType getType(String value) {
			switch (value) {
			case "\t":
			case "tab":
			case "Tab":
				return Tab;
			case ",":
			case "Comma":
			case "comma":
				return Comma;
			case ";":
			case "Semicolon":
			case "semicolon":
				return Semicolon;
			case " ":
			case "space":
			case "Space":
				return Space;
			}
			return null;
		}

		public String toString() {
			switch (this) {
			case Tab:
				return "\t";
			case Comma:
				return ",";
			case Semicolon:
				return ";";
			case Space:
				return "[ ]+";
			}
			return "\t";
		}
	}

	// public Object getObject(int fieldIndex) {
	// return dataRows.get(cursorPosition - 1).getDataRow()[fieldIndex];
	// }
	//
	// public Object getObject(String fieldName) {
	// return getObject(dataColumn.getFieldIndex(fieldName));
	// }

	public List<DataRow> getDataRows() {
		return dataRows;
	}

	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getRowNum() {
		return dataRows.size();
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getCursorPosition() {
		return cursorPosition;
	}

	public void setCursorPosition(int cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	public List<DataColumn> getDataColumns() {
		return dataColumns;
	}

	public void setDataColumns(List<DataColumn> dataColumns) {
		this.dataColumns = dataColumns;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

}
