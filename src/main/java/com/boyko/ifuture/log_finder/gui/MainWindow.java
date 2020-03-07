package com.boyko.ifuture.log_finder.gui;

import com.boyko.ifuture.log_finder.EventListener;
import com.boyko.ifuture.log_finder.EventListener2;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
    private PreviewTabbedPanel tabbedPanel = new PreviewTabbedPanel();


    public MainWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowAtCenter();

        EventListener2 listener2 = new EventListener2() {
            @Override
            public void onClickDo(String path) {
                tabbedPanel.showFile(path);
            }
        };
        TreePanel tree = new TreePanel(listener2);

        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        EventListener listener = new EventListener() {
            @Override
            public void onSearchActivated(String path, String extension, String content) {
                tree.refresh(path, extension, content);
            }
        };


        panel.setLeftComponent(tree);
        panel.setRightComponent(tabbedPanel);

        Container window = this.getContentPane();
        window.add(new TopPanel(listener), BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);
    }

    private void setWindowAtCenter() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - WINDOW_SIZE.width / 2, dimension.height / 2 - WINDOW_SIZE.height / 2,
                WINDOW_SIZE.width, WINDOW_SIZE.height);
    }

}