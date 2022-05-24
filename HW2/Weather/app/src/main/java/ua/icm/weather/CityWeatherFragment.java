package ua.icm.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.icm.weather.content.CityUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CityWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CityWeatherFragment extends Fragment {

    public CityUtils.City mCity;

    public CityWeatherFragment() {
    }

    public static CityWeatherFragment newInstance(int selectedCity) {
        CityWeatherFragment fragment = new CityWeatherFragment();
        Bundle args = new Bundle();
        args.putInt(CityUtils.CITY_ID_KEY, selectedCity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(CityUtils.CITY_ID_KEY)) {
            mCity = CityUtils.CITY_ITEMS.get(getArguments().getInt(CityUtils.CITY_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.city_weather, container, false);
        if (mCity != null) {
            ((TextView) rootView.findViewById(R.id.city_weather)).setText(mCity.weather);
        }
       return rootView;
    }
}