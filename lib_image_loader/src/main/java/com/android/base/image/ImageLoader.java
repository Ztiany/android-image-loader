package com.android.base.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;

// @formatter:off
public interface ImageLoader {

    void display(ImageView imageView, String url);
    void display(ImageView imageView, Source source);
    void display(ImageView imageView, String url, DisplayConfig config);
    void display(ImageView imageView, Source source, DisplayConfig config);
    void display(ImageView imageView, String url, LoadListener<Drawable> loadListener);
    void display(ImageView imageView, String url, DisplayConfig config, LoadListener<Drawable> loadListener);

    void display(Fragment fragment, ImageView imageView, String url);
    void display(Fragment fragment, ImageView imageView, Source source);
    void display(Fragment fragment, ImageView imageView, String url, DisplayConfig displayConfig);
    void display(Fragment fragment, ImageView imageView, Source source, DisplayConfig displayConfig);
    void display(Fragment fragment, ImageView imageView, String url, LoadListener<Drawable> loadListener);
    void display(Fragment fragment, ImageView imageView, String url, DisplayConfig displayConfig, LoadListener<Drawable> loadListener);

    ///////////////////////////////////////////////////////////////////////////
    // pause and resume
    ///////////////////////////////////////////////////////////////////////////

    void pause(Fragment fragment);
    void resume(Fragment fragment);
    void pause(Context context);
    void resume(Context context);

    ///////////////////////////////////////////////////////////////////////////
    // preload
    ///////////////////////////////////////////////////////////////////////////

    void preload(Context context, Source source);
    void preload(Context context, Source source, int width, int height);

    ///////////////////////////////////////////////////////////////////////////
    // LoadBitmap
    ///////////////////////////////////////////////////////////////////////////

    void loadBitmap(Context context, Source source, boolean cache, LoadListener<Bitmap> bitmapLoadListener);
    void loadBitmap(Fragment fragment, Source source, boolean cache, LoadListener<Bitmap> bitmapLoadListener);
    void loadBitmap(Context context, Source source, boolean cache, int width, int height, LoadListener<Bitmap> bitmapLoadListener);
    void loadBitmap(Fragment fragment, Source source, boolean cache, int width, int height, LoadListener<Bitmap> bitmapLoadListener);
    @WorkerThread Bitmap loadBitmap(Context context, Source source, boolean cache, int width, int height);

    ///////////////////////////////////////////////////////////////////////////
    // clear
    ///////////////////////////////////////////////////////////////////////////

    @WorkerThread void clearDisCache(Context context);
    void clear(View view);
    void clear(Fragment fragment, View view);

    ///////////////////////////////////////////////////////////////////////////
    // progress
    ///////////////////////////////////////////////////////////////////////////

    @UiThread
    void removeAllListener(String url);

    /**
     * Add a listener for the corresponding URL, multiple ProgressListeners can be added for the same URL,
     * but the same instance of ProgressListener will not be added repeatedly.
     */
    @UiThread
    void addListener(@NonNull String url, @NonNull ProgressListener progressListener);

    /**
     * Add a listener for the corresponding URL, there will be only one ProgressListener for the same URL,
     * the added ProgressListener will be replaced by the new one, which is isolated with {@link #addListener(String, ProgressListener)}.
     */
    void setListener(String url, @Nullable ProgressListener progressListener);

}