package engine;

import java.io.File;
import java.util.List;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public abstract class FileSystemUtils {

    public static String LOG_TAG = FileSystemUtils.class.getSimpleName();

    /**
     * Constructor.
     */
    public FileSystemUtils() {}

    /**
     * Returns all files in specified folder as list.
     *
     * @param folder The folder in which we will find files.
     * @param list The list of the results.
     * @return {@code true} if we got no errors, otherwise {@code false}.
     */
    public abstract boolean findFilesInFolder(final File folder, final List<LocalFile> list);
}
