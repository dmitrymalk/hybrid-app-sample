package engine;

import java.io.File;
import java.io.Serializable;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public abstract class LocalFile implements Serializable {

    private static String LOG_TAG = LocalFile.class.getSimpleName();
    protected File mFile;
    protected Thumbnail mThumbnail;

    /**
     * Constructor.
     *
     * @param file The file, which is stored in the file system.
     */
    public LocalFile(File file) {
        mFile = file;
    }

    public String getDisplayName() {
        if (mFile != null) {
            return mFile.getName();
        } else {
            System.out.println( LOG_TAG + " Cannot return correct path. Got null pointer.");
            return "";
        }
    }

    public int getDisplayThumbnail() {
        return mThumbnail.getResourceId();
    }

    public String getPath() {
        if (mFile != null) {
            return mFile.getParent();
        } else {
            System.out.println( LOG_TAG + " Cannot return correct file path. Got null pointer.");
            return "";
        }
    }

    public File getFile() {
        if (mFile != null) {
            return mFile;
        } else {
            System.out.println( LOG_TAG + " Cannot return file. Got null pointer.");
            return null;
        }
    }

    public String getUriAsString()
    {
        return null;
    }
}
