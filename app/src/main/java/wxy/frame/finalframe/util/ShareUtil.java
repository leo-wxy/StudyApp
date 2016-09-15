package wxy.frame.finalframe.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by wxy on 16/9/9.
 * 系统自带的分享工具
 */
public class ShareUtil {
    /**
     * @param context
     * @param text
     * @param title
     */
    public static void shareText(Context context, String text, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_TITLE, title);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     * @param context
     * @param uri
     * @param title
     */
    public static void shareImage(Context context, Uri uri, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    }

}
