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
	 * �s�f�[�^
	 */
	private Object[] dataRow;

	/**
	 * ���̍s���֘A���� DataTable
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
	 * �R���X�g���N�^
	 */
	public DataRow() {

	}

	/**
	 * �R���X�g���N�^
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
	 * �w�肵���C���f�b�N�X�̒l���擾���܂��B
	 * 
	 * @param fieldIndex
	 *            �C���f�b�N�X
	 * @return �l
	 */
	public Object getObject(int fieldIndex) {

		if (fieldIndex < 0) {
			return null;
		}

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
		if (DataSetUtil.isEmpty(fieldName)) {
			return null;
		}

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
		setState(DataStateEnum.Modified);
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

	/**
	 * state���擾���܂��B
	 *
	 * @return the state
	 */
	public DataStateEnum getState() {
		return state;
	}

	/**
	 * state��ݒ肵�܂�
	 *
	 * @param state
	 *            the state to set
	 */
	public void setState(DataStateEnum state) {
		this.state = state;
	}

}
