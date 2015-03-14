package com.dmitrymalkovich.fileexplorer;

import java.io.File;

import engine.LocalFolder;

/**
 * file-explorer_android
 * Created by Dmitry Malkovich on 3/8/15.
 * Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
 */
public class LocalFolderImpl extends LocalFolder {
    /**
     * Constructor
     * @param file The file, which is stored in the file system.
     */
    public LocalFolderImpl(File file) {
        super(file);
        mThumbnail = new ThumbnailImpl(this);
    }
}
