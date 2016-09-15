package wxy.frame.finalframe.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by xixi on 16/6/22.
 */

public class WeatherBean implements Parcelable {

    /**
     * wendu : 30
     * ganmao : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
     * forecast :预报
     * aqi : 68 空气质量指数
     * city : 杭州
     */

    private String wendu;
    private String ganmao;
    private List<ForecastWeatherBean> forecast;
    private String aqi;
    private String aqiType;//空气质量指数 0-50 优 51-100 良 101-150 轻度污染 151-200 中度污染 201-300 重度污染
    private String city;

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public List<ForecastWeatherBean> getForecast() {
        return forecast;
    }

    public void setForecast(List<ForecastWeatherBean> forecast) {
        this.forecast = forecast;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqiType() {
        return getType(aqi);
    }

    public void setAqiType(String aqiType) {
        this.aqiType = aqiType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wendu);
        dest.writeString(this.ganmao);
        dest.writeTypedList(this.forecast);
        dest.writeString(this.aqi);
        dest.writeString(this.aqiType);
        dest.writeString(this.city);
    }

    public WeatherBean() {
    }

    protected WeatherBean(Parcel in) {
        this.wendu = in.readString();
        this.ganmao = in.readString();
        this.forecast = in.createTypedArrayList(ForecastWeatherBean.CREATOR);
        this.aqi = in.readString();
        this.aqiType = in.readString();
        this.city = in.readString();
    }

    public static final Parcelable.Creator<WeatherBean> CREATOR = new Parcelable.Creator<WeatherBean>() {
        @Override
        public WeatherBean createFromParcel(Parcel source) {
            return new WeatherBean(source);
        }

        @Override
        public WeatherBean[] newArray(int size) {
            return new WeatherBean[size];
        }
    };

    private String getType(String aqi) {
        if (!TextUtils.isEmpty(aqi)) {
            int aqiNum = Integer.parseInt(aqi);
            if (aqiNum > 0 && aqiNum < 51) {
                return "优";
            } else if (aqiNum > 50 && aqiNum < 101) {
                return "良";
            } else if (aqiNum > 100 && aqiNum < 151) {
                return "轻度污染";
            } else if (aqiNum > 150 && aqiNum < 201) {
                return "中度污染";
            } else {
                return "重度污染";
            }
        }
        return "";
    }
}
