package com.liupanshui.educenter.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    public static <T> void writeExcel(HttpServletResponse response, List<T> dataList, Class<T> clazz, String fileName)
            throws IOException {
        // 创建Workbook对象
        Workbook workbook = new SXSSFWorkbook(10000);

        // 创建Sheet对象
        Sheet sheet = workbook.createSheet();

        // 设置表头样式
        CellStyle headerCellStyle = createHeaderCellStyle(workbook);

        // 写入表头
        Row headerRow = sheet.createRow(0);
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(field.getName());
            cell.setCellStyle(headerCellStyle);
        }

        // 写入数据
        CellStyle dataCellStyle = createDataCellStyle(workbook);
        CreationHelper creationHelper = workbook.getCreationHelper();
        DataFormat dataFormat = creationHelper.createDataFormat();
        for (int i = 0; i < dataList.size(); i++) {
            T data = dataList.get(i);
            Row dataRow = sheet.createRow(i + 1);
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(data);
                } catch (IllegalAccessException e) {
                    continue;
                }
                String stringValue = "";
                if (value != null) {
                    stringValue = value.toString();
                }
                Cell cell = dataRow.createCell(j);
                cell.setCellValue(stringValue);

                // 设置单元格格式（如果需要）
                if (value instanceof Number) {
                    CellStyle numericCellStyle = workbook.createCellStyle();
                    numericCellStyle.cloneStyleFrom(dataCellStyle);
                    numericCellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
                    cell.setCellStyle(numericCellStyle);
                } else if (value instanceof Date) {
                    CellStyle dateCellStyle = workbook.createCellStyle();
                    dateCellStyle.cloneStyleFrom(dataCellStyle);
                    dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
                    cell.setCellStyle(dateCellStyle);
                } else {
                    cell.setCellStyle(dataCellStyle);
                }
            }
        }

        // 输出文件到浏览器
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        return headerCellStyle;
    }

    private static CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.LEFT);
        dataCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataCellStyle.setWrapText(true);

        return dataCellStyle;
    }


}