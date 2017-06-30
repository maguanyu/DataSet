package dataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import common.enums.DataStateEnum;
import common.util.DataSetUtil;
import dataset.base.SqlCommand;
import dataset.base.SqlCommandResolver;

public class ToDB {
	
	/**
	 * データベース接続
	 */
	private Connection conn = null;
	
	/**
	 * コンストラクタ
	 */
	public ToDB(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * データテーブルを更新する
	 * 
	 * @param dataTable 更新用データテーブル
	 * @throws Exception 
	 */
	public void saveTable(DataTable dataTable) throws Exception {
		
		if (DataSetUtil.isEmpty(dataTable)) {
			return;
		}
		SqlCommandResolver resolver = new SqlCommandResolver();
		HashSet<DataStateEnum> statesSet = judgeStates(dataTable);
		
		// 追加の場合
		if (statesSet.contains(DataStateEnum.Added)) {
			saveTable(resolver.getInsertCommand(dataTable));
		}
		// 変更の場合
		if (statesSet.contains(DataStateEnum.Modified)) {
			saveTable(resolver.getUpdateCommand(dataTable));
		}
		//　削除の場合
		if (statesSet.contains(DataStateEnum.Deleted)) {
			saveTable(resolver.getDeleteCommand(dataTable));
		}
	}
	
	/**
	 * データテーブルを更新する
	 * 
	 * @param SqlCommandList 更新用SQL文
	 * @throws Exception 
	 */
	private void saveTable(List<SqlCommand> SqlCommandList)  throws Exception {
		PreparedStatement ps = null;
		SqlCommandResolver resolver = new SqlCommandResolver();
		try {
			ps = conn.prepareStatement(SqlCommandList.get(0).getText());
			for (SqlCommand command : SqlCommandList) {
				resolver.addSqlParameter(ps, command.getParameters());
				ps.addBatch();
			}
			// SQLを実行
			ps.executeBatch();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	/**
	 * 状態を取得する
	 * 
	 * @param dataTable データテーブル
	 * @return 状態セット 
	 */
	private HashSet<DataStateEnum> judgeStates(DataTable dataTable) {
		HashSet<DataStateEnum> statesSet = new HashSet<>();
		for (DataRow dataRow : dataTable.getDataRows()) {
			if (DataStateEnum.Added.equals(dataRow.getState())) {
				// 追加の場合
				statesSet.add(DataStateEnum.Added);
			} else if (DataStateEnum.Modified.equals(dataRow.getState())) {
				// 変更の場合
				statesSet.add(DataStateEnum.Modified);
			} else if (DataStateEnum.Deleted.equals(dataRow.getState())) {
				// 削除の場合
				statesSet.add(DataStateEnum.Deleted);
			}
		}
		return statesSet;
	}

}
