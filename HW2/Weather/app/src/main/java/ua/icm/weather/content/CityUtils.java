package ua.icm.weather.content;

import java.util.ArrayList;
import java.util.List;

public class CityUtils {

    public static final List<City> CITY_ITEMS = new ArrayList<>();

    public static final String CITY_ID_KEY = "item_id";

    // The number of songs.
    private static final int COUNT = 7;


    public static class City {
        public final String name;
        public final String weather;

        private City(String name, String weather) {
            this.name = name;
            this.weather = weather;
        }
    }

    private static void addItem(City item) {
        CITY_ITEMS.add(item);
    }

  /*  static {
        for (int i = 0; i < COUNT; i++) {
            addItem(createCityAtPosition(i));
        }
    }*/

    private static City createCityAtPosition(int position) {
        String newName;
        String newWeather;
        switch (position) {
            case 0:
                newName = "Aveiro";
                newWeather = "20ºC";
                break;
            case 1:
                newName = "Porto";
                newWeather = "21ºC";
                break;
            case 2:
                newName = "Castelo Branco";
                newWeather = "25ºC";
                break;
            case 3:
                newName = "Braga";
                newWeather = "23ºC";
                break;
            case 4:
                newName = "Albufeira";
                newWeather = "28ºC";
                break;
            case 5:
                newName = "Lagos";
                newWeather = "27ºC";
                break;
            default:
                newName = "Lisboa";
                newWeather = "20ºC";
                break;
        }
        return new City(newName, newWeather);
    }
}
