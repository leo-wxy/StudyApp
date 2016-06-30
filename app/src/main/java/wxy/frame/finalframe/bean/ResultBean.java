package wxy.frame.finalframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xixi on 16/6/22.
 */

public class ResultBean<T extends Parcelable> implements Parcelable {
    private String error;
    private String desc;
    private int status;
    private T data;
    private T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.error);
        dest.writeString(this.desc);
        dest.writeInt(this.status);
        dest.writeString(data.getClass().getName());
        dest.writeParcelable(this.data, flags);
        dest.writeString(results.getClass().getName());
        dest.writeParcelable(this.results, flags);
    }

    public ResultBean() {
    }

    protected ResultBean(Parcel in) {
        this.error = in.readString();
        this.desc = in.readString();
        this.status = in.readInt();
        String dataName = in.readString();
        String resultName = in.readString();
        try {
            this.data = in.readParcelable(Class.forName(dataName).getClassLoader());
            this.results = in.readParcelable(Class.forName(resultName).getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static final Parcelable.Creator<ResultBean> CREATOR = new Parcelable.Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel source) {
            return new ResultBean(source);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };
}
