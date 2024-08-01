package com.android.base.image;

import com.android.base.image.glide.GlideImageLoader;

/**
 * @author Ztiany
 */
public class ImageLoaderFactory {

    private static ImageLoader sImageLoader;

    static {
        try {
            Class.forName("com.bumptech.glide.Glide");
            sImageLoader = new GlideImageLoader();
        } catch (ClassNotFoundException ignored) {
        }
    }

    private static void init(ImageLoader imageLoader) {
        sImageLoader = imageLoader;
    }

    public static ImageLoader getImageLoader() {
        return sImageLoader;
    }

}