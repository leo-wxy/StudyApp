package wxy.frame.finalframe.image;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * Created by WangXY on 2015/11/17.16:35.
 */
public interface LoadImageListener {
    /**
     * 加载失败监听
     *
     * @param e
     * @param errorDrawable
     */
    public void onLoadingFailed(Exception e, Drawable errorDrawable);

    /**
     * 加载成功监听
     *
     * @param resource
     * @param animation
     */
    public void onLoadingComplete(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation);
}
