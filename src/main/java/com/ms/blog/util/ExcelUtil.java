package com.ms.blog.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 表格工具类
 * @author wyh
 * @date 2023/02/02 15:21
 */
@Slf4j
public class ExcelUtil {
    public static <T> void downloadTemplate(String templateName, String sheetName, int columnWidth,
            HttpServletResponse response,Class<T> model) {
        try {
            log.info("下载{}模板", templateName);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(templateName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            EasyExcel.write(response.getOutputStream(), model).sheet(sheetName)
                    //设置列宽
                    .registerWriteHandler(new SimpleColumnWidthStyleStrategy(columnWidth))
                    .doWrite(new ArrayList<>());
        } catch (Exception e) {
            log.error("下载{}模板失败", templateName);
            throw new RuntimeException(e.getMessage());
        }
    }
}
