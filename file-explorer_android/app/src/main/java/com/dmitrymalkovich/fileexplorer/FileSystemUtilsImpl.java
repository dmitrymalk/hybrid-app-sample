package com.dmitrymalkovich.fileexplorer;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.List;

import engine.FileSystemUtils;
import engine.LocalFile;

/**
 * file-explorer_android
 * Created by Dmitry Malkovich on 3/8/15.
 * Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
 */
public class FileSystemUtilsImpl extends FileSystemUtils {

    public static String LOG_TAG = FileSystemUtils.class.getSimpleName();

    /**
     * Constructor.
     */
    public FileSystemUtilsImpl() {}

    /**
     * Returns all files in specified folder as list.
     *
     * @param folder The folder in which we will find files.
     * @param list The list of the results.
     * @return {@code true} if we got no errors, otherwise {@code false}.
     */
    @Override
    public boolean findFilesInFolder(final File folder, final List<LocalFile> list) {
        if (isExternalStorageReadable()) {
            if (list != null && folder != null) {
                if (folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (file.isDirectory())
                        {
                            list.add(new LocalFolderImpl(file));
                        }
                        else
                        {
                            list.add(new LocalFileImpl(file));
                        }
                    }
                    return true;
                } else {
                    Log.e(LOG_TAG, folder.toString() + " is not a folder.");
                    return false;
                }
            } else {
                Log.e(LOG_TAG, "Got null pointers.");
                return false;
            }
        }
        else
        {
            Log.e(LOG_TAG, "External Storage is not readable.");
            return false;
        }
    }

    /**
     *  Checks if external storage is available for read and write
     */
    public static  boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     *  Checks if external storage is available to at least read
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

}
