package com.boyko.ifuture.log_finder.controller;

import com.boyko.ifuture.log_finder.model.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.io.File;
import java.io.IOException;

public class FilesController {

    private final static String encoding = "UTF-8";
    private final static Logger logger = Logger.getLogger(FilesController.class);

    public static TreeModel getTreeFiles(String filePath, String extension, String content) {
        FileNode root = new FileNode(new FileInfo("Main node", filePath));
        addNodes(root, filePath, extension, content);
        return new DefaultTreeModel(root);
    }

    private static boolean addNodes(FileNode node, String filePath, String extension, String content) {
        File file = new File(filePath);
        FileNode newChild = new FileNode(new FileInfo (file.getName(), file.getPath()));
        if (file.isDirectory()) {
            try {
                boolean haveAppropriateFiles = false;
                for (File listFile : file.listFiles()) {
                    if (addNodes(newChild, listFile.getPath(), extension, content)) {
                        haveAppropriateFiles = true;
                    }
                }
                if (haveAppropriateFiles) {
                    node.add(newChild);
                    return true;
                }
            } catch (NullPointerException e) {
                logger.error("can't get list of files for path : " + file.getName());
            }
        } else {
            if (isAppropriate(file, extension, content)) {
                node.add(newChild);
                return true;
            }
        }
        return false;
    }

    private static boolean isAppropriate(File file, String extension, String content) {
        return checkExtension(file, extension) && checkContent(file, content);
    }

    private static boolean checkContent(File file, String content) {
        try {
            return FileUtils.readFileToString(file, encoding).contains(content);
        } catch (IOException e) {
            logger.error("Unable to check is file " + file.getName() + " appropriate"); //todo: check how to log exceptions
        }
        return false;
    }

    private static boolean checkExtension(File file, String extension) {
        return file.getName().endsWith(extension);
    }
}