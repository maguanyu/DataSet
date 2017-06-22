package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataColumn {

	private Class<?> DataType;
	private String columnName;
	private boolean AllowDBNull = false;
	private boolean primaryKey = false;
	private int index;
	private Object DefaultValue;
	private long MaxLength;
	private boolean ReadOnly = false;
	private DataTable table;
	private boolean Unique = false;

	public DataColumn() {

	}

	public Class<?> getDataType() {
		return DataType;
	}

	public void setDataType(Class<?> dataType) {
		DataType = dataType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isAllowDBNull() {
		return AllowDBNull;
	}

	public void setAllowDBNull(boolean allowDBNull) {
		AllowDBNull = allowDBNull;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Object getDefaultValue() {
		return DefaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		DefaultValue = defaultValue;
	}

	public long getMaxLength() {
		return MaxLength;
	}

	public void setMaxLength(long maxLength) {
		MaxLength = maxLength;
	}

	public boolean isReadOnly() {
		return ReadOnly;
	}

	public void setReadOnly(boolean readOnly) {
		ReadOnly = readOnly;
	}

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}

	public boolean isUnique() {
		return Unique;
	}

	public void setUnique(boolean unique) {
		Unique = unique;
	}

}
