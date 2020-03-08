package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.model.FileInfo;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;

public class PreviewTabbedPanel extends JPanel {

    private JTabbedPane tabbedPane = new JTabbedPane();

    public PreviewTabbedPanel() {

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 400));

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public void showFile(FileInfo fileInfo) {
        if (checkIsDirectory(fileInfo)) return;

        JTextArea textArea = addTab(fileInfo.getName());
        try {
            FileReader reader = new FileReader(String.valueOf(fileInfo.getPath()));
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            while (line != null) {
                textArea.append(line + "\n");
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkIsDirectory(FileInfo fileInfo) {
        if (new File(fileInfo.getPath()).isDirectory()){
            return true;
        }
        return false;
    }

    private JTextArea addTab(String tabName) {
        JTextArea view = new JTextArea();
        tabbedPane.addTab(tabName, new JScrollPane(view));
        int index = tabbedPane.indexOfTab(tabName);
        JPanel pnlTab = new JPanel(new GridBagLayout());
        pnlTab.setOpaque(false);
        JLabel lblTitle = new JLabel(tabName);
        JButton btnClose = new JButton("x");
        btnClose.setSize(new Dimension(20, 20));
        btnClose.setPreferredSize(new Dimension(20, 20));
        btnClose.setMaximumSize(new Dimension(20, 20));

//        btnClose.setOpaque(false);
//        btnClose.setContentAreaFilled(false);
//        btnClose.setBorderPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        pnlTab.add(lblTitle, gbc);

        gbc.gridx++;
        gbc.weightx = 0;
        pnlTab.add(btnClose, gbc);

        tabbedPane.setTabComponentAt(index, pnlTab);

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = tabbedPane.getSelectedIndex();
                if (select >= 0) {
                    tabbedPane.removeTabAt(select);
                }
            }
        });

        return view;
    }

    public void refresh() {
        tabbedPane.removeAll();
    }
}