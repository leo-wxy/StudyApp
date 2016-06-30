package wxy.frame.finalframe;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by xixi on 16/6/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this).
//        ImagePipelineConfig imagePipelineConfig=ImagePipelineConfig.newBuilder(this)
//                .build();

        Fresco.initialize(this);

    }
}
