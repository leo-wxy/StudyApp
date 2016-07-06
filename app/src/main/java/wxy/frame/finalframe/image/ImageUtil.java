package wxy.frame.finalframe.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import wxy.frame.finalframe.BaseActivity;
import wxy.frame.finalframe.finals.AppConfig;
import wxy.frame.finalframe.util.StringUtil;

/**
 * 图片工具类 包括网络以及本地图片加载  图片裁剪  图片保存等图片相关功能
 */
public class ImageUtil {
    /**
     * 拍照
     */
    public static final int DATA_WITH_CAMERA = 0x7;//拍照
    /**
     * 拍照并裁剪
     */
    public static final int DATA_WITH_CAMERA_CROP = 0x10;//拍照并裁剪
    /**
     * 从相册中选择照片不裁剪
     */
    public static final int DATA_WITH_PHOTO_PICKED = 0x8;//从相册中选择
    /**
     * 从相册中选择照片裁剪
     */
    public static final int DATA_WITH_PHOTO_PICKED_CROP = 0x9;//从相册中选择并裁剪

    /**
     * 加载图片不包含监听事件
     *
     * @param context
     * @param imageview
     * @param url
     * @param mDefaultImageId
     * @param mLoadImageRadius
     */
    public static void loadImage(BaseActivity context, ImageView imageview, String url, int mDefaultImageId, int mLoadImageRadius) {
        Glide.with(context)
                .load(url)//设值图片加载链接
                .placeholder(mDefaultImageId)//设置未显示图片时的加载图片
                .error(mDefaultImageId)//设置图片无法显示时的图片
                .crossFade(200)//设置图片显示时的动画效果
                .transform(new GlideRoundTransform(context, mLoadImageRadius))
                .into(imageview);//设置显示imageview
    }

