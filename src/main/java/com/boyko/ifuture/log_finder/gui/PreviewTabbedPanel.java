package com.boyko.ifuture.log_finder.gui;

import javax.swing.*;
import java.awt.*;

public class PreviewTabbedPanel extends JPanel {

    private JTabbedPane tabbedPane = new JTabbedPane();

    public PreviewTabbedPanel() {

        JButton add = new JButton("Добавить");
        JButton remove = new JButton("Удалить");

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 400));

        add.addActionListener(e -> addTab());
        remove.addActionListener(e -> removeTab());

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);

        this.add(buttons, BorderLayout.SOUTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    private void removeTab() {
        int select = tabbedPane.getSelectedIndex();
        if (select >= 0) {
            tabbedPane.removeTabAt(select);
        }
    }

    private void addTab() {
        tabbedPane.addTab("Вкладка ", new JPanel());
    }

}
