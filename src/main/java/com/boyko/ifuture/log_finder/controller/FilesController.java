package com.boyko.ifuture.log_finder.controller;

import com.boyko.ifuture.log_finder.Main;
import org.apache.log4j.Logger;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;

public class FilesController {

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
                if (haveAppropriateFiles){
                    System.out.println("need this dir  " + filePath);
                    node.add(newChild);
                    return true;
                }
            } catch (NullPointerException e) {
                logger.error("can't get list of files for path : " + file.getName());
            }
        } else {
            if (isAppropriate(file)){
                System.out.println("appropriate file = " + file.getName());
                node.add(newChild);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean isAppropriate(File file){
        return file.getName().endsWith(".java");

    }
}
