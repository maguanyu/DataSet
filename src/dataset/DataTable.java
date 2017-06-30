package dataset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import common.enums.CompareSymbol;
import common.enums.FieldTypeEnum;
import common.enums.ItemDividerEnum;
import common.util.DataSetUtil;

/**
 * 
 * @author magy
 * 
 */
public class DataTable {
	/**
	 * DataColumnのリスト
	 */
	private List<DataColumn> dataColumns;

	/**
	 * テーブル名
	 */
	public String tableName;

	/**
	 * カーソル
	 */
	private int cursorPosition;

	/**
	 * DataRowのリスト
	 */
	private List<DataRow> dataRows;

	/**
	 * PK
	 */
	private List<String> primaryKeys;

	/**
	 * コンストラクタ
	 */
	public DataTable() {
		this.dataColumns = new ArrayList<DataColumn>();
		this.dataRows = new ArrayList<DataRow>();
	}

	/**
	 * コンストラクタ
	 */
	public DataTable(List<DataColumn> dataColumns, List<DataRow> dataRows) {
		this.dataColumns = dataColumns;
		this.dataRows = dataRows;
		this.dataColumns = new ArrayList<DataColumn>();
		this.dataRows = new ArrayList<DataRow>();
	}

	/**
	 * コンストラクタ
	 */
	public DataTable(String tableName) {

		this.tableName = tableName;
		this.dataColumns = new ArrayList<DataColumn>();
		this.dataRows = new ArrayList<DataRow>();
	}

	/**
	 * PKを取得
	 */
	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	/**
	 * PKをセット
	 */
	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	/**
	 * コンストラクタ
	 */
	public DataTable(List<String> primaryKeys, List<DataColumn> dataColumns, List<DataRow> dataRows) {
		this.primaryKeys = primaryKeys;
		this.dataColumns = dataColumns;
		this.dataRows = dataRows;
	}

	/**
	 * 次の行
	 * 
	 * @return true:問題なし
	 */
	public boolean next() {
		if (cursorPosition < dataRows.size()) {
			cursorPosition++;
			return true;
		}

		return false;
	}

	/**
	 * 指定したインデックスにある DataRow を取得します。
	 * 
	 * @param rowIndex
	 *            インデックス
	 * @return 取得した DataRow
	 */
	public DataRow getDataRow(int rowIndex) {
		if (dataRows == null || rowIndex < 0 || dataRows.size() < rowIndex) {
			return null;
		}
		return dataRows.get(rowIndex);
	}

	/**
	 * DataRowを追加します。
	 * 
	 * @param dataRow
	 * @return 処理状態
	 */
	public boolean addDataRow(DataRow dataRow) {
		if (dataRows == null) {
			return false;
		}
		dataRow.setTable(this);
		return dataRows.add(dataRow);
	}

	/**
	 * DataRowを追加します。
	 * 
	 * @param dataRow
	 * @return 処理状態
	 */
	public boolean addDataColumn(DataColumn dataColumn) {
		if (dataRows == null) {
			return false;
		}
		dataColumn.setTable(this);
		return dataColumns.add(dataColumn);
	}

	/**
	 * 指定した位置に新しい行を挿入します。
	 * 
	 * @param dataRow
	 * @param index
	 *            インデックス
	 * @return 処理状態
	 */
	public boolean insertAt(DataRow dataRow, int index) {
		if (dataRows == null) {
			return false;
		}

		dataRows.add(index, dataRow);
		return true;
	}

	/**
	 * 指定した位置に行を削除します。
	 * 
	 * @param index
	 *            インデックス
	 * @return 処理状態
	 */
	public boolean removeDataRow(int index) {
		if (dataRows == null || index < 0 || dataRows.size() < index) {
			return false;
		}
		dataRows.remove(index);
		return true;
	}

	/**
	 * すべての行のコレクションを消去します。
	 * 
	 * @return 処理状態
	 */
	public boolean clear() {
		if (dataRows == null) {
			return false;
		}
		dataRows.clear();

		return true;
	}

