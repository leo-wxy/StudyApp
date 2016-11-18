package wxy.frame.finalframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wxy on 2016/11/18.
 */

public class ArticleBean implements Parcelable, Cloneable {
    private String name;
    private boolean collect = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte(this.collect ? (byte) 1 : (byte) 0);
    }

    public ArticleBean() {
    }

    protected ArticleBean(Parcel in) {
        this.name = in.readString();
        this.collect = in.readByte() != 0;
    }

    public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
        @Override
        public ArticleBean createFromParcel(Parcel source) {
            return new ArticleBean(source);
        }

        @Override
        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };

    public ArticleBean clone() {
        ArticleBean themeBean = null;
        try {
            themeBean = (ArticleBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return themeBean;
    }
}
