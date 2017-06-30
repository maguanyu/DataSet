package dataset.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.enums.DataStateEnum;
import common.enums.FieldTypeEnum;
import dataset.DataColumn;
import dataset.DataRow;
import dataset.DataTable;

public class SqlCommandResolver {

	/**
	 * 追加コマンドを取得する
	 * 
	 * @param dataTable
	 *            データテーブル
	 * @return 追加コマンド
	 */
	public List<SqlCommand> getInsertCommand(DataTable dataTable) {
		if (null == dataTable) {
			return null;
		}
		List<DataRow> dataRows = dataTable.getDataRows();
		if (null == dataRows || 0 == dataRows.size()) {
			return null;
		}
		String[] propNames = dataTable.getColumnNames();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(dataTable.getTableName());
		sql.append(" (");
		sql.append(getPropertyColumns(propNames));
		sql.append(") values (");
		sql.append(getPropertyPlaceHolders(propNames));
		sql.append(")");
		String sqlText = sql.toString();

		List<SqlCommand> commands = new ArrayList<SqlCommand>();
		for (DataRow dataRow : dataTable.getDataRows()) {
			if (DataStateEnum.Added.equals(dataRow.getState())) {
				commands.add(
						new SqlCommand(sqlText, getSqlParameter(dataRow.getDataRow(), dataTable.getDataColumns())));
			}
		}
		return commands;
	}

	/**
	 * 更新コマンドを取得する
	 * 
	 * @param @param
	 *            dataTable データテーブル
	 * @return 更新コマンド
	 */
	public List<SqlCommand> getUpdateCommand(DataTable dataTable) {
		StringBuilder sql = new StringBuilder();
		String[] propNames = dataTable.getColumnNames();

		sql.append("update ");
		sql.append(dataTable.getTableName());
		sql.append(" set ");
		sql.append(getPropertySetters(propNames, dataTable.getPrimaryKeys()));
		sql.append(" where ");
		// 更新条件・PK
		sql.append(getPropertyWhere(dataTable.getPrimaryKeys()));
		String sqlText = sql.toString();
		List<SqlCommand> commands = new ArrayList<SqlCommand>();
		List<DataColumn> dataColumns = getAllColoums(dataTable.getDataColumns(), propNames, dataTable.getPrimaryKeys());
		for (DataRow dataRow : dataTable.getDataRows()) {
			if (DataStateEnum.Modified.equals(dataRow.getState())) {
				Object[] allColoumsValue = getAllColoumsValue(dataRow.getDataRow(), propNames, dataTable.getPrimaryKeys());
				commands.add(
						new SqlCommand(sqlText, getSqlParameter(allColoumsValue, dataColumns)));
			}
		}
		return commands;
	}

	/**
	 * 削除コマンドを取得する
	 * 
	 * @param @param
	 *            dataTable データテーブル
	 * @return 削除コマンド
	 */
	public List<SqlCommand> getDeleteCommand(DataTable dataTable) {
		StringBuilder sql = new StringBuilder();
		String[] propNames = dataTable.getColumnNames();
		sql.append("delete from ");
		sql.append(dataTable.getTableName());
		sql.append(" where ");
		// 削除条件・PK
		sql.append(getPropertyWhere(dataTable.getPrimaryKeys()));
		String sqlText = sql.toString();
		List<SqlCommand> commands = new ArrayList<SqlCommand>();
		List<DataColumn> keyColumns = getKeyColoums(dataTable.getDataColumns(), propNames, dataTable.getPrimaryKeys());
		for (DataRow dataRow : dataTable.getDataRows()) {
			if (DataStateEnum.Deleted.equals(dataRow.getState())) {
				Object[] keyColoumsValue = getKeyColoumsValue(dataRow.getDataRow(), propNames, dataTable.getPrimaryKeys());
				commands.add(
						new SqlCommand(sqlText, getSqlParameter(keyColoumsValue, keyColumns)));
			}
		}
		return commands;
	}

	/**
	 * パラメータを設定する
	 * 
	 * @param ps
	 *            PreparedStatement
	 * @param p
	 *            パラメータ
	 */
	public void addSqlParameter(PreparedStatement ps, SqlParameter[] p) throws SQLException {
		if (p == null) {
			return;
		}
		for (int j = 0; j < p.length; j++) {
			if (p[j] == null) {
				ps.setString(j + 1, null);
				continue;
			}
			if (FieldTypeEnum.Integer.equals(p[j].getType())) {
				ps.setInt(j + 1, p[j].getIntValue());
			} else if (FieldTypeEnum.Double.equals(p[j].getType())) {
				ps.setDouble(j + 1, p[j].getDoubleValue());
			} else {
				ps.setString(j + 1, p[j].getStringValue());
			}
		}
	}
	
	/**
	 * カラムセット値配列を取得する
	 * 
	 * @param dataRow
	 *            カラム値集合
	 * @param propNames
	 *            プロパティ名配列
	 * @param primaryKeys
	 *            主キー
	 * @return カラムセット値配列
	 */
	private Object[] getAllColoumsValue(Object[] dataRow, String[] propNames, List<String> primaryKeys) {
		List<Object> keyValueList = new ArrayList<>();
		List<Object> coloumsValueList = new ArrayList<>();
		for (int i = 0; i < propNames.length; i++) {
			String propName = propNames[i];
			if (primaryKeys.contains(propName)) {
				keyValueList.add(dataRow[i]);
			} else {
				coloumsValueList.add(dataRow[i]);
			}
		}
		coloumsValueList.addAll(keyValueList);
		return coloumsValueList.toArray();
	}
	
