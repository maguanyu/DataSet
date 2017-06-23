package datasets;

/**
 * 
 * @author magy
 *
 */
public class DataRow {

	/**
	 * 行データ
	 */
	private Object[] dataRow;

	/**
	 * この行が関連する DataTable
	 */
	private DataTable table;

	/**
	 * 変更フラグ
	 */
	private boolean isChanged = false;

	/**
	 * 削除フラグ
	 */
	private boolean isDeleted = false;

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
	 * 指定したインデックスの値を取得します。
	 * 
	 * @param fieldIndex
	 *            インデックス
	 * @return 値
	 */
	public Object getObject(int fieldIndex) {
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

		int index = table.getColumnIndex(fieldName);

		if (index < 0 || dataRow == null || table.getDataColumns().get(index).isReadOnly()) {
			return false;
		}
		dataRow[table.getColumnIndex(fieldName)] = object;
		isChanged = true;
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
		isChanged = true;
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
	 * isChangedを取得します。
	 *
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * isChangedを設定します
	 *
	 * @param isChanged
	 *            the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * isDeletedを取得します。
	 *
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * isDeletedを設定します
	 *
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

}
