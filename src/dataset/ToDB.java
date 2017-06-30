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
	 * �f�[�^�x�[�X�ڑ�
	 */
	private Connection conn = null;
	
	/**
	 * �R���X�g���N�^
	 */
	public ToDB(Connection conn){
		this.conn = conn;
	}
	
	/**
	 * �f�[�^�e�[�u�����X�V����
	 * 
	 * @param dataTable �X�V�p�f�[�^�e�[�u��
	 * @throws Exception 
	 */
	public void saveTable(DataTable dataTable) throws Exception {
		
		if (DataSetUtil.isEmpty(dataTable)) {
			return;
		}
		SqlCommandResolver resolver = new SqlCommandResolver();
		HashSet<DataStateEnum> statesSet = judgeStates(dataTable);
		
		// �ǉ��̏ꍇ
		if (statesSet.contains(DataStateEnum.Added)) {
			saveTable(resolver.getInsertCommand(dataTable));
		}
		// �ύX�̏ꍇ
		if (statesSet.contains(DataStateEnum.Modified)) {
			saveTable(resolver.getUpdateCommand(dataTable));
		}
		//�@�폜�̏ꍇ
		if (statesSet.contains(DataStateEnum.Deleted)) {
			saveTable(resolver.getDeleteCommand(dataTable));
		}
	}
	
	/**
	 * �f�[�^�e�[�u�����X�V����
	 * 
	 * @param SqlCommandList �X�V�pSQL��
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
			// SQL�����s
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
	 * ��Ԃ��擾����
	 * 
	 * @param dataTable �f�[�^�e�[�u��
	 * @return ��ԃZ�b�g 
	 */
	private HashSet<DataStateEnum> judgeStates(DataTable dataTable) {
		HashSet<DataStateEnum> statesSet = new HashSet<>();
		for (DataRow dataRow : dataTable.getDataRows()) {
			if (DataStateEnum.Added.equals(dataRow.getState())) {
				// �ǉ��̏ꍇ
				statesSet.add(DataStateEnum.Added);
			} else if (DataStateEnum.Modified.equals(dataRow.getState())) {
				// �ύX�̏ꍇ
				statesSet.add(DataStateEnum.Modified);
			} else if (DataStateEnum.Deleted.equals(dataRow.getState())) {
				// �폜�̏ꍇ
				statesSet.add(DataStateEnum.Deleted);
			}
		}
		return statesSet;
	}

}
