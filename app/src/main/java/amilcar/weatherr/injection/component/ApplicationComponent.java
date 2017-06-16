package amilcar.weatherr.injection.component;

import javax.inject.Singleton;

import amilcar.weatherr.WeatherApplication;
import amilcar.weatherr.data.WeatherService;
import amilcar.weatherr.data.background.StdDevWeatherLoader;
import amilcar.weatherr.data.background.WeatherLoader;
import amilcar.weatherr.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(WeatherApplication application);
    void inject(WeatherLoader loader);
    void inject(StdDevWeatherLoader loader);

    WeatherService weatherService();
}
