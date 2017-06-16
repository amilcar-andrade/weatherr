package amilcar.weatherr.data.background;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import amilcar.weatherr.WeatherApplication;
import amilcar.weatherr.data.WeatherService;
import amilcar.weatherr.data.model.Weather;
import amilcar.weatherr.data.model.WeatherEnvelop;
import retrofit2.Call;

public class StdDevWeatherLoader extends AbstractAsyncTaskLoader<Double> {
    public static final int ID = 1252;
    private static final int START_TIME = 1;
    private static final int END_TIME = 5;

    @Inject
    WeatherService service;

    private Call<WeatherEnvelop> call;

    public StdDevWeatherLoader(Context context) {
        super(context);
        WeatherApplication.get(context).getComponent().inject(this);
    }

    @Override
    Double loadInBackground0() {
        double sumOfTemps = 0;
        List<Weather> weathers = new ArrayList<>(END_TIME);
        for (int i = START_TIME; i <= END_TIME; i++) {
            try {
                call = service.getFutureWeather(i);
                final Weather weather = call.execute().body().weather;
                weathers.add(weather);
                sumOfTemps = sumOfTemps + weather.temp;
            } catch (IOException e) {
                // Try to calculate the std-dev with the available items
                return calculateStdDev(weathers, sumOfTemps);
            }
        }
        return calculateStdDev(weathers, sumOfTemps);
    }

    @Override
    protected void onReset() {
        super.onReset();
        call.cancel();
    }

    @VisibleForTesting
    public double calculateStdDev(List<Weather> weathers, double sumOfTemps) {
        int standardDeviation = 0;
        if (weathers == null || weathers.isEmpty()) {
            return standardDeviation;
        }

        int dataPoints = weathers.size();
        double average = sumOfTemps / dataPoints;
        double sumE = 0;

        for (int i = 0; i < weathers.size(); i++) {
            double currentTemp = weathers.get(i).temp;
            double diff = currentTemp - average;
            double power2 = Math.pow(diff, 2);
            sumE = power2 + sumE;
        }
        double divisionResult = sumE / (dataPoints - 1);
        return Math.sqrt(divisionResult);
    }
}
