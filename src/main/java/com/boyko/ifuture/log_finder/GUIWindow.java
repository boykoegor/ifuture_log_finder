package com.boyko.ifuture.log_finder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.event.ActionListener;

public class GUIWindow extends JFrame {

    private JLabel textLable = new JLabel("Текст:");
    private JTextField text = new JTextField();
    private JButton search = new JButton("Искать");
    private JLabel wayLabel = new JLabel("Путь:");
    private JButton way = new JButton("...");
    private JLabel fileFormatLable = new JLabel("Формат файла");
    private JTextField fileFormat = new JTextField(".log");
    private JTabbedPane tabbedPane = new JTabbedPane();


    GUIWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        way.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser wayChooser = new JFileChooser();
                wayChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int reply = wayChooser.showDialog(null, "Выбрать папку");
                switch (reply) {
                    case JFileChooser.APPROVE_OPTION:
                        File directory = wayChooser.getCurrentDirectory();
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    case JFileChooser.ERROR_OPTION:
                        break;

                }
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(textLable)
                                .addComponent(wayLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(text)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(way)
                                        .addComponent(fileFormatLable)
                                        .addComponent(fileFormat)))
                        .addComponent(search))
                .addComponent(tabbedPane)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, search, fileFormat, way);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textLable)
                        .addComponent(text)
                        .addComponent(search))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(wayLabel)
                        .addComponent(way)
                        .addComponent(fileFormatLable)
                        .addComponent(fileFormat))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(tabbedPane)
                )
        );


    }

}
