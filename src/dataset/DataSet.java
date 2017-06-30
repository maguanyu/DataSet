package dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import common.enums.FieldTypeEnum;
import common.enums.ItemDividerEnum;
import common.util.DataSetUtil;

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

	private List<String> tableNames;

	/**
	 * コンストラクタ
	 */
	public DataSet() {
		dataTables = new LinkedHashMap<>();
		tableNames = new ArrayList<>();
	}

	/**
	 * 指定した テーブル名にある DataTable を削除します。
	 * 
	 * @param tableName
	 * @return true:削除する false：削除しない
	 */
	public boolean removeTableIfExists(String tableName) {

		if (DataSetUtil.isEmpty(tableName) || dataTables == null || !dataTables.containsKey(tableName)) {
			return false;
		}

		dataTables.remove(tableName);
		return true;

	}

	public boolean addTable(DataTable dataTable) {

		if (dataTable == null) {
			return false;
		}
		if (dataTables == null) {
			dataTables = new LinkedHashMap<>();
		}
		if (tableNames == null) {
			tableNames = new ArrayList<>();
		}

		dataTables.put(dataTable.tableName, dataTable);
		tableNames.add(dataTable.tableName);

		return true;
	}

	/**
	 * 指定した テーブル名にある DataTable を取得します。
	 * 
	 * @param tableName
	 * @return true:削除する false：削除しない
	 */
	public DataTable getTable(String tableName) {

		if (DataSetUtil.isEmpty(tableName) || dataTables == null || !dataTables.containsKey(tableName)) {
			return null;
		}

		return dataTables.get(tableName);

	}

	/**
	 * 指定した indexにある DataTable を取得します。
	 * 
	 * @param index
	 * @return true:削除する false：削除しない
	 */
	public DataTable getTable(int index) {

		if (dataTables == null || dataTables.size() < index || tableNames.size() < index) {
			return null;
		}

		return dataTables.get(tableNames.get(index));

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
		if (DataSetUtil.isEmpty(tableName) || dataTables == null) {
			return false;
		}
		return dataTables.containsKey(tableName);
	}

	/**
	 * すべての DataTable を削除します。
	 */
	public void clear() {
		dataTables.clear();
		tableNames.clear();
	}

	/**
	 * CSVファイルからDataTableを作成する。
	 * 
	 * @param filePath
	 *            ファイルパス
	 * 
	 * @return DataTable
	 */
	public void readCSV(String filePath) {
		readCSV(filePath, ItemDividerEnum.Comma);
	}

	/**
	 * CSVファイルからDataTableを作成する。
	 * 
	 * @param filePath
	 *            ファイルパス
	 * @param divider
	 *            ディバイダー
	 * 
	 * @return DataTable
	 */
	private void readCSV(String filePath, ItemDividerEnum divider) {
		if (!DataSetUtil.isFile(filePath)) {
			throw new RuntimeException("対象フォルダが不正です。");
		}
		File f = new File(filePath);
		DataTable outTable = new DataTable();

		String tbName = f.getName().replace(".csv", "");
		outTable.setTableName(tbName);

		List<DataColumn> dataColumns = new ArrayList<>();

		try {
			// FileReader fr = new FileReader(filePath);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String readLines = br.readLine();

			String[] linesContent = split(readLines, divider.toString());
			for (int i = 0; i < linesContent.length; i++) {

				DataColumn dataColumn = new DataColumn(linesContent[i].trim());
				dataColumns.add(dataColumn);
			}

			if ((readLines = br.readLine()) != null) {
				linesContent = split(readLines, divider.toString());
				DataRow dataRow = new DataRow(linesContent);
				outTable.addDataRow(dataRow);

				for (int i = 0; i < linesContent.length; i++) {

					try {
						Integer.parseInt(linesContent[i].trim());
						dataColumns.get(i).setDataType(FieldTypeEnum.Integer);
						continue;
					} catch (NumberFormatException numberFormatException) {
					}

					try {
						Double.parseDouble(linesContent[i].trim());
						dataColumns.get(i).setDataType(FieldTypeEnum.Double);

						continue;
					} catch (NumberFormatException numberFormatException) {
					}

					DataColumn dataColumn = dataColumns.get(i);

					dataColumn.setDataType(FieldTypeEnum.String);
					outTable.addDataColumn(dataColumn);
				}
			}

			while ((readLines = br.readLine()) != null) {

				linesContent = split(readLines, divider.toString());

				DataRow dataRow = new DataRow(linesContent);
				outTable.addDataRow(dataRow);
			}

			br.close();
			isr.close();
		} catch (Exception e) {
		}
		addTable(outTable);
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
	 * tableNamesを取得します。
	 *
	 * @return the tableNames
	 */
	public List<String> getTableNames() {
		return tableNames;
	}

	/**
	 * tableNamesを設定します
	 *
	 * @param tableNames
	 *            the tableNames to set
	 */
	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

	/**
	 * [tableCount]を取得する。
	 * 
	 * @return the tableCount
	 */
	public int getTableCount() {
		return dataTables.size();
	}

	/**
	 * 
	 * @param text
	 *            目標テキスト
	 * @param divider
	 *            ディバイダー
	 * 
	 * @return 結果
	 */
	private String[] split(String text, String divider) {
		String[] tokens = text.split(divider + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim().replace("\"", "");
		}
		return tokens;
	}

	/**
	 * ＣＳＶファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public List<File> writeCSV(String path) {
		List<File> files = new ArrayList<>();
		for (Entry<String, dataset.DataTable> entry : dataTables.entrySet()) {
			files.add(writeCSV(path, entry.getKey()));
		}
		return files;
	}

	/**
	 * ＣＳＶファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public File writeCSV(String path, String tableName) {
		return getTable(tableName).writeCSV(path);

	}

	/**
	 * XmlファイルからDataSetを作成する。
	 * 
	 * @param path
	 *            パス
	 */
	public void readXML(String path) {
		SAXReader reader = new SAXReader();
		try {
			if (!DataSetUtil.isFile(path)) {
				throw new RuntimeException("対象フォルダが不正です。");
			}
			File f = new File(path);

			reader.setEncoding("UTF-8");

			Document document = reader.read(f);
			Element root = document.getRootElement();
			Element table = root.elements().get(0);

			List<Element> columnEle = table.elements();
			DataTable dataTable = new DataTable(table.getName());

			for (Element element : columnEle) {

				DataColumn dataColumn = new DataColumn(element.getName());
				dataTable.addDataColumn(dataColumn);

			}

			for (Element element : root.elements()) {

				List<Element> rowEles = element.elements();
				Object[] row = new Object[rowEles.size()];
				for (int i = 0; i < rowEles.size(); i++) {
					Element ele = rowEles.get(i);
					row[i] = ele.getText();
				}

				DataRow dataRow = new DataRow(row);
				dataTable.addDataRow(dataRow);
			}

			addTable(dataTable);

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * XMLファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public List<File> writeXML(String path) {

		List<File> files = new ArrayList<>();
		for (Entry<String, dataset.DataTable> entry : dataTables.entrySet()) {
			files.add(writeXML(path, entry.getKey()));
		}
		return files;
	}

	/**
	 * XMLファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public File writeXML(String path, String tableName) {
		return getTable(tableName).writeXML(path);

	}
}
