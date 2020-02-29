package com.boyko.ifuture.log_finder;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIWindow extends JFrame {

    private JLabel selectedPath = new JLabel("Путь не указан");
    private File selectedFile;
    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);

    GUIWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowAtCenter();
        JPanel topPanel = getTopPanel();

        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(getTreePanel());
        panel.setRightComponent(getPreviewTabbedPanel());

        Container window = this.getContentPane();
        window.add(topPanel, BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);
    }

    private JPanel getTopPanel() {
        JPanel topPanel = new JPanel();
        JLabel fileFormatLabel = new JLabel("Формат файла");
        JLabel textLabel = new JLabel("Текст:");
        JTextField text = new JTextField();
        JLabel pathLabel = new JLabel("Путь:");
        JTextField fileFormat = new JTextField(".log");
        JButton path = new JButton("...");
        path.addActionListener(e -> {
            JFileChooser wayChooser = new JFileChooser();
            wayChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int reply = wayChooser.showDialog(null, "Выбрать папку");
            switch (reply) {
                case JFileChooser.APPROVE_OPTION:
                    selectedPath.setText(wayChooser.getSelectedFile().toString());
                    selectedFile = wayChooser.getSelectedFile();
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                case JFileChooser.ERROR_OPTION:
                    break;
            }
        });

        JButton search = new JButton("Искать");
        search.addActionListener(e -> {
            if (selectedFile != null) {

            }
        });

        GroupLayout layout = new GroupLayout(topPanel);
        topPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(textLabel)
                                .addComponent(pathLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(text)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(path)
                                        .addComponent(selectedPath)
                                        .addGap(20)
                                        .addComponent(fileFormatLabel)
                                        .addComponent(fileFormat)))
                        .addComponent(search))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, search, fileFormat, path);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(textLabel)
                        .addComponent(text)
                        .addComponent(search))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pathLabel)
                        .addComponent(path)
                        .addComponent(selectedPath)
                        .addGap(20)
                        .addComponent(fileFormatLabel)
                        .addComponent(fileFormat))
        );
        return topPanel;
    }

    private JPanel getPreviewTabbedPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(500, 400));

        JButton add = new JButton("Добавить");
        JTabbedPane tabbedPane = new JTabbedPane();
        add.addActionListener(e -> tabbedPane.addTab("Вкладка ", new JPanel()));
        JPanel buttons = new JPanel();
        buttons.add(add);

        JButton remove = new JButton("Удалить");
        remove.addActionListener(e -> {
            int select = tabbedPane.getSelectedIndex();
            if (select >= 0) {
                tabbedPane.removeTabAt(select);
            }
        });
        buttons.add(remove);

        rightPanel.add(buttons, BorderLayout.SOUTH);
        rightPanel.add(tabbedPane, BorderLayout.CENTER);
        return rightPanel;
    }

    private JPanel getTreePanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(250, 400));

        JLabel countFiles = new JLabel("Текст обнаружен в данных файлах:");
        leftPanel.add(countFiles, BorderLayout.NORTH);
        JTree treeModel = new JTree();
        JScrollPane scrollPane = new JScrollPane(treeModel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        return leftPanel;
    }

    private void setWindowAtCenter() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - WINDOW_SIZE.width / 2, dimension.height / 2 - WINDOW_SIZE.height / 2, WINDOW_SIZE.width, WINDOW_SIZE.height);
    }
}
