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

    public static TreeModel getTreeFiles(String filePath, String extension) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Main node");
        addNodes(root, filePath, extension);
        return new DefaultTreeModel(root);
    }

    private static boolean addNodes(DefaultMutableTreeNode node, String filePath, String extension) {
        File file = new File(filePath);
        DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(file.getName());
        if (file.isDirectory()) {
            try {
                boolean haveAppropriateFiles = false;
                for (File listFile : file.listFiles()) {
                    if (addNodes(newChild, listFile.getPath(), extension)) {
                        haveAppropriateFiles = true;
                    }
                }
                if (haveAppropriateFiles) {
//                    System.out.println("need this dir  " + filePath);
                    node.add(newChild);
                    return true;
                }
            } catch (NullPointerException e) {
                logger.error("can't get list of files for path : " + file.getName());
            }
        } else {
            if (isAppropriate(file, extension)) {
//                System.out.println("appropriate file = " + file.getName());
                node.add(newChild);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean isAppropriate(File file, String extension) {
            return  checkExtension(file, extension) && checkContent(file);
    }

    private static boolean checkContent(File file) {
        try {
            return FileUtils.readFileToString(file, encoding).contains("123");
        } catch (IOException e) {
            logger.error("Unable to check is file " + file.getName() + " appropriate"); //todo: check how to log exceptions
        }
        return false;
    }

    private static boolean checkExtension(File file, String extension) {
        return file.getName().endsWith(extension);
    }
}
