package org.example;

import javax.swing.*;
//
public class EditOperations {
//    public static void cut(JTextArea textArea) {
//        String selectedText = textArea.getSelectedText();
//        if (selectedText != null) {
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            clipboard.setContents(new StringSelection(selectedText), null);
//            textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
//        }
//    }
//
//    public static void copy(JTextArea textArea) {
//        String selectedText = textArea.getSelectedText();
//        if (selectedText != null) {
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            clipboard.setContents(new StringSelection(selectedText), null);
//        }
//    }
//
//    public static void paste(JTextArea textArea) {
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        Transferable contents = clipboard.getContents(null);
//        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
//        if (hasTransferableText) {
//            try {
//                String pastedText = (String) contents.getTransferData(DataFlavor.stringFlavor);
//                textArea.insert(pastedText, textArea.getCaretPosition());
//            } catch (UnsupportedFlavorException | IOException e) {
//                JOptionPane.showMessageDialog(null, "粘贴失败：" + e.getMessage());
//            }
//        }
//    }

}