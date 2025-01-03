package me.ztiany.imageloader;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.base.image.glide.ProgressGlideModule;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

@GlideModule
public class ConfigGlideModule extends ProgressGlideModule {

    @Override
    public void applyOptions(@NonNull final Context context, GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20;
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        int maxCacheSize = 100 * 1024 * 1024;
        String cacheFileName = "imgCache";
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheFileName, maxCacheSize));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

}