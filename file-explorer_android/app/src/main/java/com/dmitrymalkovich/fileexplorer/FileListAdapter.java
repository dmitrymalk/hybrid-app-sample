package com.dmitrymalkovich.fileexplorer;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import engine.FileFilter;
import engine.LocalFile;
import engine.LocalFolder;

/**
 * Created by dmitry.
 * Copyright (c) 2014 Dmitry Malkovich. All rights reserved.
 */
public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder>
{
    private List<LocalFile> mFiles;
    private FileListFragment.FileListCallbacks mCallbacks;

    public FileListAdapter(final List<LocalFile> files)
    {
        mFiles = files;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LocalFile mFileComponent;
        public TextView textView;
        public int position;
        public FileListFragment.FileListCallbacks mCallbacks;
        public ImageView predefinedThumbnail;
        public ImageView thumbnail;
        public RelativeLayout wrapper;
        public RelativeLayout holder;

        public ViewHolder(final View v)
        {
            super(v);
            this.textView = (TextView) v.findViewById(R.id.text_view);
            this.predefinedThumbnail = (ImageView) v.findViewById(R.id.predefined_thumbnail);
            this.thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            this.wrapper = (RelativeLayout) v.findViewById(R.id.wrapper);
            this.holder = (RelativeLayout) v.findViewById(R.id.holder);
        }

        @Override
        public void onClick(View v) {
            if (mFileComponent instanceof LocalFolder) {
                mCallbacks.openFolder((LocalFolder) mFileComponent, position);
            } else {
                mCallbacks.openFile(mFileComponent, position);
            }
        }
    }

    @Override
    public FileListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_file, parent, false);

        TextView textView = (TextView) v.findViewById(R.id.text_view);

        ViewHolder vh = new ViewHolder((View) textView.getParent().getParent());
        v.setOnClickListener(vh);
        vh.mCallbacks = mCallbacks;

        return vh;
    }

    @Override
    public void onBindViewHolder(final FileListAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(mFiles.get(position).getDisplayName());
        LocalFile file = mFiles.get(position);
        holder.mFileComponent = mFiles.get(position);
        holder.position = position;

        // Loading thumbnail
        if (FileFilter.isImage(file.getFile())) {
            holder.predefinedThumbnail.setVisibility(View.VISIBLE);
            holder.thumbnail.setVisibility(View.GONE);
            holder.predefinedThumbnail.setImageResource(file.getDisplayThumbnail());
            holder.thumbnail.setImageDrawable(null);
            DisplayImageOptions options;
            options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            ImageLoader.getInstance().displayImage(file.getUriAsString(), holder.thumbnail, options,
                    new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                            if (position == holder.position) {
                                holder.predefinedThumbnail.setVisibility(View.GONE);
                                holder.thumbnail.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }
        else
        {
            holder.predefinedThumbnail.setVisibility(View.VISIBLE);
            holder.thumbnail.setVisibility(View.GONE);
            holder.predefinedThumbnail.setImageResource(file.getDisplayThumbnail());
        }

        // RecyclerView is under ToolBar, so we set marginTop for the first ListItem to ActionBar#height
        if (position == 0)
        {
            RelativeLayout relativeLayout = holder.wrapper;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();

            TypedValue tv = new TypedValue();
            if (relativeLayout.getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            {
                int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,relativeLayout.getContext().getResources().getDisplayMetrics());
                layoutParams.topMargin = actionBarHeight;
            }
        }
        else
        {
            RelativeLayout relativeLayout = holder.wrapper;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams();

            layoutParams.topMargin = 0;
        }
    }

    @Override
    public int getItemCount() {
        if (mFiles != null) {
            return mFiles.size();
        }
        else {
            return 0;
        }
    }

    public void setCallbacks(FileListFragment.FileListCallbacks callbacks) {
        mCallbacks = callbacks;
    }

}
