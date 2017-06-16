package amilcar.weatherr.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Clouds implements Parcelable {
    public final int cloudiness;

    public Clouds(int cloudiness) {
        this.cloudiness = cloudiness;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cloudiness);
    }

    protected Clouds(Parcel in) {
        this.cloudiness = in.readInt();
    }

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel source) {
            return new Clouds(source);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clouds)) return false;

        Clouds clouds = (Clouds) o;

        return cloudiness == clouds.cloudiness;

    }

    @Override
    public int hashCode() {
        return cloudiness;
    }
}
