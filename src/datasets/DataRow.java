package datasets;

/**
 * 
 * @author magy
 *
 */
public class DataRow {

	/**
	 * �s�f�[�^
	 */
	private Object[] dataRow;

	/**
	 * ���̍s���֘A���� DataTable
	 */
	private DataTable table;

	/**
	 * �ύX�t���O
	 */
	private boolean isChanged = false;

	/**
	 * �폜�t���O
	 */
	private boolean isDeleted = false;

	/**
	 * 
	 */
	private boolean hasError = false;

	/**
	 * �R���X�g���N�^
	 */
	public DataRow() {

	}

	/**
	 * �w�肵���C���f�b�N�X�̒l���擾���܂��B
	 * 
	 * @param fieldIndex
	 *            �C���f�b�N�X
	 * @return �l
	 */
	public Object getObject(int fieldIndex) {
		return dataRow[fieldIndex];
	}

	/**
	 * �w�肵���R�������̒l���擾���܂��B
	 * 
	 * @param fieldName
	 *            �R������
	 * @return �l
	 */
	public Object getObject(String fieldName) {
		return dataRow[table.getColumnIndex(fieldName)];
	}

	/**
	 * �w�肵���R�������̒l��ݒ肵�܂��B
	 * 
	 * @param fieldName
	 *            �R������
	 * @param object
	 *            �l
	 * 
	 * @return �������
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
	 * �w�肵���C���f�b�N�X�̒l��ݒ肵�܂��B
	 * 
	 * @param index
	 *            �C���f�b�N�X
	 * @param object
	 *            �l
	 * 
	 * @return �������
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
	 * dataRow ���擾���܂��B
	 * 
	 * @return dataRow
	 */
	public Object[] getDataRow() {
		return dataRow;
	}

	/**
	 * dataRow ��ݒ肵�܂��B
	 * 
	 * @param dataRow
	 */
	public void setDataRow(Object[] dataRow) {
		this.dataRow = dataRow;
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

	/**
	 * isChanged���擾���܂��B
	 *
	 * @return the isChanged
	 */
	public boolean isChanged() {
		return isChanged;
	}

	/**
	 * isChanged��ݒ肵�܂�
	 *
	 * @param isChanged
	 *            the isChanged to set
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	/**
	 * isDeleted���擾���܂��B
	 *
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * isDeleted��ݒ肵�܂�
	 *
	 * @param isDeleted
	 *            the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * hasError���擾���܂��B
	 *
	 * @return the hasError
	 */
	public boolean isHasError() {
		return hasError;
	}

	/**
	 * hasError��ݒ肵�܂�
	 *
	 * @param hasError
	 *            the hasError to set
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

}