	/**
	 * カラム配列を取得する
	 * 
	 * @param dataColumn
	 *            カラム集合
	 * @param propNames
	 *            プロパティ名配列
	 * @param primaryKeys
	 *            主キー
	 * @return カラムリスト
	 */
	private List<DataColumn> getAllColoums(List<DataColumn> dataColumn, String[] propNames, List<String> primaryKeys) {
		List<DataColumn> dataColumnsAll = new ArrayList<>();
		List<DataColumn> keyColumns = new ArrayList<>();
		for (int i = 0; i < propNames.length; i++) {
			String propName = propNames[i];
			if (primaryKeys.contains(propName)) {
				keyColumns.add(dataColumn.get(i));
			} else {
				dataColumnsAll.add(dataColumn.get(i));
			}
		}
		dataColumnsAll.addAll(keyColumns);
		return dataColumnsAll;
	}
	
	/**
	 * キーカラムセット値配列を取得する
	 * 
	 * @param dataRow
	 *            カラム値集合
	 * @param propNames
	 *            プロパティ名配列
	 * @param primaryKeys
	 *            主キー
	 * @return キーカラムセット値配列
	 */
	private Object[] getKeyColoumsValue(Object[] dataRow, String[] propNames, List<String> primaryKeys) {
		List<Object> keyValueList = new ArrayList<>();
		for (int i = 0; i < propNames.length; i++) {
			String propName = propNames[i];
			if (primaryKeys.contains(propName)) {
				keyValueList.add(dataRow[i]);
			} 
		}
		return keyValueList.toArray();
	}
	
	/**
	 * キーカラム配列を取得する
	 * 
	 * @param dataColumn
	 *            カラム集合
	 * @param propNames
	 *            プロパティ名配列
	 * @param primaryKeys
	 *            主キー
	 * @return キーカラムリスト
	 */
	private List<DataColumn> getKeyColoums(List<DataColumn> dataColumn, String[] propNames, List<String> primaryKeys) {
		List<DataColumn> keyColumns = new ArrayList<>();
		for (int i = 0; i < propNames.length; i++) {
			String propName = propNames[i];
			if (primaryKeys.contains(propName)) {
				keyColumns.add(dataColumn.get(i));
			} 
		}
		return keyColumns;
	}

	/**
	 * カラムのプレースホールダー文を取得する
	 * 
	 * @param propNames
	 *            プロパティ名配列
	 * @return カラムのプレースホールダー文
	 */
	private String getPropertyPlaceHolders(String[] propNames) {
		StringBuilder buf = new StringBuilder();
		for (int index = 0; index < propNames.length; index++) {
			if (index > 0) {
				buf.append(", ");
			}
			buf.append("?");
		}
		return buf.toString();
	}

	/**
	 * カラム検索用SQL文を取得する
	 * 
	 * @param propNames
	 *            プロパティ名配列
	 * @return カラム検索用SQL文
	 */
	private String getPropertyColumns(String[] propNames) {
		StringBuilder buf = new StringBuilder();
		int index = 0;
		for (String propName : propNames) {
			if (index > 0) {
				buf.append(", ");
			}
			buf.append(propName);
			index++;
		}
		return buf.toString();
	}

	/**
	 * カラムセット用SQL文を取得する
	 * 
	 * @param propNames
	 *            プロパティ名配列
	 * @param primaryKeys
	 *            主キー
	 * @return カラムセット用SQL文
	 */
	private String getPropertySetters(String[] propNames, List<String> primaryKeys) {
		StringBuilder buf = new StringBuilder();
		int index = 0;
		for (String propName : propNames) {
			if (primaryKeys.contains(propName)) {
				continue;
			}
			if (index++ > 0) {
				buf.append(", ");
			}
			buf.append(propName);
			buf.append(" = ?");
		}
		return buf.toString();
	}

	/**
	 * 更新・削除条件用SQL文を取得する
	 * 
	 * @param propNames
	 *            プロパティ名配列
	 * @return 更新・削除条件用SQL文
	 */
	private String getPropertyWhere(List<String> primaryKeys) {
		StringBuilder buf = new StringBuilder();
		int index = 0;
		for (String primaryKey : primaryKeys) {
			if (index++ > 0) {
				buf.append(" AND ");
			}
			buf.append(primaryKey);
			buf.append(" = ?");
		}
		return buf.toString();
	}

	private SqlParameter[] getSqlParameter(Object[] parameters, List<DataColumn> dataColumns) {
		SqlParameter[] p = new SqlParameter[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			FieldTypeEnum type = dataColumns.get(i).getDataType();
			if (null == parameters[i]) {
				p[i] = new SqlParameter(type, null);
			} else if (FieldTypeEnum.Integer.equals(type)) {
				p[i] = new SqlParameter(type, Integer.parseInt(parameters[i].toString()));
			} else if (FieldTypeEnum.Double.equals(type)) {
				p[i] = new SqlParameter(type, Double.parseDouble(parameters[i].toString()));
			} else {
				p[i] = new SqlParameter(type, parameters[i].toString());
			}
		}
		return p;
	}

}
