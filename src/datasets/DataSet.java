package datasets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private Map<String, DataTable> dataTables = new HashMap<>();
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
	 * �e�[�u�������擾���܂��B
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
	 * �w�肵�� �e�[�u�����ɂ��� DataTable ���擾���܂��B
	 * 
	 * @param tableName
	 * @return true:�폜���� false�F�폜���Ȃ�
	 */
	public DataTable getTable(String tableName) {

		if (dataTables == null || !dataTables.containsKey(tableName)) {
			return null;
		}

		return dataTables.get(tableName);

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

	public DataTable importTxt(String filePath, itemDividerType divider) {
		File f = new File(filePath);
		DataTable outTable = new DataTable();
		if (!f.exists()) {
			return outTable;
		}

		String tbName = f.getName().toLowerCase().replace(".csv", "");
		tbName = tbName.replace(".txt", "");
		outTable.setTableName(tbName);

		HashMap[] nameTypes = getFieldNameType(filePath, divider);
		HashMap<Integer, String> names = nameTypes[0];
		HashMap<Integer, fieldType> types = nameTypes[1];
		List<DataColumn> dataColumns = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			// outTable.addField(names.get(i), types.get(i));

			DataColumn dataColumn = new DataColumn(names.get(i));
			dataColumn.setTable(outTable);
			dataColumns.add(dataColumn);
		}

		outTable.setDataColumns(dataColumns);
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String readLines = br.readLine();

			String[] linesContent;

			while ((readLines = br.readLine()) != null) {

				linesContent = split(readLines, divider.toString());

				DataRow dataRow = new DataRow();
				dataRow.setDataRow(linesContent);
				outTable.addDataRow(dataRow);
			}

			br.close();
			fr.close();
		} catch (Exception e) {
		}
		dataTables.put(tbName, outTable);
		return outTable;
	}

	private static HashMap<String, fieldType>[] getFieldNameType(String filePath, itemDividerType divider) {
		HashMap<Integer, String> names = new HashMap();
		HashMap<Integer, fieldType> types = new HashMap();

		try {
			FileReader fr = new FileReader(filePath);

			BufferedReader br = new BufferedReader(fr);
			String readLines = br.readLine();

			String[] headingsContent = split(readLines, divider.toString());
			for (int i = 0; i < headingsContent.length; i++) {
				// if (avoidColumns.contains(i)) {
				// continue;
				// }
				names.put(i, headingsContent[i].trim());
			}

			while ((readLines = br.readLine()) != null) {
				headingsContent = split(readLines, divider.toString());

				for (int i = 0; i < headingsContent.length; i++) {
					if (types.containsKey(i) && types.get(i) != fieldType.Double && types.get(i) != fieldType.String) {
						try { // Not supporting import int
							int t = Integer.parseInt(headingsContent[i].trim());
							types.put(i, fieldType.Integer);
							continue;
						} catch (NumberFormatException numberFormatException) {
						}
					} else if (!types.containsKey(i)) {
						try { // Not supporting import int
							int t = Integer.parseInt(headingsContent[i].trim());
							types.put(i, fieldType.Integer);
							continue;
						} catch (NumberFormatException numberFormatException) {
						}
					}

					if (types.containsKey(i) && types.get(i) != fieldType.String) {
						try {
							double t = Double.parseDouble(headingsContent[i].trim());
							types.put(i, fieldType.Double);
							continue;
						} catch (NumberFormatException numberFormatException) {
						}
					} else if (!types.containsKey(i)) {
						try {
							double t = Double.parseDouble(headingsContent[i].trim());
							types.put(i, fieldType.Double);
							continue;
						} catch (NumberFormatException numberFormatException) {
						}
					}

					types.put(i, fieldType.String);
				}
			}

			if (types.size() < names.size()) {
				for (int i = types.size() - 1; i < names.size(); i++) {
					types.put(i, fieldType.String);
				}
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, e);
		}

		HashMap[] out = new HashMap[2];

		out[0] = names;
		out[1] = types;
		return out;
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

	public enum fieldType {

		Integer, Double, String;

		public static fieldType getType(String value) {
			switch (value) {
			case "INTEGER":
			case "int":
			case "integer":
			case "Integer":
			case "INT":
				return Integer;
			case "FLOAT":
			case "float":
			case "REAL":
			case "real":
			case "DOUBLE":
			case "double":
			case "Double":
				return Double;
			default:
				return String;
			}
		}
	}

	/**
	 * 
	 * CSV �f�B�o�C�_�[
	 * 
	 * @author magy
	 *
	 */
	public enum itemDividerType {

		Tab, Comma, Semicolon, Space;

		public static itemDividerType getType(String value) {
			switch (value) {
			case "\t":
			case "tab":
			case "Tab":
				return Tab;
			case ",":
			case "Comma":
			case "comma":
				return Comma;
			case ";":
			case "Semicolon":
			case "semicolon":
				return Semicolon;
			case " ":
			case "space":
			case "Space":
				return Space;
			}
			return null;
		}

		public String toString() {
			switch (this) {
			case Tab:
				return "\t";
			case Comma:
				return ",";
			case Semicolon:
				return ";";
			case Space:
				return "[ ]+";
			}
			return "\t";
		}
	}

	public static String[] split(String in, String divider) {
		String[] tokens = in.split(divider + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim().replace("\"", "");
		}
		return tokens;
	}

	public void exportCSV(String path) {

		for (Entry<String, datasets.DataTable> entry : dataTables.entrySet()) {
			entry.getValue().exportTXT(path, null, itemDividerType.Comma);

		}
	}
}
