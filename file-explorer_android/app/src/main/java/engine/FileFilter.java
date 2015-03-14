package engine;

import java.io.File;

/**
 * Created by dmitry on 1/31/15.
 * Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
 */
public class FileFilter {

    private static final String[] imageFileExtensions = new String[]{"jpg", "png", "gif", "jpeg"};

    public static boolean isImage(File file) {
        if (file != null) {
            for (String extension : imageFileExtensions) {
                if (file.getName().toLowerCase().endsWith(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

}