	/**
	 * コラム名を取得します。
	 * 
	 * @param fieldIndex
	 *            インデックス
	 * @return コラム名
	 */
	public String getColumnName(int fieldIndex) {

		if (dataColumns == null || fieldIndex < 0 || dataColumns.size() < fieldIndex) {
			return null;
		}

		return dataColumns.get(fieldIndex).getColumnName();
	}

	/**
	 * コラムインデックスを取得します。
	 * 
	 * @param fieldName
	 *            コラム名
	 * @return インデックス
	 */
	public int getColumnIndex(String fieldName) {

		int columnIndex = -1;

		if (dataColumns == null || fieldName.isEmpty() || fieldName == null) {
			return columnIndex;
		}

		for (int i = 0; i < dataColumns.size(); i++) {
			DataColumn dataColumn = dataColumns.get(i);
			if (dataColumn.getColumnName().equals(fieldName)) {
				return i;
			}
		}

		return columnIndex;
	}

	/**
	 * 指定したインデックスの Type を取得します。
	 * 
	 * @param fieldIndex
	 *            インデックス
	 * @return Type
	 */
	public FieldTypeEnum getFieldClass(int fieldIndex) {
		if (dataColumns == null || fieldIndex < 0 || dataColumns.size() < fieldIndex) {
			return null;
		}
		return dataColumns.get(fieldIndex).getDataType();
	}

	/**
	 * dataColumnsを取得します。
	 * 
	 * @return the dataColumns
	 */
	public List<DataColumn> getDataColumns() {
		return dataColumns;
	}

	/**
	 * dataColumnsを設定します
	 * 
	 * @param dataColumns
	 *            the dataColumns to set
	 */
	public void setDataColumns(List<DataColumn> dataColumns) {

		this.dataColumns = dataColumns;
	}

	/**
	 * tableNameを取得します。
	 * 
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * tableNameを設定します
	 * 
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * rowNumを取得します。
	 * 
	 * @return the rowNum
	 */
	public int getRowNum() {
		return dataRows.size();
	}

	/**
	 * columnNumを取得します。
	 * 
	 * @return the columnNum
	 */
	public int getColumnNum() {
		return dataColumns.size();
	}

	/**
	 * cursorPositionを取得します。
	 * 
	 * @return the cursorPosition
	 */
	public int getCursorPosition() {
		return cursorPosition;
	}

