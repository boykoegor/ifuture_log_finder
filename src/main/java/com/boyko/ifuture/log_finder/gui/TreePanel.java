package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.EventListener2;
import com.boyko.ifuture.log_finder.controller.FilesController;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreePanel extends JPanel implements MouseListener {
    private JTree tree;
    private EventListener2 listener2;
    private String rootPath;
    private String ext;

    public void refresh(String path, String extension, String content) {
        tree.setModel(FilesController.getTreeFiles(path, extension, content));
        System.out.println("nice");
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
        tree.addMouseListener(this);
    }

    public TreePanel(EventListener2 listener) {
        this();
        this.listener2 = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            TreePath pathOfFile = tree.getPathForLocation(e.getX(), e.getY());
            if ((pathOfFile != null) && pathOfFile.getLastPathComponent().toString().endsWith(ext)) {
                Object[] path = pathOfFile.getPath();
                StringBuffer sb = new StringBuffer();
                for (int i = 2; i < path.length; i++) {
                    if (i != path.length - 1)
                        sb.append(path[i] + "/");
                    else
                        sb.append(path[i]);
                }
                listener2.onClickDo(rootPath + "/" + sb);

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
