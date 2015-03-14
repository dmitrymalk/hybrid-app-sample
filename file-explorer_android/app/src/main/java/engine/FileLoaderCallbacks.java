package engine;

/**
 * file-explorer_android
 * Created by Dmitry Malkovich on 3/8/15.
 * Copyright (c) 2015 Dmitry Malkovich. All rights reserved.
 */
public interface FileLoaderCallbacks {

    public interface FragmentLoaderCallbacks {

        public void showFileListFragment(FileSystem.PredefinedFolders folderIndex);

        /**
         * Shows {@code FileListFragment}.
         *
         * @param folder The folder to send as argument.
         * @param clearBackStack if {@code true} the stack will be cleared for support fragment manager.
         * @param addToBackStack if {@code true} the fragment will be added to the back stack.
         */
        public void showFileListFragment(LocalFolder folder, boolean clearBackStack, boolean addToBackStack);

    }

}
