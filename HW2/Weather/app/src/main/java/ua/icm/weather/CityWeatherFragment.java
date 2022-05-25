package ua.icm.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.icm.weather.content.CityUtils;
import ua.icm.weather.datamodel.CityGroup;
import ua.icm.weather.datamodel.Weather;
import ua.icm.weather.datamodel.WeatherGroup;
import ua.icm.weather.network.IpmaApiEndpoints;
import ua.icm.weather.network.RetrofitInstance;

public class CityWeatherFragment extends Fragment {

    int globalIdLocal;
    IpmaApiEndpoints service = RetrofitInstance.getRetrofitInstance().create(IpmaApiEndpoints.class);

    public CityWeatherFragment() {}

    public static CityWeatherFragment newInstance(int selectedCity) {
        CityWeatherFragment fragment = new CityWeatherFragment();
        Bundle args = new Bundle();
        args.putInt("cityGlobalLocal", selectedCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey("cityGlobalLocal")) {
            globalIdLocal = getArguments().getInt("cityGlobalLocal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_city_weather, container, false);
       /* if (mCity != null) {
            ((TextView) rootView.findViewById(R.id.city_weather)).setText(mCity.weather);
        }*/
        Call<WeatherGroup> call = service.getWeatherParent(globalIdLocal);

        call.enqueue(new Callback<WeatherGroup>() {

            @Override
            public void onResponse(Call<WeatherGroup> call, Response<WeatherGroup> response) {
                generateWeatherInfo(rootView, response.body());
            }

            @Override
            public void onFailure(Call<WeatherGroup> call, Throwable t) {
                //Toast.makeText(CityWeatherFragment.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
       return rootView;
    }


    private void generateWeatherInfo(View rootView, WeatherGroup weatherGroup) {

        // Case 1
        Weather weather1 = weatherGroup.getForecasts().get(0);
        TextView date1 = rootView.findViewById(R.id.date1);
        date1.setText(weather1.getForecastDate());

        TextView precProbVal1 = rootView.findViewById(R.id.precProbVal1);
        precProbVal1.setText(String.valueOf(weather1.getPrecipitaProb() + "%"));

        TextView minTempVal1 = rootView.findViewById(R.id.minTempVal1);
        minTempVal1.setText(String.valueOf(weather1.getTMin() + "ºC"));

        TextView maxTempVal1 = rootView.findViewById(R.id.maxTempVal1);
        maxTempVal1.setText(String.valueOf(weather1.getTMax() + "ºC"));

        TextView predWindDirVal1 = rootView.findViewById(R.id.predWindDirVal1);
        predWindDirVal1.setText(weather1.getPredWindDir());

        // Case 2
        Weather weather2 = weatherGroup.getForecasts().get(1);
        TextView date2 = rootView.findViewById(R.id.date2);
        date2.setText(weather2.getForecastDate());

        TextView precProbVal2 = rootView.findViewById(R.id.precProbVal2);
        precProbVal2.setText(String.valueOf(weather2.getPrecipitaProb() + "%"));

        TextView minTempVal2 = rootView.findViewById(R.id.minTempVal2);
        minTempVal2.setText(String.valueOf(weather2.getTMin() + "ºC"));

        TextView maxTempVal2 = rootView.findViewById(R.id.maxTempVal2);
        maxTempVal2.setText(String.valueOf(weather2.getTMax() + "ºC"));

        TextView predWindDirVal2 = rootView.findViewById(R.id.predWindDirVal2);
        predWindDirVal2.setText(weather2.getPredWindDir());

        // Case 3
        Weather weather3 = weatherGroup.getForecasts().get(2);
        TextView date3 = rootView.findViewById(R.id.date3);
        date3.setText(weather3.getForecastDate());

        TextView precProbVal3 = rootView.findViewById(R.id.precProbVal3);
        precProbVal3.setText(String.valueOf(weather3.getPrecipitaProb() + "%"));

        TextView minTempVal3 = rootView.findViewById(R.id.minTempVal3);
        minTempVal3.setText(String.valueOf(weather3.getTMin() + "ºC"));

        TextView maxTempVal3 = rootView.findViewById(R.id.maxTempVal3);
        maxTempVal3.setText(String.valueOf(weather3.getTMax() + "ºC"));

        TextView predWindDirVal3 = rootView.findViewById(R.id.predWindDirVal3);
        predWindDirVal3.setText(weather3.getPredWindDir());

        // Case 4
        Weather weather4 = weatherGroup.getForecasts().get(3);
        TextView date4 = rootView.findViewById(R.id.date4);
        date4.setText(weather4.getForecastDate());

        TextView precProbVal4 = rootView.findViewById(R.id.precProbVal4);
        precProbVal4.setText(String.valueOf(weather4.getPrecipitaProb() + "%"));

        TextView minTempVal4 = rootView.findViewById(R.id.minTempVal4);
        minTempVal4.setText(String.valueOf(weather4.getTMin() + "ºC"));

        TextView maxTempVal4 = rootView.findViewById(R.id.maxTempVal4);
        maxTempVal4.setText(String.valueOf(weather4.getTMax() + "ºC"));

        TextView predWindDirVal4 = rootView.findViewById(R.id.predWindDirVal4);
        predWindDirVal4.setText(weather4.getPredWindDir());

        // Case 5
        Weather weather5 = weatherGroup.getForecasts().get(4);
        TextView date5 = rootView.findViewById(R.id.date5);
        date5.setText(weather5.getForecastDate());

        TextView precProbVal5 = rootView.findViewById(R.id.precProbVal5);
        precProbVal5.setText(String.valueOf(weather5.getPrecipitaProb() + "%"));

        TextView minTempVal5 = rootView.findViewById(R.id.minTempVal5);
        minTempVal5.setText(String.valueOf(weather5.getTMin() + "ºC"));

        TextView maxTempVal5 = rootView.findViewById(R.id.maxTempVal5);
        maxTempVal5.setText(String.valueOf(weather5.getTMax() + "ºC"));

        TextView predWindDirVal5 = rootView.findViewById(R.id.predWindDirVal5);
        predWindDirVal5.setText(weather5.getPredWindDir());
    }

}