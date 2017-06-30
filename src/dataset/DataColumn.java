package dataset;

import common.enums.FieldTypeEnum;

/**
 * 
 * @author magy
 *
 */
public class DataColumn {

	/**
	 * 列に格納されているデータの型
	 */
	private FieldTypeEnum DataType;

	/**
	 * 列名
	 */
	public String columnName;

	/**
	 * この列に null 値を格納できるかどうか
	 */
	private boolean AllowDBNull = false;

	/**
	 * 主キー
	 */
	private boolean primaryKey = false;

	/**
	 * 既定値
	 */
	private Object DefaultValue;

	/**
	 * 最大長
	 */
	private long MaxLength;

	/**
	 * 変更できるかどうか
	 */
	private boolean ReadOnly = false;

	/**
	 * 列が属する DataTable
	 */
	private DataTable table;

	/**
	 * 列の各行の値が一意である必要があるかどうか
	 */
	private boolean Unique = false;

	/**
	 * コンストラクタ
	 */
	public DataColumn() {

	}

	/**
	 * コンストラクタ
	 */
	public DataColumn(String name) {
		this.columnName = name;
	}

	/**
	 * uniqueを取得します。
	 *
	 * @return the unique
	 */
	public boolean isUnique() {
		return Unique;
	}

	/**
	 * uniqueを設定します
	 *
	 * @param unique
	 *            the unique to set
	 */
	public void setUnique(boolean unique) {
		Unique = unique;
	}

	/**
	 * dataTypeを取得します。
	 *
	 * @return the dataType
	 */
	public FieldTypeEnum getDataType() {
		return DataType;
	}

	/**
	 * dataTypeを設定します
	 *
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(FieldTypeEnum dataType) {
		DataType = dataType;
	}

	/**
	 * columnNameを取得します。
	 *
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * columnNameを設定します
	 *
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * allowDBNullを取得します。
	 *
	 * @return the allowDBNull
	 */
	public boolean isAllowDBNull() {
		return AllowDBNull;
	}

	/**
	 * allowDBNullを設定します
	 *
	 * @param allowDBNull
	 *            the allowDBNull to set
	 */
	public void setAllowDBNull(boolean allowDBNull) {
		AllowDBNull = allowDBNull;
	}

	/**
	 * primaryKeyを取得します。
	 *
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * primaryKeyを設定します
	 *
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * defaultValueを取得します。
	 *
	 * @return the defaultValue
	 */
	public Object getDefaultValue() {
		return DefaultValue;
	}

	/**
	 * defaultValueを設定します
	 *
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue) {
		DefaultValue = defaultValue;
	}

	/**
	 * maxLengthを取得します。
	 *
	 * @return the maxLength
	 */
	public long getMaxLength() {
		return MaxLength;
	}

	/**
	 * maxLengthを設定します
	 *
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(long maxLength) {
		MaxLength = maxLength;
	}

	/**
	 * readOnlyを取得します。
	 *
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return ReadOnly;
	}

	/**
	 * readOnlyを設定します
	 *
	 * @param readOnly
	 *            the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		ReadOnly = readOnly;
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

}
