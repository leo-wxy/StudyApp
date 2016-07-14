package wxy.frame.finalframe.util;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import wxy.frame.finalframe.R;

/**
 * Created by xixi on 16/6/29.
 */

public class SnackBarUtils {
    public static void showSnack(View view, String tip) {
        Snackbar.make(view, tip, Snackbar.LENGTH_SHORT).show();
    }

    public static void showSnack(View view, String tip, String action, View.OnClickListener onClickListener) {
        Snackbar.make(view, tip, Snackbar.LENGTH_SHORT).setAction(action, onClickListener).show();
    }

    public static void showSnackLong(View view, String tip) {
        Snackbar.make(view, tip, Snackbar.LENGTH_LONG).show();
    }

    public static void showSnackLong(View view, String tip, String action, View.OnClickListener onClickListener) {
        Snackbar.make(view, tip, Snackbar.LENGTH_LONG).setAction(action, onClickListener).show();
    }

    /**
     * 设置Snackbar背景颜色
     * @param snackbar
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int backgroundColor) {
        View view = snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置Snackbar文字和背景颜色
     * @param snackbar
     * @param messageColor
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if(view!=null){
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

}
