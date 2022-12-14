package com.inetbanking.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String path = "C:\\Users\\SPURGE\\Downloads\\Ebanking_demo-master\\Ebanking_demo-master\\src\\test\\java\\com\\inetbanking\\TestData\\LoginDataXL.xlsx";

	public static int getRowCount( String xlSheet) throws Throwable
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;

	}


	public static int getCellCount(String xlSheet, int rowNum) throws Throwable
	{

		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;


	}	

	public static String getCellData( String xlSheet, int rowNum, int colNum) throws Throwable
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try
		{
			DataFormatter formater = new DataFormatter();
			String cellData = formater.formatCellValue(cell);
			return cellData;

		}
		catch(Exception e)
		{
			data ="";
		}
		wb.close();
		fi.close();
		return data;

	}

	public static void setCellData( String xlSheet, int rowNum, int colNum, String data) throws Throwable
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlSheet);
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}




}
