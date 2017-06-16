package amilcar.weatherr.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import amilcar.weatherr.data.api.WeatherApi;
import amilcar.weatherr.data.model.WeatherEnvelop;
import retrofit2.Call;

@Singleton
public class WeatherService {
    private final WeatherApi api;

    @Inject
    public WeatherService(WeatherApi api) {
        this.api = api;
    }

    public Call<WeatherEnvelop> getWeather() {
        return api.getWeather();
    }

    public Call<WeatherEnvelop> getFutureWeather(int n) {
        return api.getFutureWeather(n);
    }
}
