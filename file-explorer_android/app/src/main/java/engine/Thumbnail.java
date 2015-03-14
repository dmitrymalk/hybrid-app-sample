package engine;

import java.io.Serializable;

/**
 * Created by dmitry on 1/18/15.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public abstract class Thumbnail implements Serializable {

    public static int NOT_DEFINED = 0;
    private int mResourceId = NOT_DEFINED;

    public Thumbnail(LocalFile file)
    {
        if (file instanceof LocalFolder) {
            mResourceId = getDefaultFolderResourceId();
        } else
        {
            mResourceId = getDefaultFileResourceId();
        }
    }

    public abstract int getDefaultFileResourceId();

    public abstract int getDefaultFolderResourceId();

    public int getResourceId() {
        return mResourceId;
    }

    public void setResourceId(int mResourceId) {
        this.mResourceId = mResourceId;
    }

}
