package datasets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * DataSet のクラスです
 * 
 * @author magy
 *
 */
public class DataSet {

	/**
	 * DataTableのマップ
	 */
	private Map<String, DataTable> dataTables;
	/**
	 * テーブル数
	 */
	private int tableCount = 0;

	/**
	 * コンストラクタ
	 */
	public DataSet() {

	}

	/**
	 * テーブル名を取得します
	 * 
	 * @return テーブル名のリスト
	 */
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

	/**
	 * 指定した テーブル名にある DataTable を削除します。
	 * 
	 * @param tableName
	 * @return true:削除する false：削除しない
	 */
	public boolean removeTableIfExists(String tableName) {

		if (dataTables == null || !dataTables.containsKey(tableName)) {
			return false;
		}

		dataTables.remove(tableName);
		tableCount--;
		return true;

	}

	/**
	 * テーブルのリストをします
	 * 
	 * @return テーブルのリスト
	 */
	public List<DataTable> getTables() {
		return new ArrayList<DataTable>(dataTables.values());
	}

	/**
	 * 指定した名前の DataTable 存在するかどうかを確認します。
	 * 
	 * @param tableName
	 * @return true:存在する false：存在しない
	 */
	public boolean containsTable(String tableName) {
		if (dataTables == null) {
			return false;
		}
		return dataTables.containsKey(tableName);
	}

	/**
	 * すべての DataTable を削除します。
	 */
	public void clearDataSet() {
		dataTables = new HashMap<>();
		tableCount = 0;
	}

	/**
	 * [dataTables]を取得する。
	 * 
	 * @return the dataTables
	 */
	public Map<String, DataTable> getDataTables() {
		return dataTables;
	}

	/**
	 * 
	 * [dataTables]を設定する。
	 * 
	 * @param dataTables
	 * 
	 */
	public void setDataTables(Map<String, DataTable> dataTables) {
		this.dataTables = dataTables;
	}

	/**
	 * [tableCount]を取得する。
	 * 
	 * @return the tableCount
	 */
	public int getTableCount() {
		return tableCount;
	}

	/**
	 * 
	 * [tableCount]を設定する。
	 * 
	 * @param tableCount
	 * 
	 */
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

}
