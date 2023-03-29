package thayduc.quanlydancu.demo.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import thayduc.quanlydancu.demo.dto.HoaDonDTO;
import thayduc.quanlydancu.demo.dto.HoaDonTKDTO;
import thayduc.quanlydancu.demo.entity.CuDan;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class HoaDonExportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<HoaDonDTO> listHoaDonDTO;

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
        else if(value instanceof Date){
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


        createCell(row, 0, "Hóa Đơn ID", style);
        createCell(row, 1, "Họ tên", style);
        createCell(row, 2, "Email", style);
        createCell(row, 3, "Tên dịch vụ", style);
        createCell(row, 4, "Đơn vị tính", style);
        createCell(row, 5, "Đã Dùng", style);
        createCell(row, 6, "Tổng tiền", style);
        createCell(row, 7, "Ngày tạo", style);
    }

    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (HoaDonDTO hoaDonDTO : listHoaDonDTO) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, hoaDonDTO.getId(), style);
            createCell(row, columnCount++, hoaDonDTO.getHoTen(), style);
            createCell(row, columnCount++, hoaDonDTO.getEmail(), style);
            createCell(row, columnCount++, hoaDonDTO.getTendv(), style);
            createCell(row, columnCount++, hoaDonDTO.getDonViTinh(), style);
            createCell(row, columnCount++, hoaDonDTO.getSoDaDung(), style);
            createCell(row, columnCount++, hoaDonDTO.getTongTien(), style);
            createCell(row, columnCount++, hoaDonDTO.getNgayTao(), style);

        }
    }
    public HoaDonExportExcel(List<HoaDonDTO> listHoaDonDTO) {
        this.listHoaDonDTO = listHoaDonDTO;
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
