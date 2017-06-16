package amilcar.weatherr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable{
    public final double temp;
    final int pressure;
    final int humidity;

    public Weather(double temp, int pressure, int humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.temp);
        dest.writeInt(this.pressure);
        dest.writeInt(this.humidity);
    }

    protected Weather(Parcel in) {
        this.temp = in.readDouble();
        this.pressure = in.readInt();
        this.humidity = in.readInt();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather)) return false;

        Weather weather = (Weather) o;

        if (Double.compare(weather.temp, temp) != 0) return false;
        if (pressure != weather.pressure) return false;
        return humidity == weather.humidity;

    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        temp1 = Double.doubleToLongBits(temp);
        result = (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + pressure;
        result = 31 * result + humidity;
        return result;
    }
}
