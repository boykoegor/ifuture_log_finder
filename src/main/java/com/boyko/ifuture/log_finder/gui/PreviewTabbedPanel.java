package com.boyko.ifuture.log_finder.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;

public class PreviewTabbedPanel extends JPanel {

    private JTabbedPane tabbedPane = new JTabbedPane();
    final TextArea textOfFile = new TextArea();

    public PreviewTabbedPanel() {

        JButton add = new JButton("Добавить");
        JButton remove = new JButton("Удалить");

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 400));


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//               showFile("/Users/boyko.e.r/IdeaProjects/ifuture_log_finder/src/main/java/com/boyko/ifuture/log_finder/gui/MainWindow.java");
            }
        });
        remove.addActionListener(e -> removeTab());

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);

        this.add(buttons, BorderLayout.SOUTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public void showFile(String path) {
        addTab();
        try {
            FileReader reader = new FileReader(String.valueOf(path));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null ){
                textOfFile.append(line + "\n");
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void removeTab() {
        int select = tabbedPane.getSelectedIndex();
        if (select >= 0) {
            tabbedPane.removeTabAt(select);
        }
    }

    private void addTab() {
        tabbedPane.addTab("Вкладка ", new JScrollPane(textOfFile));
    }




}
