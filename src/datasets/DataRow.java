package datasets;

/**
 * 
 * @author magy
 *
 */
public class DataRow {

	private Object[] dataRow;

	public DataRow() {

	}

	public Object getObject(int fieldIndex) {
		return dataRow[fieldIndex];
	}

	public Object[] getDataRow() {
		return dataRow;
	}

	public void setDataRow(Object[] dataRow) {
		this.dataRow = dataRow;
	}

}
