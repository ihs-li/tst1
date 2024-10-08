package org.example;
import javax.swing.*;
import java.awt.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
// 在MainEditor类中添加RSyntaxTextArea的导入
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
public class MainEditor extends JFrame {
    private RSyntaxTextArea textArea;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsPDFMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem selectTextMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem pasteMenuItem;
    private JMenuItem cutMenuItem;
    private JMenuItem searchWordMenuItem;
    private JMenuItem aboutMenuItem;
    //    private JMenuItem searchMenuItem;
    private JMenuItem printMenuItem;
    private JMenuItem timeDateMenuItem;
    public RSyntaxTextArea getTextArea() {
        return textArea;
    }
    public MainEditor() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // 初始化RSyntaxTextArea
        textArea = new RSyntaxTextArea();
        add(new JScrollPane(textArea));
        createMenuBar();
        setVisible(true);
    }
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
//      File
        JMenu fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        saveAsPDFMenuItem = new JMenuItem("Save As PDF");
        printMenuItem = new JMenuItem("Print");
        exitMenuItem = new JMenuItem("Exit");
//        Edit
        JMenu editMenu = new JMenu("Edit");
        selectTextMenuItem = new JMenuItem("Select text");
        copyMenuItem = new JMenuItem("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        cutMenuItem = new JMenuItem("Cut");
//        Search
        JMenu searchMenu = new JMenu("Search");
        searchWordMenuItem = new JMenuItem("Search Word");
//      View
        JMenu viewMenu = new JMenu("View");
//        searchMenuItem = new JMenuItem("Search");
        timeDateMenuItem = new JMenuItem("Time & Date");
//       Help
        JMenu helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        // Add menu items to menus
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsPDFMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(printMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editMenu.add(selectTextMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(cutMenuItem);
        searchMenu.add(searchWordMenuItem);
//        viewMenu.add(searchMenuItem);
        viewMenu.add(timeDateMenuItem);
        helpMenu.add(aboutMenuItem);
        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(searchMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        // Bind actions to menu items
        bindActions();
    }
    private void bindActions() {
        newMenuItem.addActionListener(e -> FileOperations.createNewDocument(this));
        openMenuItem.addActionListener(e -> FileOperations.openDocument(this));
        saveMenuItem.addActionListener(e -> FileOperations.saveDocument(this));
        exitMenuItem.addActionListener(e -> System.exit(0));
        cutMenuItem.addActionListener(e -> textArea.cut());
        copyMenuItem.addActionListener(e -> textArea.copy());
        pasteMenuItem.addActionListener(e -> textArea.paste());
        aboutMenuItem.addActionListener(e -> HelpOperations.showAbout());
        searchWordMenuItem.addActionListener(e -> SearchOperations.searchWord(textArea));
        printMenuItem.addActionListener(e -> FileOperations.printDocument(this));
        selectTextMenuItem.addActionListener(e -> textArea.selectAll());
        timeDateMenuItem.addActionListener(e -> FileOperations.handleTimeDate(this));
        saveAsPDFMenuItem.addActionListener(e -> FileOperations.saveAsPDFDocument(this));
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainEditor();
        });
    }
}