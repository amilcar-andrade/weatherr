package amilcar.weatherr;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import amilcar.weatherr.data.model.Clouds;
import amilcar.weatherr.data.model.Coord;
import amilcar.weatherr.data.model.SimpleWeather;
import amilcar.weatherr.data.model.Weather;
import amilcar.weatherr.data.model.WeatherEnvelop;
import amilcar.weatherr.data.model.Wind;

@RunWith(AndroidJUnit4.class)
public class ParcelableTests {

    @Test
    public void envelop() {
        WeatherEnvelop e = TestUtils.createWeatherEnvelop();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        WeatherEnvelop eFromParcel = WeatherEnvelop.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void weather() {
        Weather e = TestUtils.createWeather();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Weather eFromParcel = Weather.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void coord() {
        Coord e = TestUtils.createCoord();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Coord eFromParcel = Coord.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void clouds() {
        Clouds e = TestUtils.createClouds();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Clouds eFromParcel = Clouds.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void wind() {
        Wind e = TestUtils.createWind();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        Wind eFromParcel = Wind.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }

    @Test
    public void simpleWeather() {
        SimpleWeather e = TestUtils.createSimpleWeather();
        Parcel parcel = Parcel.obtain();
        e.writeToParcel(parcel, 0);

        parcel.setDataPosition(0);
        SimpleWeather eFromParcel = SimpleWeather.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(e, eFromParcel);
    }
}
