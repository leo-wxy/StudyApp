package wxy.frame.finalframe.finals;

import android.os.Environment;

import java.io.File;

/**
 * Created by xixi on 16/7/6.
 */

public interface AppConfig {

    // --------------应用缓存文件基本信息-----------------------
    /**
     * 程序在手机SDK中的主缓存目录.
     */
    public static final String APP_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator
            + "ThreeTi";
    /**
     * 程序在手机SDK中的缓存目录.
     */
    public static final String DIR_CACHE = APP_PATH + File.separator + "cache";
    /**
     * 程序在手机SDK中的更新缓存目录.
     */
    public static final String DIR_UPDATE = APP_PATH + File.separator + "update";
    /**
     * 程序在手机SDK中的图片缓存目录.
     */
    public static final String DIR_IMG = APP_PATH + File.separator + "image";
    /**
     * 程序在手机SDK中的视频缓存目录.
     */
    public static final String DIR_VIDEO = APP_PATH + File.separator + "video";
    /**
     * 程序在手机SDK中的音频缓存目录.
     */
    public static final String DIR_AUDIO = APP_PATH + File.separator + "audio";
    /**
     * 程序在手机SDK中的日志缓存目录.
     */
    public static final String LOGCAT_DIR = APP_PATH + File.separator + "Log";

}
