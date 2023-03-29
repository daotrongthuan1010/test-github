package thayduc.quanlydancu.demo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import thayduc.quanlydancu.demo.entity.CuDan;

public class DanCuExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<CuDan> listCuDan;

    public void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }
        else if(value instanceof Date ){
            cell.setCellValue((Date) value);

        }
        else if(value instanceof Timestamp){
            cell.setCellValue((Timestamp) value);

        }
        else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    public void writeHeaderLine() {
        sheet = workbook.createSheet("CuDans");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(15);
        style.setFont(font);


        createCell(row, 0, "Cư dân ID", style);
        createCell(row, 1, "Tên", style);
        createCell(row, 2, "Ngày sinh", style);
        createCell(row, 3, "Số điện thoại", style);
        createCell(row, 4, "Chứng minh thư", style);
    }

    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (CuDan cuDan : listCuDan) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, cuDan.getId(), style);
            createCell(row, columnCount++, cuDan.getTen(), style);
            createCell(row, columnCount++, cuDan.getNgaysinh(), style);
            createCell(row, columnCount++, cuDan.getSoDT(), style);
            createCell(row, columnCount++, cuDan.getCmt(), style);

        }
    }
    public DanCuExcelExporter(List<CuDan> listCuDan) {
        this.listCuDan = listCuDan;
        workbook = new XSSFWorkbook();
    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}