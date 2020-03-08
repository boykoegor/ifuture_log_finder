package com.boyko.ifuture.log_finder;

import com.boyko.ifuture.log_finder.model.FileInfo;

public interface TreeListener {
    void onNodeSelected(FileInfo fileInfo);
}