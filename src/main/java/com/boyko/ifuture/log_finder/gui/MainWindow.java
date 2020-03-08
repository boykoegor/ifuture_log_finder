package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.SearchListener;
import com.boyko.ifuture.log_finder.TreeListener;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);

    public MainWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowAtCenter();

        PreviewTabbedPanel tabbedPanel = new PreviewTabbedPanel();
        TreeListener treeListener = fileInfo -> tabbedPanel.showFile(fileInfo);
        TreePanel tree = new TreePanel(treeListener);

        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        SearchListener searchListener = (path, extension, content) -> {
            tree.refresh(path, extension, content);
            tabbedPanel.refresh();
        };

        panel.setLeftComponent(tree);
        panel.setRightComponent(tabbedPanel);

        Container window = this.getContentPane();
        window.add(new SearchPanel(searchListener), BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);
    }

    private void setWindowAtCenter() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - WINDOW_SIZE.width / 2, dimension.height / 2 - WINDOW_SIZE.height / 2,
                WINDOW_SIZE.width, WINDOW_SIZE.height);
    }

}