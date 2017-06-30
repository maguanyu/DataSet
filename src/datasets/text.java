package datasets;

import datasets.DataSet.itemDividerType;

public class text {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSet dataSet = new DataSet();

		String csvPath = "C:\\Users\\magy\\Desktop\\text.XML";
		String csvPath1 = "C:\\Users\\magy\\Desktop\\test1.csv";

		//DataTable dt = dataSet.importTxt(csvPath, itemDividerType.Comma);
		dataSet.importXML(csvPath);
 
	}

}
