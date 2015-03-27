package engine;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public abstract class LocalFolder extends LocalFile implements Serializable {

    private static String LOG_TAG = LocalFolder.class.getSimpleName();
    private List<LocalFile> mChildFiles;

    /**
     * Constructor
     * @param file The file, which is stored in the file system.
     */
    public LocalFolder(File file) {
        super(file);
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
}
