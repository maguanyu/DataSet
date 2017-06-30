package dataset;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.enums.FieldTypeEnum;
import dataset.base.SqlCommandResolver;
import dataset.base.SqlParameter;

public class FromDB {

	/**
	 * �f�[�^�x�[�X�ڑ�
	 */
	private Connection conn = null;

	/**
	 * �f�[�^�x�[�X��
	 */
	private String catalog = null;

	/**
	 * �X�L�[�}
	 */
	private String schema = null;

	/**
	 * �R���X�g���N�^
	 */
	public FromDB(Connection conn, String catalog, String schema) {
		this.conn = conn;
		this.catalog = catalog;
		this.schema = schema;
	}

	/**
	 * DB�f�[�^���擾����
	 * 
	 * @param sql
	 *            �����pSQL
	 * @param par
	 *            �����p�p�����[�^
	 * @param tableName
	 *            �e�[�u����
	 * @return �擾����DB�f�[�^
	 * @throws Exception
	 */
	public DataTable getDataTable(String sql, SqlParameter[] par, String tableName) throws Exception {
		PreparedStatement ps = null;
		DataTable table = new DataTable();
		ResultSet rs = null;
		Object[] dataRowValue;
		int dataRowIndex = 0;
		SqlCommandResolver resolver = new SqlCommandResolver();
		List<String> primaryKeys = null;
		try {
			primaryKeys = getPrimaryKeys(tableName);
			// PK��ݒ肷��
			table.setPrimaryKeys(primaryKeys);

			ps = conn.prepareStatement(sql);
			resolver.addSqlParameter(ps, par);
			// SQL�����s
			rs = ps.executeQuery();
			// DB�f�[�^���擾����
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				DataRow dataRow = new DataRow();
				dataRowValue = new Object[rsmd.getColumnCount()];

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					String columnName = rsmd.getColumnName(i);
					if (0 == dataRowIndex) {
						String columnType = rsmd.getColumnTypeName(i);
						DataColumn column = new DataColumn();
						column.setColumnName(columnName);
						column.setDataType(FieldTypeEnum.getType(columnType));
						if (primaryKeys.contains(columnName)) {
							column.setPrimaryKey(true);
						} else {
							column.setPrimaryKey(false);
						}
						// �J������ݒ�
						table.addDataColumn(column);
					}
					dataRowValue[i - 1] = rs.getObject(columnName);
				}
				dataRow.setDataRow(dataRowValue);
				table.addDataRow(dataRow);
				dataRowIndex++;
			}
			table.setTableName(schema + ".\"" + tableName + "\"");
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				throw e;
			}
		}
		return table;
	}

	/**
	 * PK���擾����
	 * 
	 * @param tableName
	 *            �e�[�u����
	 * @return �擾����PK���X�g
	 * @throws Exception
	 */
	private List<String> getPrimaryKeys(String tableName) throws Exception {
		List<String> primaryKeys = new ArrayList<>();
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet ids = meta.getPrimaryKeys(catalog, schema, tableName);
			while (ids != null && ids.next()) {
				primaryKeys.add(ids.getString("COLUMN_NAME"));
			}
		} catch (Exception e) {
			throw e;
		}
		return primaryKeys;
	}

}
