package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.TreeListener;
import com.boyko.ifuture.log_finder.controller.FileNode;
import com.boyko.ifuture.log_finder.controller.FilesController;

import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    private JTree tree;
    private TreeListener treeListener;
    private String rootPath;
    private String ext;

    public void refresh(String path, String extension, String content) {
        tree.setModel(FilesController.getTreeFiles(path, extension, content));
        rootPath = path;
        ext = extension;
    }

    public TreePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 400));
        JLabel countFiles = new JLabel("Текст обнаружен в данных файлах:");
        this.add(countFiles, BorderLayout.NORTH);
        tree = new JTree(FilesController.getTreeFiles("", ".log", ""));
        tree.setRootVisible(false);
        JScrollPane scrollPane = new JScrollPane(tree,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);
        tree.addTreeSelectionListener(e -> {
            FileNode node = (FileNode) tree.getLastSelectedPathComponent();
            if (node != null){
                treeListener.onNodeSelected(node.getInfo());
            }
        });
    }

    public TreePanel(TreeListener listener) {
        this();
        this.treeListener = listener;
    }
}
