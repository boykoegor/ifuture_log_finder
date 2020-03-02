package com.boyko.ifuture.log_finder.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final Dimension WINDOW_SIZE = new Dimension(800, 600);

    public MainWindow() {
        super("Поисковик текста");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowAtCenter();

        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(new TreePanel());
        panel.setRightComponent(new PreviewTabbedPanel());

        Container window = this.getContentPane();
        window.add(new TopPanel(), BorderLayout.NORTH);
        window.add(panel, BorderLayout.CENTER);
    }

    private void setWindowAtCenter() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - WINDOW_SIZE.width / 2, dimension.height / 2 - WINDOW_SIZE.height / 2, WINDOW_SIZE.width, WINDOW_SIZE.height);
    }
}
