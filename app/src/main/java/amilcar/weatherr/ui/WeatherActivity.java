package amilcar.weatherr.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amilcar.weatherr.R;
import amilcar.weatherr.data.background.StdDevWeatherLoader;
import amilcar.weatherr.data.background.WeatherLoader;
import amilcar.weatherr.data.model.SimpleWeather;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity {
    private static final String BUNDLE_KEY_SHOW_STD_DEV = "BUNDLE_KEY_SHOW_STD_DEV";

    @BindView(R.id.standard)
    TextView standardDev;
    @BindView(R.id.temperatureC)
    TextView temperatureC;
    @BindView(R.id.temperatureF)
    TextView temperatureF;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.cloud)
    ImageView cloud;
    boolean showStdDev;

    LoaderManager.LoaderCallbacks<SimpleWeather> weatherCallback = new LoaderManager.LoaderCallbacks<SimpleWeather>(){

        @Override
        public Loader<SimpleWeather> onCreateLoader(int id, Bundle args) {
            return new WeatherLoader(WeatherActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<SimpleWeather> loader, SimpleWeather data) {
            populateWeatherViews(data);
        }

        @Override
        public void onLoaderReset(Loader<SimpleWeather> loader) {
            // no-op
        }
    };

    LoaderManager.LoaderCallbacks<Double> stdDevCallback = new LoaderManager.LoaderCallbacks<Double>(){

        @Override
        public Loader<Double> onCreateLoader(int id, Bundle args) {
            return new StdDevWeatherLoader(WeatherActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<Double> loader, Double data) {
            populateStdDevView(data);
        }

        @Override
        public void onLoaderReset(Loader<Double> loader) {
            // no-op
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            showStdDev = savedInstanceState.getBoolean(BUNDLE_KEY_SHOW_STD_DEV);
        }
        displayData();
    }

    private void displayData() {
        getSupportLoaderManager().initLoader(WeatherLoader.ID, null, weatherCallback);
        if (showStdDev) {
            getSupportLoaderManager().initLoader(StdDevWeatherLoader.ID, null, stdDevCallback);
        }
    }

    private void populateWeatherViews(SimpleWeather weather) {
        temperatureC.setText(weather.getCelsiusTemperature());
        temperatureF.setText(weather.getFahrenheitTemperature());
        wind.setText(weather.getWindSpeed());
        cloud.setVisibility(weather.isClouded() ? View.VISIBLE : View.GONE);
    }

    private void populateStdDevView(Double integer) {
        standardDev.setText(String.valueOf(integer));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(BUNDLE_KEY_SHOW_STD_DEV, showStdDev);
    }

    @OnClick(R.id.standard_btn)
    public void onStandardDeviation(View view) {
        showStdDev = true;
        getSupportLoaderManager().initLoader(StdDevWeatherLoader.ID, null, stdDevCallback);
    }
}
