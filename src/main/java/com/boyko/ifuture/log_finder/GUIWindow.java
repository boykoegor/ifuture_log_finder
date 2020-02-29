package com.boyko.ifuture.log_finder;

import sun.jvm.hotspot.ui.tree.SimpleTreeModel;

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
    private JLabel thisWay = new JLabel("Путь не указан");
    private JLabel fileFormatLable = new JLabel("Формат файла");
    private JTextField fileFormat = new JTextField(".log");
    private JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    final JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel buttons = new JPanel();
    private File selectedFile;
    private JLabel countFiles = new JLabel("Текст обнаружен в данных файлах:");
    private JTree treeModel = new JTree();
    private JScrollPane scrollPane = new JScrollPane(treeModel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);




    GUIWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250, 400));

        leftPanel.add(countFiles, BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(500, 400));

        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tabbedPane.addTab("Вкладка ", new JPanel());
            }
        });
        buttons.add(add);

        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int select = tabbedPane.getSelectedIndex();
                if (select >= 0) {
                    tabbedPane.removeTabAt(select);
                }
            }
        });
        buttons.add(remove);

        rightPanel.add(buttons, BorderLayout.SOUTH);
        rightPanel.add(tabbedPane, BorderLayout.CENTER);

        panel.setLeftComponent(leftPanel);
        panel.setRightComponent(rightPanel);


        way.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser wayChooser = new JFileChooser();
                wayChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int reply = wayChooser.showDialog(null, "Выбрать папку");
                switch (reply) {
                    case JFileChooser.APPROVE_OPTION:
                        thisWay.setText(wayChooser.getSelectedFile().toString());
                        selectedFile = wayChooser.getSelectedFile();
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    case JFileChooser.ERROR_OPTION:
                        break;

                }

            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {

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
                                        .addComponent(thisWay)
                                        .addGap(20)
                                        .addComponent(fileFormatLable)
                                        .addComponent(fileFormat)))
                        .addComponent(search))
                .addComponent(panel)
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
                        .addComponent(thisWay)
                        .addGap(20)
                        .addComponent(fileFormatLable)
                        .addComponent(fileFormat))
                .addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panel)
                )
        );


    }

}
