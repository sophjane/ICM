package ua.icm.weather.network;

import java.util.List;

import ua.icm.weather.datamodel.Weather;

public interface ForecastForACityResultsObserver {
    public void receiveForecastList(List<Weather> forecast);
    public void onFailure(Throwable cause);
}
