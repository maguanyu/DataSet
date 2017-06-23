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
	 * DataColumn�̃��X�g
	 */
	public List<DataColumn> dataColumns;

	/**
	 * �e�[�u����
	 */
	public String tableName;

	/**
	 * �s����
	 */
	private int rowNum;

	/**
	 * �R��������
	 */
	private int columnNum;

	/**
	 * �J�[�\��
	 */
	private int cursorPosition;

	/**
	 * DataRow�̃��X�g
	 */
	private List<DataRow> dataRows;

	/**
	 * �R���X�g���N�^
	 */
	public DataTable() {
		dataRows = new ArrayList<>();
		dataColumns = new ArrayList<>();
	}

	/**
	 * ���̍s
	 * 
	 * @return true:���Ȃ�
	 */
	public boolean next() {
		if (cursorPosition < dataRows.size()) {
			cursorPosition++;
			return true;
		}

		return false;
	}

	/**
	 * �w�肵���C���f�b�N�X�ɂ��� DataRow ���擾���܂��B
	 * 
	 * @param rowIndex
	 *            �C���f�b�N�X
	 * @return �擾���� DataRow
	 */
	public DataRow getDataRow(int rowIndex) {
		if (dataRows == null || rowIndex < 0 || rowNum < rowIndex) {
			return null;
		}
		return dataRows.get(rowIndex);
	}

	/**
	 * DataRow��ǉ����܂��B
	 * 
	 * @param dataRow
	 * @return �������
	 */
	public boolean addDataRow(DataRow dataRow) {
		if (dataRows == null) {
			return false;
		}
		return dataRows.add(dataRow);
	}

	/**
	 * �w�肵���ʒu�ɐV�����s��}�����܂��B
	 * 
	 * @param dataRow
	 * @param index
	 *            �C���f�b�N�X
	 * @return �������
	 */
	public boolean insertAt(DataRow dataRow, int index) {
		if (dataRows == null) {
			return false;
		}

		dataRows.add(index, dataRow);
		return true;
	}

	/**
	 * �w�肵���ʒu�ɍs���폜���܂��B
	 * 
	 * @param index
	 *            �C���f�b�N�X
	 * @return �������
	 */
	public boolean removeDataRow(int index) {
		if (dataRows == null || index < 0 || rowNum < index) {
			return false;
		}
		dataRows.remove(index);
		return true;
	}

	/**
	 * ���ׂĂ̍s�̃R���N�V�������������܂��B
	 * 
	 * @return �������
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
	 * �R���������擾���܂��B
	 * 
	 * @param fieldIndex
	 *            �C���f�b�N�X
	 * @return �R������
	 */
	public String getColumnName(int fieldIndex) {

		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}

		return dataColumns.get(fieldIndex).getColumnName();
	}

	/**
	 * �R�����C���f�b�N�X���擾���܂��B
	 * 
	 * @param fieldName
	 *            �R������
	 * @return �C���f�b�N�X
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
	 * �w�肵���C���f�b�N�X�� Type ���擾���܂��B
	 * 
	 * @param fieldIndex
	 *            �C���f�b�N�X
	 * @return Type
	 */
	public Class<?> getFieldClass(int fieldIndex) {
		if (dataColumns == null || fieldIndex < 0 || columnNum < fieldIndex) {
			return null;
		}
		return dataColumns.get(fieldIndex).getDataType();
	}

	/**
	 * dataColumns���擾���܂��B
	 *
	 * @return the dataColumns
	 */
	public List<DataColumn> getDataColumns() {
		return dataColumns;
	}

	/**
	 * dataColumns��ݒ肵�܂�
	 *
	 * @param dataColumns
	 *            the dataColumns to set
	 */
	public void setDataColumns(List<DataColumn> dataColumns) {
		this.dataColumns = dataColumns;
	}

	/**
	 * tableName���擾���܂��B
	 *
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * tableName��ݒ肵�܂�
	 *
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * rowNum���擾���܂��B
	 *
	 * @return the rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * rowNum��ݒ肵�܂�
	 *
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * columnNum���擾���܂��B
	 *
	 * @return the columnNum
	 */
	public int getColumnNum() {
		return columnNum;
	}

	/**
	 * columnNum��ݒ肵�܂�
	 *
	 * @param columnNum
	 *            the columnNum to set
	 */
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	/**
	 * cursorPosition���擾���܂��B
	 *
	 * @return the cursorPosition
	 */
	public int getCursorPosition() {
		return cursorPosition;
	}

	/**
	 * cursorPosition��ݒ肵�܂�
	 *
	 * @param cursorPosition
	 *            the cursorPosition to set
	 */
	public void setCursorPosition(int cursorPosition) {
		this.cursorPosition = cursorPosition;
	}

	/**
	 * dataRows���擾���܂��B
	 *
	 * @return the dataRows
	 */
	public List<DataRow> getDataRows() {
		return dataRows;
	}

	/**
	 * dataRows��ݒ肵�܂�
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
