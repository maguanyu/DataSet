package datasetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import common.enums.DataStateEnum;
import common.enums.FieldTypeEnum;
import dataset.DataRow;
import dataset.DataTable;
import dataset.FromDB;
import dataset.ToDB;
import dataset.base.SqlParameter;

public class ToDBTest {

	private Connection conn = null;
	
	private int executeStatus = 0;

	@Before
	public void before() {
		try {
			conn = createConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveTable() {
		try {
			FromDB from = new FromDB(conn, "D209007", "rgaa");
			String sql = "select * from rgaa.\"COMPANY\" where rgaa.\"COMPANY\".logo = ? order by rgaa.\"COMPANY\".id asc";
			SqlParameter[] p = new SqlParameter[1];
			p[0] = new SqlParameter(FieldTypeEnum.String, "LIANDI");
			DataTable table = from.getDataTable(sql, p, "COMPANY");
			// edit
			List<DataRow> DataRows = table.getDataRows();
			Object[] values = new Object[6];
			int index = 0;
			int MaxId = Integer.parseInt(DataRows.get(DataRows.size() - 1).getObject("id").toString());
			values[index++] = MaxId + 1;
			values[index++] = "LIANDI";
			values[index++] = "ƒeƒXƒg";
			values[index++] = "20";
			values[index++] = "“ì‹ž";
			values[index++] = 1001;
			DataRow rowAdd = new DataRow(values);
			rowAdd.setState(DataStateEnum.Added);
			DataRow rowModified = table.getDataRows().get(0);
			rowModified.setObject("age", Integer.parseInt(rowModified.getObject("age").toString()) + 1);
			rowModified.setState(DataStateEnum.Modified);
			DataRow rowDeleted = table.getDataRows().get(1);
			rowDeleted.setState(DataStateEnum.Deleted);
			table.addDataRow(rowAdd);

			ToDB to = new ToDB(conn);
			to.saveTable(null);
			to.saveTable(table);
			assertThat(executeStatus, is(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Connection createConn() throws ClassNotFoundException, SQLException {
		final String DRIVER = "org.postgresql.Driver";
		final String URL = "jdbc:postgresql://172.16.4.49:20901/D209007";
		final String USERNAME = "D209007";
		final String PASSWORD = "D209007";
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		if (conn != null) {
			System.out.println("Connection success!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return conn;
	}

}
