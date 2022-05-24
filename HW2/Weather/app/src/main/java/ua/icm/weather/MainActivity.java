package ua.icm.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.icm.weather.content.CityUtils;
import ua.icm.weather.datamodel.City;
import ua.icm.weather.datamodel.CityGroup;
import ua.icm.weather.network.IpmaApiEndpoints;
import ua.icm.weather.network.RetrofitInstance;

public class MainActivity extends AppCompatActivity {

    private boolean mTwoPane = false;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //Create handle for the RetrofitInstance interface
        IpmaApiEndpoints service = RetrofitInstance.getRetrofitInstance().create(IpmaApiEndpoints.class);

        Call<CityGroup> call = service.getCityParent();

        call.enqueue(new Callback<CityGroup>() {

            @Override
            public void onResponse(Call<CityGroup> call, Response<CityGroup> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<CityGroup> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


      /*  RecyclerView recyclerView = (RecyclerView) findViewById(R.id.city_list);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(CityUtils.CITY_ITEMS));

        // The city_weather_container only shows up when the screen's width is 600dp or larger
        if (findViewById(R.id.city_weather_container) != null) {
            mTwoPane = true;
        }*/


    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(CityGroup cityGroup) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.city_list);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(cityGroup.getCities()));
        // The city_weather_container only shows up when the screen's width is 600dp or larger
        if (findViewById(R.id.city_weather_container) != null) {
            mTwoPane = true;
        }
    }

    class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<City> mValues;

        SimpleItemRecyclerViewAdapter(List<City> items) {
            mValues = items;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mContentView.setText(mValues.get(position).getLocal());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        int selectedSong = holder.getAdapterPosition();
                        CityWeatherFragment fragment = CityWeatherFragment.newInstance(selectedSong);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.city_weather_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CityWeatherActivity.class);
                        intent.putExtra(CityUtils.CITY_ID_KEY, holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mContentView;
            City mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }


}