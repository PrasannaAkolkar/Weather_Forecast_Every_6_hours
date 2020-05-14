package com.example.weatherforecastfor5days;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherShowActivity extends AppCompatActivity {


    private static String[] CITIES = new String[]{"Pune", "Nashik", "Nagpur", "Jalna", "Pimpri", "Oslo", "London", "Peru", "Latur", "Patna", "Mumbai", "Aurangabad", "Manchester", "Jalgaon"};

    public String cityName;
    public String appid2;
    public static final String CITY_NAME = "CityName";


    ImageView imageView;
    ProgressBar progressBar;
    TextView temp_tv;
    TextView description_tv;
    TextView dt_txt_tv;
    TextView time_tv1;
    TextView desc_tv1;
    TextView temp_max_tv1;
    TextView temp_min_tv1;
    TextView time_tv2;
    TextView desc_tv2;
    TextView temp_max_tv2;
    TextView temp_min_tv2;
    TextView time_tv3;
    TextView desc_tv3;
    TextView temp_max_tv3;
    TextView temp_min_tv3;
    TextView time_tv4;
    TextView desc_tv4;
    TextView temp_max_tv4;
    TextView temp_min_tv4;


    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;


    public AutoCompleteTextView city_tv;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show);

        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        temp_tv = findViewById(R.id.temp_tv);
        description_tv = findViewById(R.id.description_tv);
        time_tv1 = findViewById(R.id.time_tv1);
        desc_tv1 = findViewById(R.id.desc_tv1);
        temp_max_tv1 = findViewById(R.id.temp_max_tv1);
        temp_min_tv1 = findViewById(R.id.temp_min_tv1);
        time_tv2 = findViewById(R.id.time_tv2);
        desc_tv2 = findViewById(R.id.desc_tv2);
        temp_max_tv2 = findViewById(R.id.temp_max_tv2);
        temp_min_tv2 = findViewById(R.id.temp_min_tv2);
        time_tv3 = findViewById(R.id.time_tv3);
        desc_tv3 = findViewById(R.id.desc_tv3);
        temp_max_tv3 = findViewById(R.id.temp_max_tv3);
        temp_min_tv3 = findViewById(R.id.temp_min_tv3);
        time_tv4 = findViewById(R.id.time_tv4);
        desc_tv4 = findViewById(R.id.desc_tv4);
        temp_max_tv4 = findViewById(R.id.temp_max_tv4);
        temp_min_tv4 = findViewById(R.id.temp_min_tv4);
        city_tv = findViewById(R.id.city_tv);
        searchButton = findViewById(R.id.search_btn);


        card1 = findViewById(R.id.card_view1);
        card2 = findViewById(R.id.card_view2);
        card3 = findViewById(R.id.card_view3);
        card4 = findViewById(R.id.card_view4);


        appid2 = "b10055a15ba615092f8cbb5686aa831f";


        progressBar.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CITIES);
        city_tv.setAdapter(adapter);


    }

    public void getDetails(View view) {
        progressBar.setVisibility(View.VISIBLE);
        try {
            cityName = city_tv.getText().toString();
        } catch (Exception e) {
            e.fillInStackTrace();
            Toast.makeText(this, "Enter a Valid City Name", Toast.LENGTH_SHORT).show();
        }
        getCurrentWeather();

        getWeatherAfterSixHours();
        getWeatherAfterTwelveHours();
        getWeatherAfterEighteenHours();

        progressBar.setVisibility(View.INVISIBLE);
        card1.setVisibility(View.VISIBLE);
        card2.setVisibility(View.VISIBLE);
        card3.setVisibility(View.VISIBLE);
        card4.setVisibility(View.VISIBLE);


    }


    public void getCurrentWeather() {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(cityName, appid2);

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();

                city_tv.setText(modelClasses.city.name);

                String date = (modelClasses.list.get(0).dt_txt).substring(0, 10);
                String time = (modelClasses.list.get(0).dt_txt).substring(11, 19);

                double temp = (modelClasses.list.get(0).main.temp);
                long tempInCelcius = Math.round(temp - 273.15);
                temp_tv.setText(String.valueOf(tempInCelcius) + "°" + "C");


                time_tv1.setText(time);

                double tempMax = (modelClasses.list.get(0).main.temp_max);
                long tempInCelciusMax = Math.round(tempMax - 273.15);
                temp_max_tv1.setText(String.valueOf(tempInCelciusMax) + "°");

                double tempMin = (modelClasses.list.get(0).main.temp_min);
                long tempInCelciusMin = Math.round(tempMin - 273.15);
                temp_min_tv1.setText(String.valueOf(tempInCelciusMin) + "°");

                String desc = (modelClasses.list.get(0).weather.get(0).description);

                if (desc.equals("moderate rain") || desc.equals("light rain") || desc.equals("shower rain") || desc.equals("heavy intensity shower rain") || desc.equals("light intensity drizzle") || desc.equals("heavy intensity rain")) {

                    imageView.setImageResource(R.drawable.rainimage);
                }

                if (desc.equals("clear sky")) {
                    imageView.setImageResource(R.drawable.clearsky2);
                }

                if (desc.equals("overcast clouds") || desc.equals("scattered clouds") || desc.equals("broken clouds") || desc.equals("few clouds")) {
                    imageView.setImageResource(R.drawable.clouds);
                }

                if (desc.equals("haze") || desc.equals("fog") || desc.equals("mist")) {
                    imageView.setImageResource(R.drawable.fog);
                }
                if (desc.equals("smoke")) {
                    imageView.setImageResource(R.drawable.smoke);
                }
                if (desc.equals("thunderstorm") || desc.equals("thunderstorm with rain")) {
                    imageView.setImageResource(R.drawable.thunderstorm4);
                }


                description_tv.setText(modelClasses.list.get(0).weather.get(0).description);

                desc_tv1.setText(modelClasses.list.get(0).weather.get(0).description);


            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d("Error Message", t.getLocalizedMessage());
            }
        });

    }

    private void getWeatherAfterTwelveHours() {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(cityName, "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();
                String date = (modelClasses.list.get(4).dt_txt).substring(0, 10);
                String time = (modelClasses.list.get(4).dt_txt).substring(11, 19);


                // dt_txt_tv.setText(date);

                time_tv3.setText(time);

                double tempMax = (modelClasses.list.get(4).main.temp_max);
                long tempInCelciusMax = Math.round(tempMax - 273.15);
                temp_max_tv3.setText(String.valueOf(tempInCelciusMax) + "°");

                double tempMin = (modelClasses.list.get(4).main.temp_min);
                long tempInCelciusMin = Math.round(tempMin - 273.15);
                temp_min_tv3.setText(String.valueOf(tempInCelciusMin) + "°");

                desc_tv3.setText(modelClasses.list.get(4).weather.get(0).description);


            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d("Error Message", t.getLocalizedMessage());
            }
        });


    }

    private void getWeatherAfterSixHours() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(cityName, "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();
                String date = (modelClasses.list.get(2).dt_txt).substring(0, 10);
                String time = (modelClasses.list.get(2).dt_txt).substring(11, 19);


                // dt_txt_tv.setText(date);

                time_tv2.setText(time);

                double tempMax = (modelClasses.list.get(2).main.temp_max);
                long tempInCelciusMax = Math.round(tempMax - 273.15);
                temp_max_tv2.setText(String.valueOf(tempInCelciusMax) + "°");

                double tempMin = (modelClasses.list.get(2).main.temp_min);
                long tempInCelciusMin = Math.round(tempMin - 273.15);
                temp_min_tv2.setText(String.valueOf(tempInCelciusMin) + "°");

                desc_tv2.setText(modelClasses.list.get(2).weather.get(0).description);


            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d("Error Message", t.getLocalizedMessage());
            }
        });


    }

    private void getWeatherAfterEighteenHours() {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(cityName, "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();
                String date = (modelClasses.list.get(6).dt_txt).substring(0, 10);
                String time = (modelClasses.list.get(6).dt_txt).substring(11, 19);


                time_tv4.setText(time);

                double tempMax = (modelClasses.list.get(6).main.temp_max);
                long tempInCelciusMax = Math.round(tempMax - 273.15);
                temp_max_tv4.setText(String.valueOf(tempInCelciusMax) + "°");

                double tempMin = (modelClasses.list.get(6).main.temp_min);
                long tempInCelciusMin = Math.round(tempMin - 273.15);
                temp_min_tv4.setText(String.valueOf(tempInCelciusMin) + "°");

                desc_tv4.setText(modelClasses.list.get(6).weather.get(0).description);


            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d("Error Message", t.getLocalizedMessage());
            }
        });


    }

    public void moreDetails(View view) {

        if (!city_tv.getText().toString().isEmpty()) {

            Intent intent = new Intent(WeatherShowActivity.this, MoreDetailsActivity.class);
            intent.putExtra(CITY_NAME, cityName);
            startActivity(intent);
        } else {

            Toast.makeText(this, "Please enter the city to get details", Toast.LENGTH_SHORT).show();

        }
    }
}