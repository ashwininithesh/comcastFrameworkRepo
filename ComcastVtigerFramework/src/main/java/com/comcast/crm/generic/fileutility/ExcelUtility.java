package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetname,int rownum,int celnum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/Testscriptdataformodules.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetname).getRow(rownum).getCell(celnum).toString();
		wb.close();
		return data;
	
	}
	
	public int getRowcount(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/Testscriptdataformodules.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rownum=wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rownum;	
	}
	
	public void setDataIntoExcel(String sheetname,int rownum,int celnum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testData/Testscriptdataformodules.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getRow(rownum).createCell(celnum);
		FileOutputStream fos=new FileOutputStream("./testData/Testscriptdataformodules.xlsx");
		wb.write(fos);
		wb.close();
		
	}

}
