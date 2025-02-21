package com.example.ChakriHub.config.cvparsing;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    public String extractTextFromPdf(String pdfFilePath) {
        String extractedText = "";

        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            extractedText = pdfStripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return extractedText;
    }
}