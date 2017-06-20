package datasets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author magy
 *
 */
public class DataSet {

	private Map<String, DataTable> dataTables;

	private int tableCount = 0;

	public DataSet() {

	}

	public List<String> getTableNames() {

		if (dataTables == null) {
			return null;
		}

		Set<String> keySet = dataTables.keySet();
		List<String> tableNames = new ArrayList<>();
		for (String string : keySet) {
			tableNames.add(string);
		}

		return tableNames;
	}

	public boolean removeTableIfExists(String tableName) {

		if (dataTables == null || !dataTables.containsKey(tableName)) {
			return false;
		}

		dataTables.remove(tableName);
		tableCount--;
		return true;

	}

	public List<DataTable> getTables() {
		return new ArrayList<DataTable>(dataTables.values());
	}

	public boolean containsTable(String tableName) {
		if (dataTables == null) {
			return false;
		}
		return dataTables.containsKey(tableName);
	}

	public void clearDataSet() {
		dataTables = new HashMap<>();
		tableCount = 0;
	}

	public Map<String, DataTable> getDataTableMap() {
		return dataTables;
	}

	public void setDataTableMap(Map<String, DataTable> dataTables) {
		this.dataTables = dataTables;
	}

	public int getTableCount() {
		return tableCount;
	}

	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

}
