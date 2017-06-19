package datasets;

import java.util.Map;

/**
 * 
 * @author magy
 *
 */
public class DataSet {

	private Map<String, DataTable> dataTableMap;

	public DataSet() {

	}

	public Map<String, DataTable> getDataTableMap() {
		return dataTableMap;
	}

	public void setDataTableMap(Map<String, DataTable> dataTableMap) {
		this.dataTableMap = dataTableMap;
	}

}
