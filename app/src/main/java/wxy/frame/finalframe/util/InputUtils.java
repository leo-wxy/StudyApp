package wxy.frame.finalframe.util;

/**
 * Created by xixi on 16/7/15.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * 设计软键盘控制的方法
 */
public class InputUtils {
    /**
     * 打开输入法键盘
     *
     * @param editText 输入框控件
     */
    public static void showInputMethodView(Context context, EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            imm.showSoftInput(editText, 0);
        }
    }

    public static void showInputMethodView(Context context,
                                           final EditText editText, long millseconds) {
        editText.requestFocus();
        final InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    imm.showSoftInput(editText, 0);
                }
            }, millseconds);
        }
    }

    /**
     * 关闭输入法键盘
     *
     * @param editText 输入框控件
     */
    public static void hideInputMethdView(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static String getInputString(EditText editText) {
        if (editText == null) {
            return null;
        } else {
            return editText.getText().toString();
        }

    }

    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    /**
     * UI设计时没有搜索按键时调用输入法软键盘的搜索功能
     *
     * @param context
     * @param editText
     * @param tips     //输入为空时的提示语句
     * @param listener //点击监听事件
     */
    public static void searchClick(final Context context, final EditText editText, final String tips,
                                   final SearchActionListener listener) {
        editText.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                            hideInputMethdView(context, editText);
                            String key = editText.getText().toString().trim();
                            if (TextUtils.isEmpty(key)) {
                                editText.setError(tips);
                            } else {
                                listener.searchAction();
                            }
                        }
                        return false;
                    }
                }
        );
    }

    /**
     * 点击空白处收起键盘
     *
     * @param context
     */
    public static void setTouchListener(final Activity context) {
        context.getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) context
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (null != context.getCurrentFocus()) {
                    imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
                } else {
                    imm.hideSoftInputFromWindow((context.findViewById(android.R.id.content)).getWindowToken(), 0);
                }
                return false;
            }
        });
    }


    public interface SearchActionListener {
        void searchAction();
    }

    /**
     * 判断是否存在虚拟键盘
     *
     * @param context
     * @return
     */
    public static boolean isHaveVirtualKey(Activity context) {
        WindowManager manager = context.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int totalHeight = outMetrics.heightPixels;

        int dpi = 0;
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalHeight - dpi > 0;
    }
}
