package ua.icm.weather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ua.icm.weather.datamodel.CityGroup;
import ua.icm.weather.datamodel.WeatherGroup;

public interface IpmaApiEndpoints {

    @GET("open-data/distrits-islands.json")
    Call<CityGroup> getCityParent();

    @GET("open-data/forecast/meteorology/cities/daily/{localId}.json")
    Call<WeatherGroup> getWeatherParent(@Path("localId") int localId);

}
