package datasets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * DataSet �̃N���X�ł�
 * 
 * @author magy
 *
 */
public class DataSet {

	/**
	 * DataTable�̃}�b�v
	 */
	private Map<String, DataTable> dataTables;
	/**
	 * �e�[�u����
	 */
	private int tableCount = 0;

	/**
	 * �R���X�g���N�^
	 */
	public DataSet() {

	}

	/**
	 * �e�[�u�������擾���܂�
	 * 
	 * @return �e�[�u�����̃��X�g
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
	 * �w�肵�� �e�[�u�����ɂ��� DataTable ���폜���܂��B
	 * 
	 * @param tableName
	 * @return true:�폜���� false�F�폜���Ȃ�
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
	 * �e�[�u���̃��X�g�����܂�
	 * 
	 * @return �e�[�u���̃��X�g
	 */
	public List<DataTable> getTables() {
		return new ArrayList<DataTable>(dataTables.values());
	}

	/**
	 * �w�肵�����O�� DataTable ���݂��邩�ǂ������m�F���܂��B
	 * 
	 * @param tableName
	 * @return true:���݂��� false�F���݂��Ȃ�
	 */
	public boolean containsTable(String tableName) {
		if (dataTables == null) {
			return false;
		}
		return dataTables.containsKey(tableName);
	}

	/**
	 * ���ׂĂ� DataTable ���폜���܂��B
	 */
	public void clearDataSet() {
		dataTables = new HashMap<>();
		tableCount = 0;
	}

	/**
	 * [dataTables]���擾����B
	 * 
	 * @return the dataTables
	 */
	public Map<String, DataTable> getDataTables() {
		return dataTables;
	}

	/**
	 * 
	 * [dataTables]��ݒ肷��B
	 * 
	 * @param dataTables
	 * 
	 */
	public void setDataTables(Map<String, DataTable> dataTables) {
		this.dataTables = dataTables;
	}

	/**
	 * [tableCount]���擾����B
	 * 
	 * @return the tableCount
	 */
	public int getTableCount() {
		return tableCount;
	}

	/**
	 * 
	 * [tableCount]��ݒ肷��B
	 * 
	 * @param tableCount
	 * 
	 */
	public void setTableCount(int tableCount) {
		this.tableCount = tableCount;
	}

}
