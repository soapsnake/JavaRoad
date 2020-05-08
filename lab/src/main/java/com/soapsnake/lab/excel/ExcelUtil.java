package com.soapsnake.lab.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUtil {
    /**
     *
     * @param filePath Excel文件路径
     * @param clazz 待赋值对象class
     * @param allowBlank 是否允许某些单元格出现空值
     * @param fieldNames 对象字段名称数组,注意顺序,解析将按照数组字段顺序进行赋值
     * @return
     */
    public  static <T> List<T> readFromExcel(String filePath, Class<T> clazz, String[] fieldNames,
            boolean allowBlank) {
        File excelFile = null;// Excel文件对象
        InputStream is = null;// 输入流对象
        String cellStr = null;// 单元格，最终按字符串处理
        List<T> dataList = new ArrayList<>();// 返回封装数据的List
        T data = null;// 每一个雇员信息对象
        try {
            String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResource(".")).getPath();
            excelFile = new File(path + "/" + filePath);
            is = new FileInputStream(excelFile);// 获取文件输入流
            org.apache.poi.ss.usermodel.Workbook workbook2007 = WorkbookFactory.create(is);
            org.apache.poi.ss.usermodel.Sheet sheet = workbook2007.getSheetAt(0); //第一个sheet
            // 开始循环遍历行，表头不处理，从1开始
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                data = clazz.newInstance();// 实例化Student对象
                Row row = sheet.getRow(i);// 获取行对象
                if (row == null) {// 如果空行，不处理
                    continue;
                }
                // 循环遍历单元格
                if (fieldNames.length != row.getLastCellNum()) {
                    System.out.println("filenames len = " + fieldNames.length  + "excel len =" + (row.getLastCellNum()));
                    throw new RuntimeException("excel字段个数与传入字段数组长度不一致!");
                }
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);// 获取单元格对象
                    if (cell == null) {// 单元格为空设置cellStr为空串
                        if (allowBlank) {
                            cellStr = "";
                        } else {
                            throw new RuntimeException("单元格为空");
                        }
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
                        cellStr = String.valueOf(cell.getBooleanCellValue());
                    } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
                        cellStr = (int)cell.getNumericCellValue() + "";
                    }
                    else {// 其余按照字符串处理
                        cellStr = cell.getStringCellValue();
                    }
                    // 下面按照数据出现位置封装到bean中
                    Field field = clazz.getDeclaredField(fieldNames[j]);
                    field.setAccessible(true);
                    field.set(data, cellStr);
                }
                dataList.add(data);// 数据装入List
            }
        } catch (IOException | InvalidFormatException | IllegalAccessException
                | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
            log.error("解析excel出错!!!",e);
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("e关闭excel出错!!!",e);
                }
            }
        }
        log.info("总文件行数 = {}", dataList.size());
        System.out.println("总文件行数 = " + dataList.size());
        return dataList;
    }

    public static void main(String[] args) {
        String[] fileds = {"department", "tenantCode", "devId", "devName", "queueName"};
        List<TenantInit> list = readFromExcel("xx表.xlsx", TenantInit.class, fileds, false);
        System.out.println(list);
    }
}
