package com.boyko.ifuture.log_finder.gui;

import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {

    public TreePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(250, 400));
        JLabel countFiles = new JLabel("Текст обнаружен в данных файлах:");
        this.add(countFiles, BorderLayout.NORTH);
        JTree treeModel = new JTree();
        JScrollPane scrollPane = new JScrollPane(treeModel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
