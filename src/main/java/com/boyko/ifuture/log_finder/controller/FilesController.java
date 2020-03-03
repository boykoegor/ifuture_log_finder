package com.boyko.ifuture.log_finder.controller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.io.IOException;

public class FilesController {

    private final static String encoding = "UTF-8";
    private final static Logger logger = Logger.getLogger(FilesController.class);

    public static TreeModel getTreeFiles(String filePath) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Main node");
        addNodes(root, filePath);
        return new DefaultTreeModel(root);
    }

    private static boolean addNodes(DefaultMutableTreeNode node, String filePath) {
        File file = new File(filePath);
        DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(file.getName());
        if (file.isDirectory()) {
            try {
                boolean haveAppropriateFiles = false;
                for (File listFile : file.listFiles()) {
                    if (addNodes(newChild, listFile.getPath())) {
                        haveAppropriateFiles = true;
                    }
                }
                if (haveAppropriateFiles) {
                    System.out.println("need this dir  " + filePath);
                    node.add(newChild);
                    return true;
                }
            } catch (NullPointerException e) {
                logger.error("can't get list of files for path : " + file.getName());
            }
        } else {
            if (isAppropriate(file)) {
                System.out.println("appropriate file = " + file.getName());
                node.add(newChild);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean isAppropriate(File file) {
            return  checkExtension(file) && checkContent(file);
    }

    private static boolean checkContent(File file) {
        try {
            return FileUtils.readFileToString(file, encoding).contains("isAppropriate");
        } catch (IOException e) {
            logger.error("Unable to check is file " + file.getName() + " appropriate"); //todo: check how to log exceptions
        }
        return false;
    }

    private static boolean checkExtension(File file) {
        return file.getName().endsWith(".java");
    }
}
