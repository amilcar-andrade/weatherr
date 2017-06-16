package amilcar.weatherr.data.api;

import amilcar.weatherr.data.model.WeatherEnvelop;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    String END_POINT = "http://twitter-code-challenge.s3-website-us-east-1.amazonaws.com/";

    @GET("/current.json")
    Call<WeatherEnvelop> getWeather();

    @GET("/future_{n}.json")
    Call<WeatherEnvelop> getFutureWeather(@Path("n") int future);


    class Factory {
        public static WeatherApi createWeatherApi() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(WeatherApi.class);
        }
    }
}
