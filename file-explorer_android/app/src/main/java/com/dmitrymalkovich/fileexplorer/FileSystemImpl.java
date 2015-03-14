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
            mRoot.setPredefinedThumbnail(R.drawable.ic_android_grey600_24dp);
        }
        return mRoot;
    }

    private LocalFolder getStorage() {
        if (mStorage == null) {
            File root = Environment.getExternalStorageDirectory();
            mStorage = new LocalFolderImpl(root);
            mStorage.setPredefinedThumbnail(R.drawable.ic_home_grey600_24dp);
        }
        return mStorage;
    }

    private LocalFolder getDcim() {
        if (mDcim == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            mDcim = new LocalFolderImpl(root);
            mDcim.setPredefinedThumbnail(R.drawable.ic_photo_camera_grey600_24dp);
        }
        return mDcim;
    }

    private LocalFolder getDownloads() {
        if (mDownloads == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            mDownloads = new LocalFolderImpl(root);
            mDownloads.setPredefinedThumbnail(R.drawable.ic_get_app_grey600_24dp);
        }
        return mDownloads;
    }

    private LocalFolder getMusic() {
        if (mMusic == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            mMusic = new LocalFolderImpl(root);
            mMusic.setPredefinedThumbnail(R.drawable.ic_my_library_music_grey600_24dp);
        }
        return mMusic;
    }

    private LocalFolder getPictures() {
        if (mPictures == null) {
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            mPictures = new LocalFolderImpl(root);
            mPictures.setPredefinedThumbnail(R.drawable.ic_perm_media_grey600_24dp);
        }
        return mPictures;
    }

    private LocalFolder getDocuments() {
        // TODO: Possible exception on 4.3 (Galaxy S3, HTC One X, ...)
        try {
            if (mDocuments == null) {
                File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                mDocuments = new LocalFolderImpl(root);
                mDocuments.setPredefinedThumbnail(R.drawable.ic_insert_drive_file_grey600_24dp);
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
            mMovies.setPredefinedThumbnail(R.drawable.ic_theaters_grey600_24dp);
        }
        return mMovies;
    }

}
