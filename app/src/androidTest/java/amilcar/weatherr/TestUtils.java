package amilcar.weatherr;

import amilcar.weatherr.data.model.Clouds;
import amilcar.weatherr.data.model.Coord;
import amilcar.weatherr.data.model.SimpleWeather;
import amilcar.weatherr.data.model.Weather;
import amilcar.weatherr.data.model.WeatherEnvelop;
import amilcar.weatherr.data.model.Wind;

public final class TestUtils {
    // Cloud constants
    private static final int CLOUD = 10;

    // Coord constants
    private static final double LAT = 1.1;
    private static final double LON = 6.6;

    // Wind constants
    private static final double SPEED = 9.9;
    private static final int DEG = 90;

    // Weather constants
    private static final double TEMP = 44;
    private static final int PRESSURE = 12;
    private static final int HUMIDITY = 0;

    private TestUtils() {
        // utility class
    }

    static Clouds createClouds() {
        return new Clouds(CLOUD);
    }

    static Coord createCoord() {
        return new Coord(LON, LAT);
    }

    static Wind createWind() {
        return new Wind(SPEED, DEG);
    }

    static Weather createWeather() {
        return new Weather(TEMP, PRESSURE, HUMIDITY);
    }

    static Weather createWeather(double temp) {
        return new Weather(temp, PRESSURE, HUMIDITY);
    }

    static WeatherEnvelop createWeatherEnvelop() {
        return new WeatherEnvelop(
          createCoord(), createWeather(), createWind(), createClouds()
        );
    }

    static SimpleWeather createSimpleWeather() {
        return new SimpleWeather(TEMP, SPEED, CLOUD);
    }

}
