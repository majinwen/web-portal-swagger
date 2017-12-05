package com.toutiao.web.common.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * zhangjinglei 2017/9/3 下午1:01
 */
public class ExcelUtil {

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellTypeEnum()) {
            case STRING:
                strCell = cell.getStringCellValue();
                break;
            case NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }

        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell
     *            Excel单元格
     * @return String 单元格数据内容
     */
    public static DateTime getDateCellValue(Cell cell) {
        DateTime res=null;
        try {
            switch (cell.getCellTypeEnum()){

                case NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        Date date = cell.getDateCellValue();
                        if(date!=null){
                            res=new DateTime(date);
                        }
                    }
                    break;
                case STRING:
                    String s = getStringCellValue(cell);
                    String d = s.replaceAll("[年月]", "-").replace("日", "").trim();
                    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                    res = DateTime.parse(d,fmt);
                    break;
                default:
                    return null;
            }


        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return res;
    }
}
