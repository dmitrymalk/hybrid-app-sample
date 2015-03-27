package com.dmitrymalkovich.fileexplorer;

import android.net.Uri;
import android.util.Log;

import engine.Thumbnail;

import java.io.File;
import java.io.Serializable;

import engine.LocalFile;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public class LocalFileImpl extends LocalFile implements Serializable {

    private static String LOG_TAG = LocalFileImpl.class.getSimpleName();

    /**
     * Constructor.
     *
     * @param file The file, which is stored in the file system.
     */
    public LocalFileImpl(File file) {
        super(file);
        mThumbnail = new ThumbnailImpl(this);
    }

    @Override
    public String getUriAsString() {
        if (mFile != null) {
            Uri uri = Uri.fromFile(mFile);
            if (uri != null) {
                return uri.toString();
            } else {
                Log.e(LOG_TAG, "Cannot get string from uri.");
            }
        } else {
            Log.e(LOG_TAG, "Cannot return uri. Got null pointer.");
        }
        return "";
    }

}
