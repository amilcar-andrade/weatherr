package amilcar.weatherr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleWeather implements Parcelable {
    private static final int CLOUDINESS_PERCENTAGE = 50;

    public final double cTemperature;
    public final double windSpeed;
    public final int cloudiness;

    public SimpleWeather(double cTemperature, double windSpeed, int cloudiness) {
        this.cTemperature = cTemperature;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
    }

    public SimpleWeather() {
        this.cTemperature = 0;
        this.windSpeed = 0;
        this.cloudiness = 0;
    }

    public String getCelsiusTemperature() {
        // The API returns celsius so just return that value
        return String.valueOf(cTemperature);
    }

    public String getFahrenheitTemperature() {
        // Fahrenheit = (9/5) * Celsius + 32
        return String.valueOf((9.0/5.0) * cTemperature + 32);
    }

    public String getWindSpeed() {
        return String.valueOf(windSpeed);
    }

    public boolean isClouded() {
        return cloudiness > CLOUDINESS_PERCENTAGE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.cTemperature);
        dest.writeDouble(this.windSpeed);
        dest.writeInt(this.cloudiness);
    }

    protected SimpleWeather(Parcel in) {
        this.cTemperature = in.readDouble();
        this.windSpeed = in.readDouble();
        this.cloudiness = in.readInt();
    }

    public static final Creator<SimpleWeather> CREATOR = new Creator<SimpleWeather>() {
        @Override
        public SimpleWeather createFromParcel(Parcel source) {
            return new SimpleWeather(source);
        }

        @Override
        public SimpleWeather[] newArray(int size) {
            return new SimpleWeather[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleWeather)) return false;

        SimpleWeather that = (SimpleWeather) o;

        if (Double.compare(that.cTemperature, cTemperature) != 0) return false;
        if (Double.compare(that.windSpeed, windSpeed) != 0) return false;
        return cloudiness == that.cloudiness;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cTemperature);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(windSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cloudiness;
        return result;
    }
}