    /**
     * 包含监听事件的加载
     *
     * @param context
     * @param imageview
     * @param url
     * @param mDefaultImageId
     * @param mLoadImageRadius
     * @param imageListener    加载监听事件
     */
    public static void loadImage(BaseActivity context, ImageView imageview, String url, int mDefaultImageId, int mLoadImageRadius, final LoadImageListener
            imageListener) {
        Glide.with(context)
                .load(url)//设值图片加载链接
                .placeholder(mDefaultImageId)//设置未显示图片时的加载图片
                .error(mDefaultImageId)//设置图片无法显示时的图片
                .crossFade(200)//设置图片显示时的动画效果
                .transform(new GlideRoundTransform(context, mLoadImageRadius))
                .into(new GlideDrawableImageViewTarget(imageview) {
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        imageListener.onLoadingFailed(e, errorDrawable);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        imageListener.onLoadingComplete(resource, animation);
                    }

                });//设置显示imageview
    }


    public static void loadImage(BaseActivity context, ImageView imageview, int id, int mDefaultImageId, int mLoadImageRadius, final LoadImageListener
            imageListener) {
        Glide.with(context)
                .load(id)//设值图片加载链接
                .placeholder(mDefaultImageId)//设置未显示图片时的加载图片
                .error(mDefaultImageId)//设置图片无法显示时的图片
                .crossFade(200)//设置图片显示时的动画效果
                .transform(new GlideRoundTransform(context, mLoadImageRadius))
                .into(new GlideDrawableImageViewTarget(imageview) {
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        imageListener.onLoadingFailed(e, errorDrawable);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        imageListener.onLoadingComplete(resource, animation);
                    }

                });//设置显示imageview
    }

    /**
     * 拍照
     *
     * @param context
     * @param filename
     */
    public static void takePhoto(Activity context, String filename) {
        Uri imageuri = Uri.parse(StringUtil.getFileUrlHead(2) + AppConfig.DIR_IMG + File.separator + filename);
        Intent intent = getIntent(DATA_WITH_CAMERA, imageuri);
        if (intent != null) {
            try {
                context.startActivityForResult(intent, DATA_WITH_CAMERA);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "拍照功能不可用", Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(context, "拍照功能不可用", Toast.LENGTH_SHORT);
        }
    }

    /**
     * 从相册中选择，不裁剪
     *
     * @param context
     * @param filename
     */
    public static void selectFromAlbumFull(Activity context, String filename) {
        Uri imageuri = Uri.parse(StringUtil.getFileUrlHead(2) + AppConfig.DIR_IMG + File.separator + filename);
        Intent intent = getIntent(DATA_WITH_PHOTO_PICKED, imageuri);
        if (intent != null) {
            try {
                context.startActivityForResult(intent, DATA_WITH_PHOTO_PICKED);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "无法获取图片", Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(context, "无法获取图片", Toast.LENGTH_SHORT);
        }
    }

    /**
     * 从相册中选择图片，需要裁剪
     *
     * @param context
     */
    public static void selectFromAlbum(Activity context) {
        Intent intent = getIntent(DATA_WITH_PHOTO_PICKED);
        if (intent != null) {
            try {
                context.startActivityForResult(intent, DATA_WITH_PHOTO_PICKED);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "无法获取图片", Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(context, "无法获取图片", Toast.LENGTH_SHORT);
        }
    }


    /**
     * 获得跳转的Intent
     *
     * @param type
     * @param imageuri
     * @return
     */
    private static Intent getIntent(int type, Uri imageuri) {
        if (type == DATA_WITH_CAMERA) {//拍照
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
            return intent;
        }
        return null;
    }


    private static Intent getIntent(int type) {
        if (type == DATA_WITH_PHOTO_PICKED) {//从相册选取
            Intent intent = null;
            if (Build.VERSION.SDK_INT < 19) {
                intent = new Intent(Intent.ACTION_GET_CONTENT, null);
            } else {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            }
            intent.setType("image/*");
            return intent;
        }
        return null;
    }


    /**
     * @param imageuri
     * @param xWeight
     * @param yWeight
     * @param width
     * @param height
     * @return
     */
    private static Intent getIntent(Uri imageuri, int xWeight, int yWeight, int width, int height) {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        if (xWeight > 0 && xWeight > 0) {
            intent.putExtra("aspectX", xWeight);
            intent.putExtra("aspectY", xWeight);
        }
        if (width > 0 && height > 0) {
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", height);
        }
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        return intent;
    }

    /**
     * 裁剪图片
     *
     * @param context
     * @return
     * @Title cropImage
     * @Description TODO
     */

    public static Intent cropImage(Context context, Uri uri) {
        try {
            // 裁剪图片意图
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/png");
            intent.putExtra("crop", "true");
            // 裁剪框的比例，1：1
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // 裁剪后输出图片的尺寸大小
            intent.putExtra("outputX", 250);
            intent.putExtra("outputY", 250);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("outputFormat", "JPEG");// 图片格式
            intent.putExtra("noFaceDetection", true);// 取消人脸识别
            intent.putExtra("return-data", true);
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
            return intent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean isPhotoCallBack(int requestCode) {
        if (requestCode == DATA_WITH_CAMERA || requestCode == DATA_WITH_PHOTO_PICKED ||
                requestCode == DATA_WITH_PHOTO_PICKED_CROP) {
            return true;
        }
        return false;
    }

    public static String save(String filename, Bitmap bitmap) {
        File file = null;
        try {
            File filexists = new File(filename);
            filexists.createNewFile();
            FileOutputStream fos = new FileOutputStream(filexists);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String filename = "temp.jpg";
    private static Uri imageUri = Uri.parse("file://" + AppConfig.DIR_IMG
            + File.separator + filename);

    /**
     * 获取文件名称
     *
     * @return
     */
    public static String getFileName() {
        return filename;
    }

    /**
     * 因为只能在Activity中使用，故开启新方法替代
     *
     * @param context
     * @param requestCode
     * @param resultCode
     * @param data
     * @param listener
     */
    public static void onActivityResult(Context context, int requestCode, int resultCode,
                                        Intent data, SelectImageListener listener) {
        if (requestCode == DATA_WITH_CAMERA) {
            filename = System.currentTimeMillis() + ".jpg";
            Uri imageUri1 = Uri.parse("file://" + AppConfig.DIR_IMG
                    + File.separator + filename);
            ((BaseActivity) context).startActivityForResult(cropImage(context, imageUri),
                    DATA_WITH_CAMERA_CROP);
            imageUri = imageUri1;
            Bitmap photo = data.getParcelableExtra("data");
            filename = System.currentTimeMillis() + ".jpg";
            filename = AppConfig.DIR_IMG + File.separator + filename;
            save(filename, photo);
            listener.selectPic();
        } else if (requestCode == DATA_WITH_CAMERA_CROP) {
            // 将拍照获取的原图删除
            File file = new File(AppConfig.DIR_IMG + File.separator
                    + "temp.jpg");
            filename = AppConfig.DIR_IMG + File.separator + filename;
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        } else if (requestCode == DATA_WITH_PHOTO_PICKED) {
            Uri imageUri1 = data.getData();
            ((BaseActivity) context).startActivityForResult(cropImage(context, imageUri1),
                    DATA_WITH_PHOTO_PICKED_CROP);
        } else if (requestCode == DATA_WITH_PHOTO_PICKED_CROP) {
            Bitmap photo = data.getParcelableExtra("data");
            filename = System.currentTimeMillis() + ".jpg";
            filename = AppConfig.DIR_IMG + File.separator + filename;
            save(filename, photo);
            listener.selectPic();
        }


    }
}
