package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataTable {

	private DataColumn dataColumn;

	private List<DataRow> dataRows;

	public DataTable() {

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
