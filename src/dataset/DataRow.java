package dataset;

import common.enums.DataStateEnum;
import common.util.DataSetUtil;

/**
 * 
 * @author magy
 *
 */
public class DataRow implements Cloneable {

	/**
	 * 行データ
	 */
	private Object[] dataRow;

	/**
	 * この行が関連する DataTable
	 */
	private DataTable table;

	/**
	 * 
	 */
	private DataStateEnum state;

	/**
	 * 
	 */
	private boolean hasError = false;

	/**
	 * コンストラクタ
	 */
	public DataRow() {

	}

	/**
	 * コンストラクタ
	 */
	public DataRow(Object[] row) {
		this.dataRow = row;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		DataRow newDataRow = (DataRow) super.clone();
		newDataRow.dataRow = (Object[]) dataRow.clone();

		return newDataRow;
	}

	/**
	 * 指定したインデックスの値を取得します。
	 * 
	 * @param fieldIndex
	 *            インデックス
	 * @return 値
	 */
	public Object getObject(int fieldIndex) {

		if (fieldIndex < 0) {
			return null;
		}

		return dataRow[fieldIndex];
	}

	/**
	 * 指定したコラム名の値を取得します。
	 * 
	 * @param fieldName
	 *            コラム名
	 * @return 値
	 */
	public Object getObject(String fieldName) {
		if (DataSetUtil.isEmpty(fieldName)) {
			return null;
		}

		return dataRow[table.getColumnIndex(fieldName)];
	}

	/**
	 * 指定したコラム名の値を設定します。
	 * 
	 * @param fieldName
	 *            コラム名
	 * @param object
	 *            値
	 * 
	 * @return 処理状態
	 */
	public boolean setObject(String fieldName, Object object) {
		if (DataSetUtil.isEmpty(fieldName) || object == null) {
			return false;
		}
		int index = table.getColumnIndex(fieldName);

		if (index < 0 || dataRow == null || table.getDataColumns().get(index).isReadOnly()) {
			return false;
		}
		dataRow[table.getColumnIndex(fieldName)] = object;
		setState(DataStateEnum.Modified);
		return true;

	}

	/**
	 * 指定したインデックスの値を設定します。
	 * 
	 * @param index
	 *            インデックス
	 * @param object
	 *            値
	 * 
	 * @return 処理状態
	 */
	public boolean setObject(int index, Object object) {
		if (index < 0 || dataRow == null || table.getDataColumns().get(index).isReadOnly()) {
			return false;
		}
		dataRow[index] = object;
		setState(DataStateEnum.Modified);
		return true;

	}

	/**
	 * dataRow を取得します。
	 * 
	 * @return dataRow
	 */
	public Object[] getDataRow() {
		return dataRow;
	}

	/**
	 * dataRow を設定します。
	 * 
	 * @param dataRow
	 */
	public void setDataRow(Object[] dataRow) {
		this.dataRow = dataRow;
	}

	/**
	 * tableを取得します。
	 *
	 * @return the table
	 */
	public DataTable getTable() {
		return table;
	}

	/**
	 * tableを設定します
	 *
	 * @param table
	 *            the table to set
	 */
	public void setTable(DataTable table) {
		this.table = table;
	}

	/**
	 * hasErrorを取得します。
	 *
	 * @return the hasError
	 */
	public boolean isHasError() {
		return hasError;
	}

	/**
	 * hasErrorを設定します
	 *
	 * @param hasError
	 *            the hasError to set
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * stateを取得します。
	 *
	 * @return the state
	 */
	public DataStateEnum getState() {
		return state;
	}

	/**
	 * stateを設定します
	 *
	 * @param state
	 *            the state to set
	 */
	public void setState(DataStateEnum state) {
		this.state = state;
	}

}
