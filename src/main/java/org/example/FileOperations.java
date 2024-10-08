package org.example;

import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.charset.StandardCharsets;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.apache.commons.io.FilenameUtils;

public class FileOperations {
    public static void createNewDocument(MainEditor editor) {
        editor.getTextArea().setText("");
    }

    public static void openDocument(MainEditor editor) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(editor);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String ext = FilenameUtils.getExtension(file.getName()).toLowerCase();
            try {
                RSyntaxTextArea textArea = editor.getTextArea();
                textArea.read(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8), null);
                setSyntaxStyle(textArea, ext);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(editor, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void setSyntaxStyle(RSyntaxTextArea textArea, String ext) {
        switch (ext) {
            case "java":
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                break;
            case "py":
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
                break;
            case "cpp":
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                break;
            default:
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        }
    }

    public static void saveDocument(MainEditor editor) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(editor);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            try {
                RSyntaxTextArea textArea = editor.getTextArea();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
                    textArea.write(writer);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(editor, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void saveAsPDFDocument(MainEditor editor) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            RSyntaxTextArea textArea = editor.getTextArea();
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(25, 750);
            String text = textArea.getText();
            String[] lines = text.split("\n");
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -12);
            }
            contentStream.endText();
            contentStream.close();
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(editor);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }
                document.save(file);
            }
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printDocument(MainEditor editor) {
        try {
            editor.getTextArea().print();
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(editor, "Printing error:" + e.getMessage());
        }
    }

    public static void handleTimeDate(MainEditor editor) {
        JOptionPane.showMessageDialog(editor, "current time: " + new java.util.Date());
    }
}
