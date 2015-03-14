package com.dmitrymalkovich.fileexplorer;

import engine.LocalFile;
import engine.Thumbnail;

/**
 * file-explorer_android
 * Created by Dmitry Malkovich on 3/8/15.
 * Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
 */
public class ThumbnailImpl extends Thumbnail {

    public ThumbnailImpl(LocalFile file)
    {
        super(file);
    }

    @Override
    public int getDefaultFileResourceId() {
        return R.drawable.ic_insert_drive_file_white_24dp;
    }

    @Override
    public int getDefaultFolderResourceId() {
        return R.drawable.ic_folder_white_24dp;
    }
}
