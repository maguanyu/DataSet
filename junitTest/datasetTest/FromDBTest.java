package datasetTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import common.enums.FieldTypeEnum;
import dataset.DataTable;
import dataset.FromDB;
import dataset.base.SqlParameter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FromDBTest {
	private Connection conn = null;

	@Before
	public void before() {
		try {
			conn = createConn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetDataTable() {
		try {
			FromDB from = new FromDB(conn, "D209007", "rgaa");
			String sql = "select * from rgaa.\"COMPANY\" where rgaa.\"COMPANY\".logo = ? ";
			SqlParameter[] p = new SqlParameter[1];
			p[0] = new SqlParameter(FieldTypeEnum.String, "LIANDI");
			DataTable table = from.getDataTable(sql, p, "COMPANY");
			assertThat(table.getDataRows().size(), is(2));
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
