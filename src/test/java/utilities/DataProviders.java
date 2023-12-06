package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;

//This file is to maintain all the DataProviders, which can be used in DataDriven testing in different test cases.

public class DataProviders {
	
	
	
	
	//DataProvider1
	@DataProvider(name="LoginTestData")
	public String[][] getloginData() throws IOException
	{
		String path=".//testData//Opencart_Logindata.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrowCount=xlutil.getRowCount("Sheet1");
		int totalcolumns=xlutil.getCellCount("Sheet1", 1);
		
		System.out.println("Total rows are: "+totalrowCount);
		
		System.out.println("Total columns are: "+totalcolumns);
		String logindata[][]=new String[totalrowCount][totalcolumns];
		
		for(int r=1;r<=totalrowCount;r++)
		{
			for(int c=0;c<totalcolumns;c++)
			{
				logindata[r-1][c]=xlutil.getCellData("sheet1", r, c);
				System.out.print(logindata[r-1][c] +"\t");
			}
			System.out.println();
		}
		
		return logindata;
	}
	
	//DataProvider2
	
	
	//DataProvider3
	
	//DataProvider....

}
