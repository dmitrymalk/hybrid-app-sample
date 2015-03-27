package com.dmitrymalkovich.fileexplorer;

import android.os.Environment;
import android.util.Log;

import engine.LocalFolder;

import java.io.File;

import engine.FileSystem;

/**
 * Created by dmitry on 12/25/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public class FileSystemImpl implements FileSystem {

    public static String LOG_TAG = FileSystemImpl.class.getSimpleName();
    private LocalFolder mRoot;
    private LocalFolder mStorage;
    private LocalFolder mDcim;
    private LocalFolder mDownloads;
    private LocalFolder mMusic;
    private LocalFolder mPictures;
    private LocalFolder mDocuments;
    private LocalFolder mMovies;

    public FileSystemImpl() { }

    public LocalFolder getFolder(PredefinedFolders folderIndex) {
        LocalFolder folder = null;
        switch (folderIndex) {
            case ROOT:
                folder = getRoot();
                break;
            case STORAGE:
                folder = getStorage();
                break;
            case DCIM:
                folder = getDcim();
                break;
            case DOWNLOADS:
                folder = getDownloads();
                break;
            case MUSIC:
                folder = getMusic();
                break;
            case PICTURES:
                folder = getPictures();
                break;
            case DOCUMENTS:
                folder = getDocuments();
                break;
            case MOVIES:
                folder = getMovies();
                break;
            default:
                break;
        }
        return folder;
    }

    private LocalFolder getRoot() {
        if (mRoot == null) {
            File root = Environment.getRootDirectory();
            mRoot = new LocalFolderImpl(root);
        }
        return mRoot;
    }

    private LocalFolder getStorage() {
        if (mStorage == null) {
            File root = Environment.getExternalStorageDirectory();
            mStorage = new LocalFolderImpl(root);
        }
        return mStorage;
    }

    private LocalFolder getDcim() {
        if (mDcim == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            mDcim = new LocalFolderImpl(root);
        }
        return mDcim;
    }

    private LocalFolder getDownloads() {
        if (mDownloads == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            mDownloads = new LocalFolderImpl(root);
        }
        return mDownloads;
    }

    private LocalFolder getMusic() {
        if (mMusic == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            mMusic = new LocalFolderImpl(root);
        }
        return mMusic;
    }

    private LocalFolder getPictures() {
        if (mPictures == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            mPictures = new LocalFolderImpl(root);
        }
        return mPictures;
    }

    private LocalFolder getDocuments() {
        // TODO: Possible exception on 4.3 (Galaxy S3, HTC One X, ...)
        try {
            if (mDocuments == null) {
                File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                mDocuments = new LocalFolderImpl(root);
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception, cannot get DIRECTORY_DOCUMENTS");
        }
        return mDocuments;
    }

    private LocalFolder getMovies() {
        if (mMovies == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
            mMovies = new LocalFolderImpl(root);
        }
        return mMovies;
    }

}
