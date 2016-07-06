package wxy.frame.finalframe.image;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

import wxy.frame.finalframe.finals.AppConfig;

/**
 * 动态设置glide缓存路径
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return DiskLruCacheWrapper.get(new File(AppConfig.DIR_CACHE), 250 * 1024 * 1024);
            }
        });
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
