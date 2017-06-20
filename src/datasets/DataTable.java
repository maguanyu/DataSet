package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataTable {

	private DataColumn dataColumn;

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

		if (dataColumn == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}

		return dataColumn.getFieldName(fieldIndex);
	}

	public int getFieldIndex(String fieldName) {
		if (dataColumn == null || fieldName.isEmpty() || fieldName == null) {
			return -1;
		}
		return dataColumn.getFieldIndex(fieldName);
	}

	public Class<?> getFieldClass(int fieldIndex) {
		if (dataColumn == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}
		return dataColumn.getFieldClass(fieldIndex);
	}

	// public Object getObject(int fieldIndex) {
	// return dataRows.get(cursorPosition - 1).getDataRow()[fieldIndex];
	// }
	//
	// public Object getObject(String fieldName) {
	// return getObject(dataColumn.getFieldIndex(fieldName));
	// }

	public DataColumn getDataColumn() {
		return dataColumn;
	}

	public void setDataColumn(DataColumn dataColumn) {
		this.dataColumn = dataColumn;
	}

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
		return rowNum;
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

}
