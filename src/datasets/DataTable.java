package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataTable {

	private DataColumn dataColumn;

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

	public Object getObject(int fieldIndex) {
		return dataRows.get(cursorPosition - 1).getDataRow()[fieldIndex];
	}

	public Object getObject(String fieldName) {
		return getObject(dataColumn.getFieldIndex(fieldName));
	}

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

}
