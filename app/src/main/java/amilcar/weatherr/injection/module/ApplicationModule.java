package amilcar.weatherr.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import amilcar.weatherr.data.WeatherService;
import amilcar.weatherr.data.api.WeatherApi;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    WeatherApi providesWeatherApi() {
        return WeatherApi.Factory.createWeatherApi();
    }

    @Provides
    @Singleton
    WeatherService providesWeatherService(WeatherApi api) {
        return new WeatherService(api);
    }
}
