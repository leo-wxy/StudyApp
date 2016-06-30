package wxy.frame.finalframe.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by xixi on 16/6/29.
 */

public class SnachBarUtils {
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


}
