package com.boyko.ifuture.log_finder.controller;

import com.boyko.ifuture.log_finder.model.FileInfo;

import javax.swing.tree.DefaultMutableTreeNode;

public class FileNode extends DefaultMutableTreeNode {
    private FileInfo info;

    public FileNode(FileInfo info) {
        this.info = info;
    }

    public FileInfo getInfo() {
        return info;
    }

    public void setInfo(FileInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return info.getName();
    }
}
