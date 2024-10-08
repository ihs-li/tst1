package org.example;

import javax.swing.text.JTextComponent;
import javax.swing.JOptionPane; // 导入 JOptionPane
public class SearchOperations {
    public static void searchWord(JTextComponent textComp) {
        String searchText = JOptionPane.showInputDialog("Enter word to find:");
        if (searchText != null && !searchText.trim().isEmpty()) {
            int index = textComp.getText().indexOf(searchText);
            if (index >= 0) {
                textComp.select(index, index + searchText.length());
            } else {
                JOptionPane.showMessageDialog(null, "Word not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}