package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.EventListener;

import javax.swing.*;
import java.io.File;

public class TopPanel extends JPanel {

    private EventListener listener;
    private File selectedFile;
    private JLabel selectedPath = new JLabel("Путь не указан");
    private JLabel fileFormatLabel = new JLabel("Формат файла");
    private JLabel textLabel = new JLabel("Текст:");
    private JTextField text = new JTextField();
    private JLabel pathLabel = new JLabel("Путь:");
    private JTextField fileFormat = new JTextField(".log");
    private JButton path = new JButton("...");
    private JButton search = new JButton("Искать");

    public TopPanel() {

        path.addActionListener(e -> selectPath(selectedPath));
        search.addActionListener(e -> {

                listener.onSearchActivated(selectedPath.getText());

        });

        place();
    }

    public TopPanel(EventListener listener) {
        this();
        this.listener = listener;
    }

    private void place() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
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
    }

    private void selectPath(JLabel selectedPath) {
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
    }
}
