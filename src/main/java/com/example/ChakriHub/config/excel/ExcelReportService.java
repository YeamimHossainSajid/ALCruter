package com.example.ChakriHub.config.excel;

import com.example.ChakriHub.auth.repository.UserRepo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReportService {
    public UserRepo userRepo;
    public ExcelRepo excelRepo;
    public ExcelReportService(UserRepo userRepo, ExcelRepo excelRepo) {
        this.userRepo = userRepo;
        this.excelRepo= excelRepo;
    }
    public void generateExcelReport(HttpServletResponse response) throws IOException {
        List<ExcelUser> ExcelUsers = excelRepo.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Properties");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Features");
        row.createCell(2).setCellValue("Location");

        row.setHeightInPoints(25);
        CellStyle wrapStyle = workbook.createCellStyle();
        wrapStyle.setWrapText(true);

        int dataRow = 1;
        for (ExcelUser excelUser : ExcelUsers) {
            HSSFRow row1 = sheet.createRow(dataRow);
            Long id=excelUser.getId();
            if(id != null) {
                row1.createCell(0).setCellValue(id);
            }else{
                row1.createCell(0).setCellValue(" ");
            }
            String features = excelUser.getEmail();
            if(!features.isEmpty()) {
                row1.createCell(1).setCellValue(features);
            }else{
                row1.createCell(1).setCellValue(" ");
            }
            String username = excelUser.getUsername();
            if(!username.isEmpty()) {
                row1.createCell(2).setCellValue(username);
            }else{
                row1.createCell(2).setCellValue(" ");
            }

//            Cell passwordCell = row1.createCell(3);
//            passwordCell.setCellValue(user.getPassword());
//            passwordCell.setCellStyle(wrapStyle);
            row1.setHeightInPoints(30);
            dataRow++;
        }

        sheet.setColumnWidth(0, 4000); // ID column width
        sheet.setColumnWidth(1, 8000); // Name column width
        sheet.setColumnWidth(2, 8000); // Email column width
        sheet.setColumnWidth(3, 10000);

        ServletOutputStream ops= response.getOutputStream();
       workbook.write(ops);
       ops.close();


    }


    @Transactional
    public void saveExcelData(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            List<ExcelUser> users = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                ExcelUser user = new ExcelUser();


                Cell idCell = row.getCell(0);
                if (idCell != null && idCell.getCellType() == CellType.NUMERIC) {
                    user.setId((long) idCell.getNumericCellValue());
                } else {
                    user.setId(null);
                }

                Cell usernameCell = row.getCell(2);
                if (usernameCell != null) {
                    user.setUsername(usernameCell.getStringCellValue());
                }else{
                    user.setUsername(" ");
                }


                Cell emailCell = row.getCell(1);
                if (emailCell != null) {
                    user.setEmail(emailCell.getStringCellValue());
                }else{
                    user.setEmail(" ");
                }


//                Cell passwordCell = row.getCell(3);
//                if (passwordCell != null) {
//                    user.setPassword(passwordCell.getStringCellValue());
//                }

                users.add(user);
            }

            excelRepo.saveAll(users);

        } catch (Exception e) {

            System.err.println("Error while reading Excel file: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file", e);
        }
    }

}