	/**
	 * cursorPositionを設定します
	 * 
	 * @param cursorPosition
	 *            the cursorPosition to set
	 */
	public void setCursorPosition(int cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	/**
	 * dataRowsを取得します。
	 * 
	 * @return the dataRows
	 */
	public List<DataRow> getDataRows() {
		return dataRows;
	}

	/**
	 * dataRowsを設定します
	 * 
	 * @param dataRows
	 *            the dataRows to set
	 */
	public void setDataRows(List<DataRow> dataRows) {
		this.dataRows = dataRows;
	}

	/**
	 * カラムプロパティ名配列を取得します。
	 * 
	 * @return colunmNames
	 */
	public String[] getColumnNames() {
		String[] colunmNames = new String[dataColumns.size()];
		for (int i = 0; i < dataColumns.size(); i++) {
			colunmNames[i] = dataColumns.get(i).getColumnName();
		}
		return colunmNames;
	}

	// public Object getObject(int fieldIndex) {
	// return dataRows.get(cursorPosition - 1).getDataRow()[fieldIndex];
	// }
	//
	// public Object getObject(String fieldName) {
	// return getObject(dataColumn.getFieldIndex(fieldName));
	// }
	/**
	 * 
	 * @param path
	 * @param header
	 * @param doubleFormate
	 * @param divider
	 * @throws IOException
	 */
	private File writeCSV(String path, String[] header, DecimalFormat doubleFormate, ItemDividerEnum divider) {
		if (header.length != dataColumns.size()) {
			return null;
		}
		File file = DataSetUtil.generateFile(path);

		try {
			PrintWriter out = new PrintWriter(file);
			out.println(toText(header, doubleFormate, divider));
			out.close();
		} catch (IOException e) {
		}
		return file;
	}

	/**
	 * 
	 * アウトプット
	 * 
	 * @param header
	 * @param doubleFormate
	 * @param divider
	 * @return
	 */
	private String toText(String[] header, DecimalFormat doubleFormate, ItemDividerEnum divider) {
		StringBuilder sb = new StringBuilder();
		String sep = System.getProperty("line.separator");

		// print the header
		for (int i = 0; i < header.length; i++) {
			if (i < header.length - 1) {
				sb.append(header[i] + divider.toString());
			} else {
				sb.append(header[i]);
			}

		}
		sb.append(sep);

		// fieldType[] types = new fieldType[columnNum];
		// for (int fieldIndex = 0; fieldIndex < columnNum; fieldIndex++) {
		// types[fieldIndex] = getField(fieldIndex).getType();
		// }
		// if (doubleFormate == null) {
		// doubleFormate = new DecimalFormat("#.###");
		// }

		for (int i = 0; i < dataRows.size(); i++) {

			Object[] objects = dataRows.get(i).getDataRow();

			for (int j = 0; j < objects.length; j++) {

				if (j < objects.length - 1) {
					sb.append(objects[j].toString() + divider.toString());
				} else {
					sb.append(objects[j].toString());
				}

			}
			sb.append(sep);
		}

		// for (int recordIndex = 0; recordIndex < columnNum; recordIndex++) {
		// for (int fieldIndex = 0; fieldIndex < columnNum; fieldIndex++) {
		// String item = "";
		// switch (types[fieldIndex]) {
		// case Integer:
		// item = this.getRecord(recordIndex).get(fieldIndex).toString();
		// break;
		// case Double:
		// item = doubleFormate
		// .format(Double.valueOf(this.getRecord(recordIndex).get(fieldIndex).toString().trim()));
		// break;
		// case String:
		// item = this.getRecord(recordIndex).get(fieldIndex).toString();
		// break;
		// }
		// sb.append(item + divider.toString());
		// }
		// sb.append(sep);
		// }

		return sb.toString();
	}

	/**
	 * ＣＳＶファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public File writeCSV(String path) {
		String fileName = DataSetUtil.patchPath(path) + tableName + ".csv";

		return writeCSV(fileName, null, ItemDividerEnum.Comma);
	}

	/**
	 * ＣＳＶファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	private File writeCSV(String path, DecimalFormat doubleFormate, ItemDividerEnum dividerType) {
		String[] header = new String[dataColumns.size()];
		for (int i = 0; i < dataColumns.size(); i++) {
			header[i] = dataColumns.get(i).getColumnName();
		}

		return writeCSV(path, header, doubleFormate, dividerType);
	}

	/**
	 * XMLファイルをアウトプットする。
	 * 
	 * @param path
	 *            パス
	 */
	public File writeXML(String path) {
		String fileName = DataSetUtil.patchPath(path) + tableName + ".xml";

		Document document = DocumentHelper.createDocument();

		Element root = document.addElement("dataset");
		for (DataRow dataRow : dataRows) {
			Element tableEle = root.addElement(tableName);

			Object[] row = dataRow.getDataRow();
			for (int i = 0; i < dataColumns.size(); i++) {
				DataColumn dataColumn = dataColumns.get(i);
				tableEle.addElement(dataColumn.getColumnName()).addText(row[i].toString());
			}
		}
		File file = DataSetUtil.generateFile(fileName);
		OutputFormat xmlFormat = new OutputFormat();
		xmlFormat.setEncoding("UTF-8");
		xmlFormat.setNewlines(true);
		xmlFormat.setIndent("  ");
		// FileWriter fileWriter;
		try {
			// fileWriter = new FileWriter(file);
			XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"), xmlFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public List<DataRow> selectByFilter(String filterStr) {
		List<String> bracketFilters = new ArrayList<>();
		filterStr = bracketStrHandle(bracketFilters, 0, filterStr);
		return totalHandler(filterStr, bracketFilters);
	}

	public List<DataRow> selectByFilter(String filterStr1, String filterStr2) {
		List<String> bracketFilters = new ArrayList<>();
		filterStr1 = bracketStrHandle(bracketFilters, 0, filterStr1);
		List<DataRow> result = totalHandler(filterStr1, bracketFilters);
		sortTotalHandler(filterStr2, result);
		return result;
	}

	private List<DataRow> selectByFilterHelper(String filterStr) {
		return selectByFilterHelper(filterStr, null);
	}

	private List<DataRow> selectByFilterHelper(String filterStr1, String filterStr2) {
		List<DataRow> resultRows = new ArrayList<>();

		if (getDataRows() == null) {
			return resultRows;
		}
		List<DataRow> dataRows = getDataRows();

		if (getDataColumns() == null) {
			return resultRows;
		}
		List<DataColumn> dataColumns = getDataColumns();

		filterStr1 = filterStr1.toLowerCase();
		String columnName = "";
		CompareSymbol compareSymbol = null;
		String columnFilter = "";
		if (filterStr1.contains(CompareSymbol.gt.getSymbol())) {
			columnName = filterStr1.split(CompareSymbol.gt.getSymbol())[0].trim();
			compareSymbol = CompareSymbol.gt;
			columnFilter = filterStr1.split(CompareSymbol.gt.getSymbol())[1].trim();
			if (columnFilter.contains("'")) {
				columnFilter = columnFilter.substring(1, columnFilter.length() - 1).trim();
			}
		} else if (filterStr1.contains(CompareSymbol.lt.getSymbol())) {
			columnName = filterStr1.split(CompareSymbol.lt.getSymbol())[0].trim();
			compareSymbol = CompareSymbol.lt;
			columnFilter = filterStr1.split(CompareSymbol.lt.getSymbol())[1].trim();
			if (columnFilter.contains("'")) {
				columnFilter = columnFilter.substring(1, columnFilter.length() - 1).trim();
			}
		} else if (filterStr1.contains(CompareSymbol.eq.getSymbol())) {
			columnName = filterStr1.split(CompareSymbol.eq.getSymbol())[0].trim();
			compareSymbol = CompareSymbol.eq;
			columnFilter = filterStr1.split(CompareSymbol.eq.getSymbol())[1].trim();
			if (columnFilter.contains("'")) {
				columnFilter = columnFilter.substring(1, columnFilter.length() - 1).trim();
			}
		} else if (filterStr1.contains(CompareSymbol.gteq.getSymbol())) {
			columnName = filterStr1.split(CompareSymbol.gteq.getSymbol())[0].trim();
			compareSymbol = CompareSymbol.gteq;
			columnFilter = filterStr1.split(CompareSymbol.gteq.getSymbol())[1].trim();
			if (columnFilter.contains("'")) {
				columnFilter = columnFilter.substring(1, columnFilter.length() - 1).trim();
			}
		} else if (filterStr1.contains(CompareSymbol.lteq.getSymbol())) {
			columnName = filterStr1.split(CompareSymbol.lteq.getSymbol())[0].trim();
			compareSymbol = CompareSymbol.lteq;
			columnFilter = filterStr1.split(CompareSymbol.lteq.getSymbol())[1].trim();
			if (columnFilter.contains("'")) {
				columnFilter = columnFilter.substring(1, columnFilter.length() - 1).trim();
			}
		}

		int columnIndex = getColumnIndex(dataColumns, columnName);

		for (DataRow dataRow : dataRows) {
			Object[] row = dataRow.getDataRow();
			if (isSatisfiedRow(row, compareSymbol, columnIndex, columnFilter)) {
				resultRows.add(dataRow);
			}
		}

		sortTotalHandler(filterStr2, resultRows);

		return resultRows;
	}

	private void sortTotalHandler(String filterStr2, List<DataRow> resultRows) {
		filterStr2 = filterStr2.toLowerCase();

		String sortColumn = "";
		if (filterStr2.contains(CompareSymbol.asc.getSymbol())) {
			sortColumn = filterStr2.substring(0, filterStr2.indexOf(CompareSymbol.asc.getSymbol())).trim();
			int sortColumnIndex = getColumnIndex(dataColumns, sortColumn);
			CompareSymbol sortType = CompareSymbol.asc;
			sortResultDataRow(resultRows, sortType, sortColumnIndex);
		} else if (filterStr2.contains(CompareSymbol.desc.getSymbol())) {
			sortColumn = filterStr2.substring(0, filterStr2.indexOf(CompareSymbol.desc.getSymbol())).trim();
			int sortColumnIndex = getColumnIndex(dataColumns, sortColumn);
			CompareSymbol sortType = CompareSymbol.desc;
			sortResultDataRow(resultRows, sortType, sortColumnIndex);
		}
	}

	private void sortResultDataRow(List<DataRow> resultRows, CompareSymbol sortType, final int sortColumnIndex) {
		if (sortType != CompareSymbol.asc && sortType != CompareSymbol.desc) {
			return;
		}
		Collections.sort(resultRows, new Comparator<DataRow>() {
			@Override
			public int compare(DataRow o1, DataRow o2) {
				Object sortedObject1 = o1.getDataRow()[sortColumnIndex];
				Object sortedObject2 = o2.getDataRow()[sortColumnIndex];
				if (sortedObject1 instanceof Byte || sortedObject1 instanceof Short || sortedObject1 instanceof Integer
						|| sortedObject1 instanceof Long || sortedObject1 instanceof Float
						|| sortedObject1 instanceof Double) {
					BigDecimal bd1 = new BigDecimal(sortedObject1.toString());
					BigDecimal bd2 = new BigDecimal(sortedObject2.toString());
					return bd1.compareTo(bd2);
				} else {
					return sortedObject1.toString().compareTo(sortedObject2.toString());
				}
			}

		});
		if (sortType == CompareSymbol.desc) {
			Collections.reverse(resultRows);
		}
	}

	private boolean isSatisfiedRow(Object[] row, CompareSymbol compareSymbol, int columnIndex, String columnFilter) {
		if (columnIndex < 0) {
			return false;
		}
		Object rowColumn = row[columnIndex];
		if (rowColumn instanceof Byte || rowColumn instanceof Short || rowColumn instanceof Integer
				|| rowColumn instanceof Long || rowColumn instanceof Float || rowColumn instanceof Double) {
			String numberRegex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
			if (!columnFilter.matches(numberRegex)) {
				return false;
			}
			BigDecimal columnFilterValue = new BigDecimal(columnFilter);
			BigDecimal rowColumnValue = new BigDecimal(rowColumn.toString());
			// greater than compare
			if (CompareSymbol.gt == compareSymbol) {
				if (columnFilterValue.compareTo(rowColumnValue) < 0) {
					return true;
				}
			}

			// less than compare
			if (CompareSymbol.lt == compareSymbol) {
				if (columnFilterValue.compareTo(rowColumnValue) > 0) {
					return true;
				}
			}

			// equal compare
			if (CompareSymbol.eq == compareSymbol) {
				if (columnFilterValue.compareTo(rowColumnValue) == 0) {
					return true;
				}
			}

			// greater than equal compare
			if (CompareSymbol.gteq == compareSymbol) {
				if (columnFilterValue.compareTo(rowColumnValue) == 0
						|| columnFilterValue.compareTo(rowColumnValue) < 0) {
					return true;
				}
			}

			// less than equal compare
			if (CompareSymbol.lteq == compareSymbol) {
				if (columnFilterValue.compareTo(rowColumnValue) == 0
						|| columnFilterValue.compareTo(rowColumnValue) > 0) {
					return true;
				}
			}
		} else {
			// greater than compare
			if (CompareSymbol.gt == compareSymbol) {
				if (columnFilter.compareTo(rowColumn.toString()) < 0) {
					return true;
				}
			}

			// less than compare
			if (CompareSymbol.lt == compareSymbol) {
				if (columnFilter.compareTo(rowColumn.toString()) > 0) {
					return true;
				}
			}

			// equal compare
			if (CompareSymbol.eq == compareSymbol) {
				if (columnFilter.equals(rowColumn.toString())) {
					return true;
				}
			}

			// greater than equal compare
			if (CompareSymbol.gteq == compareSymbol) {
				if (columnFilter.equals(rowColumn.toString()) || columnFilter.compareTo(rowColumn.toString()) < 0) {
					return true;
				}
			}

			// less than equal compare
			if (CompareSymbol.lteq == compareSymbol) {
				if (columnFilter.equals(rowColumn.toString()) || columnFilter.compareTo(rowColumn.toString()) > 0) {
					return true;
				}
			}
		}

		return false;
	}

	private int getColumnIndex(List<DataColumn> dataColumns, String columnName) {
		int columnIndex = -1;
		for (DataColumn dataColumn : dataColumns) {
			if (columnName.equals(dataColumn.getColumnName().toLowerCase())) {
				columnIndex = dataColumns.indexOf(dataColumn);
				break;
			}
		}
		return columnIndex;
	}

	private List<DataRow> totalHandler(String filterStr, List<String> bracketFilters) {
		List<String> andFilters = orHandler(filterStr);
		List<List<DataRow>> orResults = new ArrayList<>();
		for (String s : andFilters) {
			List<DataRow> orResult = andHandler(s, bracketFilters);
			orResults.add(orResult);
		}
		for (int i = 0; i < orResults.size() - 1; i++) {
			List<DataRow> preList = orResults.get(i);
			List<DataRow> suList = orResults.get(i + 1);
			preList.removeAll(suList);
			suList.addAll(preList);
			orResults.remove(i + 1);
			orResults.add(i + 1, suList);
		}
		return orResults.get(orResults.size() - 1);
	}

	private List<DataRow> andHandler(String andFilter, List<String> bracketFilters) {
		String[] singleStr = andFilter.split("AND");
		List<List<DataRow>> singleResults = new ArrayList<>();
		for (String s : singleStr) {
			s = s.trim();
			String regex = "\\{\\d+\\}";
			if (!s.matches(regex)) {
				List<DataRow> singleResult = this.selectByFilterHelper(s);
				singleResults.add(singleResult);
			} else {
				s = s.substring(1, s.length());
				s = s.substring(0, s.length() - 1);
				int bracketIndex = Integer.valueOf(s);
				List<DataRow> bracketResult = bracketHandler(bracketFilters.get(bracketIndex));
				singleResults.add(bracketResult);
			}
		}
		for (int i = 0; i < singleResults.size() - 1; i++) {
			List<DataRow> preList = singleResults.get(i);
			List<DataRow> suList = singleResults.get(i + 1);
			suList.retainAll(preList);
			singleResults.remove(i + 1);
			singleResults.add(i + 1, suList);
		}
		return singleResults.get(singleResults.size() - 1);
	}

	private List<DataRow> bracketHandler(String andFilter) {
		String[] andStrs = andFilter.split("OR");
		List<List<DataRow>> orResults = new ArrayList<>();
		for (String s : andStrs) {
			String[] singleStr = s.split("AND");
			orResults.add(singleHandler(singleStr));
		}
		for (int i = 0; i < orResults.size() - 1; i++) {
			List<DataRow> preList = orResults.get(i);
			List<DataRow> suList = orResults.get(i + 1);
			preList.removeAll(suList);
			suList.addAll(preList);
			orResults.remove(i + 1);
			orResults.add(i + 1, suList);
		}
		return orResults.get(orResults.size() - 1);
	}

	private List<DataRow> singleHandler(String[] singleStr) {
		List<List<DataRow>> singleResults = new ArrayList<>();
		for (String s : singleStr) {
			List<DataRow> singleResult = this.selectByFilterHelper(s);
			singleResults.add(singleResult);
		}
		for (int i = 0; i < singleResults.size() - 1; i++) {
			List<DataRow> preList = singleResults.get(i);
			List<DataRow> suList = singleResults.get(i + 1);
			suList.retainAll(preList);
			singleResults.remove(i + 1);
			singleResults.add(i + 1, suList);
		}
		return singleResults.get(singleResults.size() - 1);
	}

	private List<String> orHandler(String testStr) {
		List<String> andFilters = new ArrayList<>();
		String[] andStrs = testStr.split("OR");
		for (String s : andStrs) {
			andFilters.add(s);
		}
		return andFilters;
	}

	private String bracketStrHandle(List<String> bracketFilters, int count, String s) {
		if (s.indexOf("(") != -1) {
			int leftIndex = s.indexOf("(");
			int rightIndex = s.indexOf(")");
			String bracketInnerStr = s.substring(leftIndex + 1, rightIndex);
			String temp1 = s.substring(0, leftIndex - 1);
			String temp2 = s.substring(rightIndex + 1, s.length());
			s = temp1 + "{" + count + "}" + temp2;
			count++;
			bracketFilters.add(bracketInnerStr);
			s = bracketStrHandle(bracketFilters, count, s);
		}
		return s;
	}
}
