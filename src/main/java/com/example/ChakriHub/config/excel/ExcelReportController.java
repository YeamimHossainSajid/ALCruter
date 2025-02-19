package com.example.ChakriHub.config.excel;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("Excel")
public class ExcelReportController {
    @Autowired
    public ExcelReportService excelReportService;

    @GetMapping
    public void generateExcelReport(HttpServletResponse response) throws IOException {

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=flatelse.xls";
        response.setHeader(headerKey, headerValue);
        excelReportService.generateExcelReport(response);
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            excelReportService.saveExcelData(file);
            return "File uploaded and data saved to database successfully!";
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}
