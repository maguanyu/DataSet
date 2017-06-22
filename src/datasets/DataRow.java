package datasets;

/**
 * 
 * @author magy
 *
 */
public class DataRow {

	private Object[] dataRow;

	private DataTable table;

	private boolean isChanged = false;

	private boolean isDeleted = false;

	private boolean hasError = false;

	public DataRow() {

	}

	public Class<?> getType(Object object) {
		return object.getClass();
	}

	public Object getObject(int fieldIndex) {
		return dataRow[fieldIndex];
	}

	public Object getObject(String fieldName) {
		return dataRow[table.getFieldIndex(fieldName)];
	}

	public boolean setObject(String fieldName, Object object) {

		int index = table.getFieldIndex(fieldName);

		if (index < 0 || dataRow == null || table.getDataColumns().get(index).isReadOnly()) {
			return false;
		}
		dataRow[table.getFieldIndex(fieldName)] = object;
		isChanged = true;
		return true;

	}

	public Object[] getDataRow() {
		return dataRow;
	}

	public void setDataRow(Object[] dataRow) {
		this.dataRow = dataRow;
	}

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}

	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

}
