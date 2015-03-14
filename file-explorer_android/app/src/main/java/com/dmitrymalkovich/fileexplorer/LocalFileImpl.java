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
public class LocalFileImpl implements LocalFile, Serializable {

    private static String LOG_TAG = LocalFileImpl.class.getSimpleName();
    private File mFile;
    private Thumbnail mThumbnail;

    /**
     * Constructor.
     *
     * @param file The file, which is stored in the file system.
     */
    public LocalFileImpl(File file) {
        mFile = file;
        mThumbnail = new ThumbnailImpl(this);
    }

    @Override
    public String getDisplayName() {
        if (mFile != null) {
            return mFile.getName();
        } else {
            Log.e(LOG_TAG, "Cannot return correct path. Got null pointer.");
            return "";
        }
    }

    @Override
    public int getDisplayThumbnail() {
       return mThumbnail.getResourceId();
    }

    @Override
    public String getPath() {
        if (mFile != null) {
            return mFile.getParent();
        } else {
            Log.e(LOG_TAG, "Cannot return correct file path. Got null pointer.");
            return "";
        }
    }

    @Override
    public File getFile() {
        if (mFile != null) {
            return mFile;
        } else {
            Log.e(LOG_TAG, "Cannot return file. Got null pointer.");
            return null;
        }
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
