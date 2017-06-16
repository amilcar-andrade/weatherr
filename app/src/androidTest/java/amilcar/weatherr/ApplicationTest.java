package amilcar.weatherr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import amilcar.weatherr.data.background.StdDevWeatherLoader;
import amilcar.weatherr.data.background.WeatherLoader;
import amilcar.weatherr.data.model.SimpleWeather;
import amilcar.weatherr.data.model.Weather;
import amilcar.weatherr.data.model.WeatherEnvelop;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("amilcar.weatherr", appContext.getPackageName());
    }

    @Test
    public void buildSimpleWeather() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        WeatherLoader loader = new WeatherLoader(appContext);
        final WeatherEnvelop weatherEnvelop = TestUtils.createWeatherEnvelop();
        final SimpleWeather simpleWeather = loader.buildSimpleWeather(weatherEnvelop);
        assertEquals(simpleWeather.cloudiness, weatherEnvelop.clouds.cloudiness);
        assertEquals(simpleWeather.windSpeed, weatherEnvelop.wind.speed, 0);
        assertEquals(simpleWeather.cTemperature, weatherEnvelop.weather.temp, 0);
    }

    @Test
    public void buildSimpleWeatherNull() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        WeatherLoader loader = new WeatherLoader(appContext);
        final SimpleWeather emptySimpleWeather = loader.buildSimpleWeather(null);
        assertEquals(emptySimpleWeather.cloudiness, 0);
        assertEquals(emptySimpleWeather.windSpeed, 0, 0);
        assertEquals(emptySimpleWeather.cTemperature, 0, 0);
    }

    @Test
    public void calculateStdDevNullOrEmpty() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        StdDevWeatherLoader loader = new StdDevWeatherLoader(appContext);
        assertTrue(loader.calculateStdDev(null, 0) == 0.0);
        assertTrue(loader.calculateStdDev(new ArrayList<Weather>(), 0) == 0.0);
    }

    @Test
    public void calculateStdDevSameResults() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        StdDevWeatherLoader loader = new StdDevWeatherLoader(appContext);
        Weather weatherEnvelop = TestUtils.createWeather();
        List<Weather> weathers = Arrays.asList(weatherEnvelop, weatherEnvelop);
        double stdDev = loader.calculateStdDev(weathers, 88);
        assertTrue(stdDev == 0);
    }

    @Test
    public void calculateStdDevDifferentResults() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        StdDevWeatherLoader loader = new StdDevWeatherLoader(appContext);
        Weather weatherEnvelop10 = TestUtils.createWeather(10);
        Weather weatherEnvelop20 = TestUtils.createWeather(20);
        List<Weather> weathers = Arrays.asList(weatherEnvelop10, weatherEnvelop20);
        double stdDev = loader.calculateStdDev(weathers, 30);
        assertEquals(7.07, stdDev, .01);
    }
}
