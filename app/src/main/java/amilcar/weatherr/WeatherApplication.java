package amilcar.weatherr;

import android.app.Application;
import android.content.Context;

import amilcar.weatherr.injection.component.ApplicationComponent;
import amilcar.weatherr.injection.component.DaggerApplicationComponent;
import amilcar.weatherr.injection.module.ApplicationModule;

public class WeatherApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public static WeatherApplication get(Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
