package engine;

/**
 * Created by dmitry on 12/25/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public interface FileSystem {

    public static enum PredefinedFolders {
        ROOT,
        STORAGE,
        DCIM,
        DOWNLOADS,
        MUSIC,
        PICTURES,
        DOCUMENTS,
        MOVIES
    }

    public LocalFolder getFolder(PredefinedFolders folderIndex);

}