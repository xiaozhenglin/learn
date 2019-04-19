package com.xiaozl.learn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {

    public static <T> List<T> importData(File file, Class<?> outputClass) throws Exception {
        Field[] fields = outputClass.getDeclaredFields();
        List<T> values = new ArrayList();
        Workbook wb = WorkbookFactory.create(new FileInputStream(file));
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        //0行
        Row row0 = sheet.getRow(0);
        //获得总列数
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
        //获取总行数
        int firstRowNum = sheet.getFirstRowNum();
        int rowNum = sheet.getLastRowNum();

        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以1开始
            //创建类实例
            T bean = (T) outputClass.newInstance();
            for (int j = 0; j < coloumNum; j++) {
                //获取单元格
                Cell cell = row.getCell(j);
                if (cell != null) {
                    int cellType = cell.getCellType();
                    switch (cellType) {
                        case 0:
                            //数字
                            double numericCellValue = cell.getNumericCellValue();
                            int number = (int) numericCellValue;
                            BeanUtils.setProperty(bean, fields[j].getName(), number);
                            break;
                        case 1:
                            //字符串
                            String stringCellValue = cell.getStringCellValue();
                            BeanUtils.setProperty(bean, fields[j].getName(), stringCellValue);
                            break;
                        default:
                            break;
                    }
//	                	cell.setCellType(Cell.CELL_TYPE_STRING);
                    //列名
//	                  	String columnName = row0.getCell(j).getStringCellValue();
                    //列值
//		            	String stringCellValue = cell.getStringCellValue();
                    //放入实体类中
//		            	if (fields[j].isAnnotationPresent(Column.class)) {
//							Column column = fields[j].getAnnotation(Column.class);
//							if (column.name().equalsIgnoreCase(columnName) && stringCellValue != null) {
//								BeanUtils.setProperty(bean, fields[j].getName(), stringCellValue);
//							}
//						}
                }
            }
            values.add(bean);
        }
        return values;
    }

    public static File multiFileToFile(MultipartFile file) {
//        String basePath = SystemConfig.getRealUploadPath();
        String basePath = "";
        String path = basePath + "/excel/coupon/" + new Date().getTime() + ".xlsx";
        File tempFile = new File(path);
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), tempFile);
        } catch (IOException e) {
//            throw new Exception("multifile transefor file error");
        }
        return tempFile;
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
