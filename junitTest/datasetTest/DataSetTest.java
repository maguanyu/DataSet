package datasetTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import dataset.DataColumn;
import dataset.DataRow;
import dataset.DataSet;
import dataset.DataTable;

public class DataSetTest {
	public DataSet dataSet;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		dataSet = new DataSet();

	}

	@Test
	public void testRemoveTableIfExists() {

		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		dataSet.removeTableIfExists("RECORD");
		assertThat(dataSet.getDataTables().size(), is(0));
		assertThat(dataSet.removeTableIfExists(""), is(false));
		dataSet.clear();

	}

	@Test
	public void testGetTableString() {
		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		DataTable dataTable = dataSet.getTable("RECORD");
		assertThat(dataTable.getTableName(), is("RECORD"));
		Assert.assertEquals(dataSet.getTable(""), null);
		dataSet.clear();

	}

	@Test
	public void testGetTableInt() {
		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		DataTable dataTable = dataSet.getTable(0);
		assertThat(dataTable.getTableName(), is("RECORD"));
		Assert.assertEquals(dataSet.getTable(-1), null);

		dataSet.clear();

	}

	@Test
	public void testGetTables() {
		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		dataSet.getTables();
		assertThat(dataSet.getTables().size(), is(1));
		assertThat(dataSet.getTableNames().size(), is(1));

		dataSet.clear();

	}

	@Test
	public void testContainsTable() {
		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);

		assertThat(dataSet.containsTable("RECORD"), is(true));
		assertThat(dataSet.containsTable(""), is(false));

		dataSet.clear();

	}

	@Test
	public void testClear() {

		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		dataSet.clear();
		assertThat(dataSet.getTableCount(), is(0));

	}

	@Test
	public void TestReadCSV() {
		String path1 = "C:\\Users\\magy\\Desktop\\AaT121TennyumaeTsushoRireki22222.csv";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("対象フォルダが不正です。");
		dataSet.readCSV(path1);
	}

	@Test
	public void TestReadXML() {
		String path1 = "C:\\Users\\magy\\Desktop\\AaT121TennyumaeTsushoRireki22222.csv";

		thrown.expect(RuntimeException.class);
		thrown.expectMessage("対象フォルダが不正です。");
		dataSet.readXML(path1);
	}

	@Test
	public void TestReadXML2() {
		String path = "C:\\Users\\magy\\Desktop\\text1.xml";
 		dataSet.readXML(path);
	}

	@Test
	public void testReadCSVString() {

		String path = "C:\\Users\\magy\\Desktop\\AaT121TennyumaeTsushoRireki.csv";
		dataSet.readCSV(path);
		List<DataColumn> dataColumns = dataSet.getTable(0).getDataColumns();
		List<DataRow> dataRows = dataSet.getTable(0).getDataRows();
		assertThat(dataSet.getDataTables().size(), is(1));
		assertThat(dataSet.getTable(0).getTableName(), is("AaT121TennyumaeTsushoRireki"));

		assertThat(dataColumns.get(0).getColumnName(), is("shikibetsuCode"));
		assertThat(dataColumns.get(1).getColumnName(), is("UDD001ShikibetsuCode"));
		assertThat(dataColumns.get(2).getColumnName(), is("ShikibetsuCode"));

		assertThat(dataRows.get(0).getObject("shikibetsuCode"), is("torokuNo"));
		assertThat(dataRows.get(0).getObject("UDD001ShikibetsuCode"), is("int2"));
		assertThat(dataRows.get(0).getObject("ShikibetsuCode"), is("TorokuNo"));
		dataSet.clear();

	}

	@Test
	public void testWriteCSVString() {
		String path = "C:\\Users\\magy\\Desktop\\APB006\\AaT121TennyumaeTsushoRireki.csv";
		String path1 = "C:\\Users\\magy\\Desktop";
		dataSet.readCSV(path);

		List<File> files = dataSet.writeCSV(path1);
		assertThat(files.get(0).getName(), is("AaT121TennyumaeTsushoRireki.csv"));
		dataSet.clear();

	}

	@Test
	public void testReadXML() {

		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		dataSet.readXML(path);
		List<DataColumn> dataColumns = dataSet.getTable(0).getDataColumns();
		List<DataRow> dataRows = dataSet.getTable(0).getDataRows();

		assertThat(dataSet.getDataTables().size(), is(1));
		assertThat(dataSet.getTable(0).getTableName(), is("RECORD"));

		assertThat(dataColumns.get(0).getColumnName(), is("name"));
		assertThat(dataColumns.get(1).getColumnName(), is("id"));
		assertThat(dataColumns.get(2).getColumnName(), is("company"));
		assertThat(dataColumns.get(3).getColumnName(), is("tel"));

		assertThat(dataRows.get(0).getObject("name"), is("magy"));
		assertThat(dataRows.get(0).getObject("id"), is("1"));
		assertThat(dataRows.get(0).getObject("company"), is("liandi"));
		assertThat(dataRows.get(0).getObject("tel"), is("1866666"));
		dataSet.clear();

	}

	@Test
	public void testWriteXMLString() {
		String path = "C:\\Users\\magy\\Desktop\\text.xml";
		String path1 = "C:\\Users\\magy\\Desktop\\test";
		dataSet.readXML(path);
		List<File> files = dataSet.writeXML(path1);

		assertThat(files.get(0).getName(), is("RECORD.xml"));

		dataSet.clear();
	}

	@Test
	public void testAddTable() {
		DataTable dataTable = null;
		assertThat(dataSet.addTable(dataTable), is(false));

		dataSet.setDataTables(null);
		dataSet.setTableNames(null);
		dataTable = new DataTable("testTable");
		assertThat(dataSet.addTable(dataTable), is(true));

		dataSet.setDataTables(dataSet.getDataTables());
		assertThat(dataSet.getTable("testTable").getTableName(), is("testTable"));

		dataSet.clear();

	}

	@Test
	public void testGetTableCount() {

		dataSet.clear();
		assertThat(dataSet.getDataTables().size(), is(0));

	}

}
