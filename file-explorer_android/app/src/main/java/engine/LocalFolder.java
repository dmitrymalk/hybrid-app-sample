package engine;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public abstract class LocalFolder implements LocalFile, Serializable {

    private static String LOG_TAG = LocalFolder.class.getSimpleName();
    private File mFile;
    private List<LocalFile> mChildFiles;
    protected Thumbnail mThumbnail;

    /**
     * Constructor
     * @param file The file, which is stored in the file system.
     */
    public LocalFolder(File file) {
        mFile = file;
    }

    public List<LocalFile> getChildFiles(FileSystemUtils fileSystem) {
        if (mChildFiles == null) {
            mChildFiles = new ArrayList<>();
            if (fileSystem.findFilesInFolder(mFile, mChildFiles)) {
                return mChildFiles;
            } else {
                return mChildFiles;
            }
        }
        else
        {
            return mChildFiles;
        }
    }

    @Override
    public String getDisplayName()
    {
        if (mFile != null) {
            return mFile.getName();
        } else {
            System.out.println(LOG_TAG + ": Cannot return correct folder name. Got null pointer.");
            return "";
        }
    }

    @Override
    public int getDisplayThumbnail()
    {
        return mThumbnail.getResourceId();
    }

    @Override
    public String getPath() {
        if (mFile != null) {
            return mFile.getPath();
        } else {
            System.out.println(LOG_TAG + ": Cannot return correct path. Got null pointer.");
            return "";
        }
    }

    public void setPredefinedThumbnail(int resourceId) {
        mThumbnail.setResourceId(resourceId);
    }

    @Override
    public File getFile() {
        if (mFile != null) {
            return mFile;
        } else {
            System.out.println(LOG_TAG + ": Cannot return file. Got null pointer.");
            return null;
        }
    }

    public String getUriAsString()
    {
        return null;
    }

}
