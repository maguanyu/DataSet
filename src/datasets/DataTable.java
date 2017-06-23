package datasets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import datasets.DataSet.itemDividerType;

/**
 * 
 * @author magy
 *
 */
public class DataTable {
	/**
	 * DataColumnのリスト
	 */
	public List<DataColumn> dataColumns;

	/**
	 * テーブル名
	 */
	public String tableName;

	/**
	 * 行総数
	 */
	private int rowNum;

	/**
	 * コラム総数
	 */
	private int columnNum;

	/**
	 * カーソル
	 */
	private int cursorPosition;

	/**
	 * DataRowのリスト
	 */
	private List<DataRow> dataRows;

	/**
	 * コンストラクタ
	 */
	public DataTable() {
		dataRows = new ArrayList<>();
		dataColumns = new ArrayList<>();
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
		if (dataRows == null || rowIndex < 0 || rowNum < rowIndex) {
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
		return dataRows.add(dataRow);
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
		if (dataRows == null || index < 0 || rowNum < index) {
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
		rowNum = 0;
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

		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
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
		if (dataColumns == null || fieldName.isEmpty() || fieldName == null) {
			return -1;
		}

		for (DataColumn dataColumn : dataColumns) {
			if (dataColumn.getColumnName().equals(fieldName)) {
				return dataColumn.getIndex();
			}

		}
		return -1;
	}

	/**
	 * 指定したインデックスの Type を取得します。
	 * 
	 * @param fieldIndex
	 *            インデックス
	 * @return Type
	 */
	public Class<?> getFieldClass(int fieldIndex) {
		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
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
		return rowNum;
	}

	/**
	 * rowNumを設定します
	 *
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * columnNumを取得します。
	 *
	 * @return the columnNum
	 */
	public int getColumnNum() {
		return columnNum;
	}

	/**
	 * columnNumを設定します
	 *
	 * @param columnNum
	 *            the columnNum to set
	 */
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
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

	// public Object getObject(int fieldIndex) {
	// return dataRows.get(cursorPosition - 1).getDataRow()[fieldIndex];
	// }
	//
	// public Object getObject(String fieldName) {
	// return getObject(dataColumn.getFieldIndex(fieldName));
	// }
	public void exportTXT(String path, String[] header, DecimalFormat doubleFormate, itemDividerType divider)
			throws IOException {
		if (header.length != dataColumns.size()) {
			System.err.println("Header length error in DataTable printing");
			return;
		}
		try {
			// create an print writer for writing to a file
			PrintWriter out = new PrintWriter(new FileWriter(path));

			// output to the exported file
			out.println(toText(header, doubleFormate, divider));

			// close the file (VERY IMPORTANT!)
			out.close();
		} catch (IOException e) {
			System.err.println("Error during exporting DataTable to txt file");
		}
	}

	public String toText(String[] header, DecimalFormat doubleFormate, itemDividerType divider) {
		StringBuilder sb = new StringBuilder();
		String sep = System.getProperty("line.separator");

		// print the header
		for (int i = 0; i < header.length; i++) {
			sb.append(header[i] + divider.toString());
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

			DataRow dataRow = dataRows.get(i);

			Object[] objects = dataRow.getDataRow();

			for (int j = 0; j < objects.length; j++) {
				sb.append(objects[j].toString() + divider.toString());
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

	public void exportTXT(String path, DecimalFormat doubleFormate, itemDividerType dividerType) {
		String[] header = new String[dataColumns.size()];
		for (int i = 0; i < dataColumns.size(); i++) {
			header[i] = dataColumns.get(i).getColumnName();
		}

		try {
			exportTXT(path, header, doubleFormate, dividerType);
		} catch (IOException ex) {
			Logger.getLogger(DataTable.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
