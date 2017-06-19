package datasets;

import java.util.List;

/**
 * 
 * @author magy
 *
 */
public class DataColumn {

	private List<Class<?>> types;
	private List<String> columnNames;

	public DataColumn() {

	}

	public String getFieldName(int fieldIndex) {
		return columnNames.get(fieldIndex);
	}

	public int getFieldIndex(String fieldName) {
		return columnNames.indexOf(fieldName);
	}

	public Class<?> getFieldClass(int fieldIndex) {
		return types.get(fieldIndex);
	}

	public List<Class<?>> getTypes() {
		return types;
	}

	public void setTypes(List<Class<?>> types) {
		this.types = types;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public int getFieldCount() {
		return columnNames.size();
	}

	public void addField(String fieldName, Class<?> fieldClass, int fieldIndex) {
		columnNames.add(fieldIndex, fieldName);
		types.add(fieldIndex, fieldClass);
	}

	public void addField(String fieldName, Class<?> fieldClass) {
		columnNames.add(fieldName);
		types.add(fieldClass);
	}

}
