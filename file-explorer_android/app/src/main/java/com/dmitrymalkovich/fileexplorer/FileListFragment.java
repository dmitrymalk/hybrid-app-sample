package com.dmitrymalkovich.fileexplorer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import engine.LocalFile;
import engine.LocalFolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public class FileListFragment extends android.support.v4.app.Fragment {

    public static String LOG_TAG = FileListFragment.class.getSimpleName();
    private static final String ARG_FILES = "files";
    private RecyclerView mRecyclerView;
    private LocalFolder mCurrentFolder = null;
    private OnFileListFragmentInteractionListener mListener;
    private ActionMode mActionMode;

    /**
     * The callbacks for {@link FileListAdapter#setCallbacks}.
     */
    public interface FileListCallbacks {
        void openFolder(LocalFolder folder, int position);
        void openFile(LocalFile file, int position);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param folder The folder to show.
     * @return A new instance of fragment FileListFragment.
     */
    public static FileListFragment newInstance(final LocalFolder folder) {
        FileListFragment fragment = new FileListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_FILES, folder);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // list
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.file_list_view);
        mRecyclerView.setHasFixedSize(false);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        // The first loading
        if (getArguments() != null && savedInstanceState == null) {
            List<LocalFile> files = new ArrayList<>();
            if (getArguments().containsKey(ARG_FILES)) {
                Serializable s = getArguments().getSerializable(ARG_FILES);
                if (s instanceof LocalFolder) {
                    LocalFolder folder = (LocalFolder) s;
                    files = folder.getChildFiles(new FileSystemUtilsImpl());
                    mCurrentFolder = folder;
                }
            }
            setUpAdapter(files);
        }
        // Recycler View Touch Events
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private static final int HEADER_HIDE_ANIM_DURATION = 300;
            private int counter = 0;
            private boolean isHidden = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // The view is not scrolling.
                if (RecyclerView.SCROLL_STATE_IDLE == newState)
                {
                    counter = 0;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                counter+= dy;
                if (counter > 100 && !isHidden)
                {
                    // Hide Toolbar
                    final Toolbar include_toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                    include_toolbar.animate()
                            .translationY(-include_toolbar.getBottom())
                            .setDuration(HEADER_HIDE_ANIM_DURATION)
                            .setInterpolator(new DecelerateInterpolator());
                    // Set flags
                    isHidden = true;
                }

                if (counter < 100 && isHidden)
                {
                    // Show Toolbar
                    final Toolbar include_toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                    include_toolbar.animate()
                            .translationY(0)
                            .setDuration(HEADER_HIDE_ANIM_DURATION)
                            .setInterpolator(new DecelerateInterpolator());
                    // Reset flags & counters
                    isHidden = false;
                    counter = 0;
                }

            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrentFolder != null) {
            outState.putSerializable(ARG_FILES, mCurrentFolder);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        // Restore from saved state
        if (savedInstanceState != null) {
            Serializable s = savedInstanceState.getSerializable(ARG_FILES);
            if (s instanceof LocalFolder) {
                LocalFolder folder = (LocalFolder) s;
                List<LocalFile> files = folder.getChildFiles(new FileSystemUtilsImpl());
                mCurrentFolder = folder;
                setUpAdapter(files);
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFileListFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFileListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (mActionMode != null) {
            mActionMode.finish();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFileListFragmentInteractionListener {
        public void onFileListFragmentInteraction(LocalFile file);
    }

    /**
     * Sets the new data to the adapter.
     *
     * @param files The list of the {@link com.dmitrymalkovich.fileexplorer.engine.LocalFile}.
     */
    private void setUpAdapter(List<LocalFile> files)
    {
        final FileListAdapter adapter = new FileListAdapter(files);
        mRecyclerView.setAdapter(adapter);
        adapter.setCallbacks(new FileListCallbacks() {

            @Override
            public void openFolder(LocalFolder folder, int position) {
                if (mListener != null) {
                    mListener.onFileListFragmentInteraction(folder);
                    final Toolbar include_toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                    include_toolbar.animate()
                            .translationY(0)
                            .setDuration(0)
                            .setInterpolator(new DecelerateInterpolator());
                } else {
                    Log.e(LOG_TAG, "Cannot open folder, listener is null.");
                }
            }

            @Override
            public void openFile(LocalFile file, int position) {
                if (mListener != null) {
                    mListener.onFileListFragmentInteraction(file);
                    final Toolbar include_toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
                    include_toolbar.animate()
                            .translationY(0)
                            .setDuration(0)
                            .setInterpolator(new DecelerateInterpolator());
                } else {
                    Log.e(LOG_TAG, "Cannot open file, listener is null.");
                }
            }

        });
    }

}