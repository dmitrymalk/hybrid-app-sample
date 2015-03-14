package engine;

import java.io.File;
import java.io.Serializable;

/**
 * Created by dmitry on 12/27/14.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public interface LocalFile extends Serializable {

    String getDisplayName();
    int getDisplayThumbnail();
    String getPath();
    File getFile();
    String getUriAsString();

}
