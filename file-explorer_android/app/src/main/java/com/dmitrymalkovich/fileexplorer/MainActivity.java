package com.dmitrymalkovich.fileexplorer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import engine.FileLoaderCallbacks;
import engine.FileSystem;
import engine.LocalFile;
import engine.LocalFolder;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by dmitry.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public class MainActivity extends ActionBarActivity
        implements FileLoaderCallbacks.FragmentLoaderCallbacks, FileListFragment.OnFileListFragmentInteractionListener {

    public static String LOG_TAG = MainActivity.class.getSimpleName();
    private FileSystem mFileSystem = new FileSystemImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);

        if (savedInstanceState == null) {
            showFileListFragment(FileSystem.PredefinedFolders.STORAGE);
        }
    }

    @Override
    public void showFileListFragment(FileSystem.PredefinedFolders folderIndex) {
        if (mFileSystem != null) {
            showFileListFragment(mFileSystem.getFolder(folderIndex),
                /* clear back stack */true, /* add to back stack */false);
        } else {
            Log.e(LOG_TAG, "Cannot open FileListFragment. Folder to show was not found");
        }
    }

    @Override
    public void showFileListFragment(LocalFolder folder, boolean clearBackStack, boolean addToBackStack) {
        if (clearBackStack) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        Fragment fragment = FileListFragment.newInstance(folder);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onFileListFragmentInteraction(LocalFile file) {
        if (file instanceof LocalFolder) {
            showFileListFragment((LocalFolder) file, /* clear back stack */false, /* add to back stack */true);
        }
    }

}
