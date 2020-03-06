package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.controller.FilesController;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreePanel extends JPanel implements MouseListener {
    private JTree tree;

    public void refresh(String path, String extension, String content) {
        tree.setModel(FilesController.getTreeFiles(path, extension, content));
        System.out.println("nice");
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
        tree.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            if (path != null) {
                System.out.println(path.getLastPathComponent().toString());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
