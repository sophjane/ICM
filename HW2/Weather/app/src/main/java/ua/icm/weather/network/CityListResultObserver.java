package ua.icm.weather.network;

import java.util.HashMap;

import ua.icm.weather.datamodel.City;

public interface CityListResultObserver {
    public void receiveCitiesList(HashMap<String, City> citiesMap);
    public void onFailure(Throwable cause);

}
