package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.Date;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem searchItem;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem aboutItem;

    public TextEditor() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Search menu
        JMenu searchMenu = new JMenu("Search");
        searchItem = new JMenuItem("Find...");

        searchMenu.add(searchItem);

        // View menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem timeDateItem = new JMenuItem("Time & Date");

        viewMenu.add(timeDateItem);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        // Add action listeners to menu items
        newItem.addActionListener(e -> createNewWindow());
        openItem.addActionListener(e -> handleOpen());
        saveItem.addActionListener(e -> handleSave());
        exitItem.addActionListener(e -> System.exit(0));
        searchItem.addActionListener(e -> handleSearch());
        timeDateItem.addActionListener(e -> handleTimeDate());
        aboutItem.addActionListener(e -> handleAbout());

//      Edit
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        JMenuItem editMenu = new JMenu("Edit");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        menuBar.add(editMenu);
        cutItem.addActionListener(e -> handleCut());
        copyItem.addActionListener(e -> handleCopy());
        pasteItem.addActionListener(e -> handlePaste());
    }

    private void createNewWindow() {
        new TextEditor();
    }

    private void handleOpen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                textArea.read(br, null);
                br.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleSave() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (PrintWriter writer = new PrintWriter(new FileWriter(selectedFile))) {
                writer.print(textArea.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleSearch() {
        String wordToFind = JOptionPane.showInputDialog(this, "Enter word to find:");
        if (wordToFind != null && !wordToFind.isEmpty()) {
            String text = textArea.getText();
            int index = text.indexOf(wordToFind);
            if (index >= 0) {
                textArea.select(index, index + wordToFind.length());
            } else {
                JOptionPane.showMessageDialog(this, "Word not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void handleTimeDate() {
        Date date = new Date();
        textArea.insert(date.toString(), textArea.getCaretPosition());
    }

    private void handleAbout() {
        JOptionPane.showMessageDialog(this, "Developed by Team Members\nA simple text editor.", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleCut() {
        textArea.cut();
    }

    private void handleCopy() {
        textArea.copy();
    }

    private void handlePaste() {
        textArea.paste();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new TextEditor();
        });
    }
}