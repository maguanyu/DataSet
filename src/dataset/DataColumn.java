package dataset;

import common.enums.FieldTypeEnum;

/**
 * 
 * @author magy
 *
 */
public class DataColumn {

	/**
	 * ��Ɋi�[����Ă���f�[�^�̌^
	 */
	private FieldTypeEnum DataType;

	/**
	 * ��
	 */
	public String columnName;

	/**
	 * ���̗�� null �l���i�[�ł��邩�ǂ���
	 */
	private boolean AllowDBNull = false;

	/**
	 * ��L�[
	 */
	private boolean primaryKey = false;

	/**
	 * ����l
	 */
	private Object DefaultValue;

	/**
	 * �ő咷
	 */
	private long MaxLength;

	/**
	 * �ύX�ł��邩�ǂ���
	 */
	private boolean ReadOnly = false;

	/**
	 * �񂪑����� DataTable
	 */
	private DataTable table;

	/**
	 * ��̊e�s�̒l����ӂł���K�v�����邩�ǂ���
	 */
	private boolean Unique = false;

	/**
	 * �R���X�g���N�^
	 */
	public DataColumn() {

	}

	/**
	 * �R���X�g���N�^
	 */
	public DataColumn(String name) {
		this.columnName = name;
	}

	/**
	 * unique���擾���܂��B
	 *
	 * @return the unique
	 */
	public boolean isUnique() {
		return Unique;
	}

	/**
	 * unique��ݒ肵�܂�
	 *
	 * @param unique
	 *            the unique to set
	 */
	public void setUnique(boolean unique) {
		Unique = unique;
	}

	/**
	 * dataType���擾���܂��B
	 *
	 * @return the dataType
	 */
	public FieldTypeEnum getDataType() {
		return DataType;
	}

	/**
	 * dataType��ݒ肵�܂�
	 *
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(FieldTypeEnum dataType) {
		DataType = dataType;
	}

	/**
	 * columnName���擾���܂��B
	 *
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * columnName��ݒ肵�܂�
	 *
	 * @param columnName
	 *            the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * allowDBNull���擾���܂��B
	 *
	 * @return the allowDBNull
	 */
	public boolean isAllowDBNull() {
		return AllowDBNull;
	}

	/**
	 * allowDBNull��ݒ肵�܂�
	 *
	 * @param allowDBNull
	 *            the allowDBNull to set
	 */
	public void setAllowDBNull(boolean allowDBNull) {
		AllowDBNull = allowDBNull;
	}

	/**
	 * primaryKey���擾���܂��B
	 *
	 * @return the primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * primaryKey��ݒ肵�܂�
	 *
	 * @param primaryKey
	 *            the primaryKey to set
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * defaultValue���擾���܂��B
	 *
	 * @return the defaultValue
	 */
	public Object getDefaultValue() {
		return DefaultValue;
	}

	/**
	 * defaultValue��ݒ肵�܂�
	 *
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue) {
		DefaultValue = defaultValue;
	}

	/**
	 * maxLength���擾���܂��B
	 *
	 * @return the maxLength
	 */
	public long getMaxLength() {
		return MaxLength;
	}

	/**
	 * maxLength��ݒ肵�܂�
	 *
	 * @param maxLength
	 *            the maxLength to set
	 */
	public void setMaxLength(long maxLength) {
		MaxLength = maxLength;
	}

	/**
	 * readOnly���擾���܂��B
	 *
	 * @return the readOnly
	 */
	public boolean isReadOnly() {
		return ReadOnly;
	}

	/**
	 * readOnly��ݒ肵�܂�
	 *
	 * @param readOnly
	 *            the readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		ReadOnly = readOnly;
	}

	/**
	 * table���擾���܂��B
	 *
	 * @return the table
	 */
	public DataTable getTable() {
		return table;
	}

	/**
	 * table��ݒ肵�܂�
	 *
	 * @param table
	 *            the table to set
	 */
	public void setTable(DataTable table) {
		this.table = table;
	}

}
