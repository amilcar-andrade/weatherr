package amilcar.weatherr.data.background;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import java.io.IOException;

import javax.inject.Inject;

import amilcar.weatherr.WeatherApplication;
import amilcar.weatherr.data.WeatherService;
import amilcar.weatherr.data.model.SimpleWeather;
import amilcar.weatherr.data.model.WeatherEnvelop;
import retrofit2.Call;

public class WeatherLoader extends AbstractAsyncTaskLoader<SimpleWeather> {
    public static final int ID = 1251;
    @Inject
    WeatherService service;

    private final Call<WeatherEnvelop> call;

    public WeatherLoader(Context context) {
        super(context);
        WeatherApplication.get(context).getComponent().inject(this);
        call = service.getWeather();
    }

    @Override
    SimpleWeather loadInBackground0() {
        WeatherEnvelop body = null;
        try {
            body = call.execute().body();
            return buildSimpleWeather(body);
        } catch (IOException e) {
            return buildSimpleWeather(body);
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        call.cancel();
    }

    @VisibleForTesting
    public SimpleWeather buildSimpleWeather(WeatherEnvelop body) {
        if (body == null) {
            return new SimpleWeather();
        }

        return new SimpleWeather(body.weather.temp, body.wind.speed, body.clouds.cloudiness);
    }
}
