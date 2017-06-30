/**
 * 
 */
package datasetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataset.DataColumn;
import dataset.DataRow;
import dataset.DataTable;
import dataset.FromDB;
import dataset.base.SqlParameter;

/**
 * @author magy
 *
 */
public class DataTableTest {
	
	private static Connection conn = null;
	
	private static DataTable table = null;
	
	@BeforeClass
	public static void before() {
		try {
			conn = createConn();
			FromDB from = new FromDB(conn, "D209007", "rgaa");
			String sql = "select * from rgaa.\"COMPANY\" ";
			SqlParameter[] p = new SqlParameter[0];
			table = from.getDataTable(sql, p, "COMPANY");
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
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link dataset.DataTable#DataTable()}.
	 */
	@Test
	public void testDataTable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#DataTable(java.util.List, java.util.List)}.
	 */
	@Test
	public void testDataTableListOfDataColumnListOfDataRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#DataTable(java.lang.String)}.
	 */
	@Test
	public void testDataTableString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getPrimaryKeys()}.
	 */
	@Test
	public void testGetPrimaryKeys() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#setPrimaryKeys(java.util.List)}.
	 */
	@Test
	public void testSetPrimaryKeys() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#DataTable(java.util.List, java.util.List, java.util.List)}.
	 */
	@Test
	public void testDataTableListOfStringListOfDataColumnListOfDataRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#next()}.
	 */
	@Test
	public void testNext() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getDataRow(int)}.
	 */
	@Test
	public void testGetDataRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#addDataRow(dataset.DataRow)}.
	 */
	@Test
	public void testAddDataRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#addDataColumn(dataset.DataColumn)}.
	 */
	@Test
	public void testAddDataColumn() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#insertAt(dataset.DataRow, int)}.
	 */
	@Test
	public void testInsertAt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#removeDataRow(int)}.
	 */
	@Test
	public void testRemoveDataRow() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#clear()}.
	 */
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getColumnName(int)}.
	 */
	@Test
	public void testGetColumnName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getColumnIndex(java.lang.String)}.
	 */
	@Test
	public void testGetColumnIndex() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getFieldClass(int)}.
	 */
	@Test
	public void testGetFieldClass() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getDataColumns()}.
	 */
	@Test
	public void testGetDataColumns() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#setDataColumns(java.util.List)}.
	 */
	@Test
	public void testSetDataColumns() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getTableName()}.
	 */
	@Test
	public void testGetTableName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#setTableName(java.lang.String)}.
	 */
	@Test
	public void testSetTableName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getRowNum()}.
	 */
	@Test
	public void testGetRowNum() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getColumnNum()}.
	 */
	@Test
	public void testGetColumnNum() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getCursorPosition()}.
	 */
	@Test
	public void testGetCursorPosition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#setCursorPosition(int)}.
	 */
	@Test
	public void testSetCursorPosition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getDataRows()}.
	 */
	@Test
	public void testGetDataRows() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#setDataRows(java.util.List)}.
	 */
	@Test
	public void testSetDataRows() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#getColumnNames()}.
	 */
	@Test
	public void testGetColumnNames() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#writeCSV(java.lang.String, java.lang.String[], java.text.DecimalFormat, common.enums.ItemDividerEnum)}.
	 */
	@Test
	public void testWriteCSVStringStringArrayDecimalFormatItemDividerEnum() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#toText(java.lang.String[], java.text.DecimalFormat, common.enums.ItemDividerEnum)}.
	 */
	@Test
	public void testToText() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#writeCSV(java.lang.String)}.
	 */
	@Test
	public void testWriteCSVString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#writeCSV(java.lang.String, java.text.DecimalFormat, common.enums.ItemDividerEnum)}.
	 */
	@Test
	public void testWriteCSVStringDecimalFormatItemDividerEnum() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#writeXML(java.lang.String)}.
	 */
	@Test
	public void testWriteXML() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link dataset.DataTable#selectByFilter(java.lang.String)}.
	 */
	@Test
	public void testSelectByFilterString() {
		List<DataRow> result1 = table.selectByFilter("id='1008'");
		assertThat(result1.size(), is(1));
		assertThat(result1.get(0).getDataRow()[0].toString(), is("1008"));
		assertThat(result1.get(0).getDataRow()[1].toString(), is("LIANDI"));
		assertThat(result1.get(0).getDataRow()[2].toString(), is("テスト"));
		assertThat(result1.get(0).getDataRow()[3].toString(), is("20"));
		assertThat(result1.get(0).getDataRow()[4].toString(), is("南京                                                "));
		assertThat(result1.get(0).getDataRow()[5].toString(), is("1001.0"));
	}

	/**
	 * Test method for {@link dataset.DataTable#selectByFilter(java.lang.String)}.
	 */
	@Test
	public void testSelectByFilterString2() {
		List<DataRow> result2 = table.selectByFilter("id>='1001'");
		assertThat(result2.size(), is(2));
		assertThat(result2.get(1).getDataRow()[0].toString(), is("1008"));
		assertThat(result2.get(1).getDataRow()[1].toString(), is("LIANDI"));
		assertThat(result2.get(1).getDataRow()[2].toString(), is("テスト"));
		assertThat(result2.get(1).getDataRow()[3].toString(), is("20"));
		assertThat(result2.get(1).getDataRow()[4].toString(), is("南京                                                "));
		assertThat(result2.get(1).getDataRow()[5].toString(), is("1001.0"));
	}
	
	/**
	 * Test method for {@link dataset.DataTable#selectByFilter(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testSelectByFilterStringString() {
		List<DataRow> result1 = table.selectByFilter(null,"id desc");
		assertThat(result1.get(0).getDataRow()[0].toString(), is("1008"));
		assertThat(result1.get(1).getDataRow()[0].toString(), is("1001"));
	}
	
	private static Connection createConn() throws ClassNotFoundException, SQLException {
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
