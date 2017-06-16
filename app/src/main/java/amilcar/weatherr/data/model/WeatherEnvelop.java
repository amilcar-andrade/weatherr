package amilcar.weatherr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherEnvelop implements Parcelable {
    public final Coord coord;
    public final Weather weather;
    public final Wind wind;
    public final Clouds clouds;

    public WeatherEnvelop(Coord coord, Weather weather, Wind wind, Clouds clouds) {
        this.coord = coord;
        this.weather = weather;
        this.wind = wind;
        this.clouds = clouds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.coord, flags);
        dest.writeParcelable(this.weather, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeParcelable(this.clouds, flags);
    }

    protected WeatherEnvelop(Parcel in) {
        this.coord = in.readParcelable(Coord.class.getClassLoader());
        this.weather = in.readParcelable(Weather.class.getClassLoader());
        this.wind = in.readParcelable(Wind.class.getClassLoader());
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
    }

    public static final Creator<WeatherEnvelop> CREATOR = new Creator<WeatherEnvelop>() {
        @Override
        public WeatherEnvelop createFromParcel(Parcel source) {
            return new WeatherEnvelop(source);
        }

        @Override
        public WeatherEnvelop[] newArray(int size) {
            return new WeatherEnvelop[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeatherEnvelop)) return false;

        WeatherEnvelop that = (WeatherEnvelop) o;

        if (coord != null ? !coord.equals(that.coord) : that.coord != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;
        if (wind != null ? !wind.equals(that.wind) : that.wind != null) return false;
        return clouds != null ? clouds.equals(that.clouds) : that.clouds == null;

    }

    @Override
    public int hashCode() {
        int result = coord != null ? coord.hashCode() : 0;
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        return result;
    }
}
