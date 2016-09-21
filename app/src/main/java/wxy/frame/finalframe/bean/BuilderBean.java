package wxy.frame.finalframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 测试构造者模式
 * Created by wxy on 2016/9/16.
 */
public class BuilderBean implements Parcelable {
    private String title;
    private String text;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.url);
    }

    public BuilderBean() {

    }

    public BuilderBean(String title, String text, String url) {
        this.title = title;
        this.text = text;
        this.url = url;
    }

    protected BuilderBean(Parcel in) {
        this.title = in.readString();
        this.text = in.readString();
        this.url = in.readString();
    }

    public static final Creator<BuilderBean> CREATOR = new Creator<BuilderBean>() {
        @Override
        public BuilderBean createFromParcel(Parcel source) {
            return new BuilderBean(source);
        }

        @Override
        public BuilderBean[] newArray(int size) {
            return new BuilderBean[size];
        }
    };

    @Override
    public String toString() {
        return this.title + " " + this.text + " " + this.url;
    }

    public static class Builder {
        String title, text, url;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public BuilderBean build() {
            return new BuilderBean(title, text, url);
        }

    }
}
