package wxy.frame.finalframe.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xixi on 16/6/22.
 */

public class ForecastWeatherBean implements Parcelable {

    /**
     * fengxiang : 西南风
     * fengli : 3-4级
     * high : 高温 35℃
     * type : 雷阵雨
     * low : 低温 27℃
     * date : 22日星期三
     */

    private String fengxiang;
    private String fengli;
    private String high;
    private String type;
    private String low;
    private String date;

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fengxiang);
        dest.writeString(this.fengli);
        dest.writeString(this.high);
        dest.writeString(this.type);
        dest.writeString(this.low);
        dest.writeString(this.date);
    }

    public ForecastWeatherBean() {

    }

    protected ForecastWeatherBean(Parcel in) {
        this.fengxiang = in.readString();
        this.fengli = in.readString();
        this.high = in.readString();
        this.type = in.readString();
        this.low = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<ForecastWeatherBean> CREATOR = new Parcelable.Creator<ForecastWeatherBean>() {
        @Override
        public ForecastWeatherBean createFromParcel(Parcel source) {
            return new ForecastWeatherBean(source);
        }

        @Override
        public ForecastWeatherBean[] newArray(int size) {
            return new ForecastWeatherBean[size];
        }
    };
}
