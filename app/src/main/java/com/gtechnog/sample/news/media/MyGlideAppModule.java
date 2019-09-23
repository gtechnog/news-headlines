package com.gtechnog.sample.news.helper;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class MyGlideAppModule extends AppGlideModule {

    private static final long GLIDE_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);

        // Configure Memory Cache
        MemorySizeCalculator calc1 = new MemorySizeCalculator.Builder(context)
                .build();
        builder.setMemoryCache(new LruResourceCache(calc1.getMemoryCacheSize()));

        // Configure Bitmap Pool
        MemorySizeCalculator calc2 = new MemorySizeCalculator.Builder(context)
                .build();
        builder.setBitmapPool(new LruBitmapPool(calc2.getBitmapPoolSize()));

        // Configure Disk Cache
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, GLIDE_DISK_CACHE_SIZE));

        // Default Request Options
        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565)
                        .downsample(DownsampleStrategy.AT_MOST)
                        .skipMemoryCache(true));
    }
}
