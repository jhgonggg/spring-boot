package com.funtl.hello.spring.boot.util;

import com.alibaba.excel.EasyExcel;
import com.funtl.hello.spring.boot.constant.SysConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
public class ExcelUtil {

    public static void export(String fileName, List exportList, Class tClass, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String filename = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename + ".xlsx");
            EasyExcel.write(response.getOutputStream(), tClass).sheet(filename).doWrite(exportList);
        } catch (Exception e) {
            log.error("导出数据异常,文件名:{},错误信息:{}", fileName, e);
            throw new RuntimeException("导出数据异常");
        }
    }

    public static void write(String filePath, String fileName, List dataList, Class tClass) {
        try {
            String encoderFileName = URLEncoder.encode(fileName, SysConst.UTF_8);
            EasyExcel.write(filePath, tClass).sheet(encoderFileName).doWrite(dataList);
        } catch (Exception e) {
            log.error("生成excel出错，文件名：{}，错误信息：{}", fileName, e.getMessage());
            throw new RuntimeException("生成excel出错");
        }
    }
}
