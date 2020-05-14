package com.example.weatherforecastfor5days;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.weatherforecastfor5days.WeatherShowActivity;

public class MoreDetailsActivity extends AppCompatActivity {

    public String city;


    TextView Date_tv1;
    TextView humidity_tv1;
    TextView pressure_tv1;
    TextView windspeed_tv1;
    ImageView weather_imageView1;
    TextView desc1;

    TextView Date_tv2;
    TextView humidity_tv2;
    TextView pressure_tv2;
    TextView windspeed_tv2;
    ImageView weather_imageView2;
    TextView desc2;

    TextView Date_tv3;
    TextView humidity_tv3;
    TextView pressure_tv3;
    TextView windspeed_tv3;
    ImageView weather_imageView3;
    TextView desc3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        Date_tv1 = findViewById(R.id.Date_tv1);
        humidity_tv1 = findViewById(R.id.humidity_tv1);
        pressure_tv1 = findViewById(R.id.pressure_tv1);
        windspeed_tv1 = findViewById(R.id.windspeed_tv1);
        weather_imageView1 = findViewById(R.id.weather_imageView1);
        desc1 = findViewById(R.id.desc_tv1);

        Date_tv2 = findViewById(R.id.Date_tv2);
        humidity_tv2 = findViewById(R.id.humidity_tv2);
        pressure_tv2 = findViewById(R.id.pressure_tv2);
        windspeed_tv2 = findViewById(R.id.windspeed_tv2);
        weather_imageView2 = findViewById(R.id.weather_imageView2);
        desc2 = findViewById(R.id.desc_tv2);


        Date_tv3 = findViewById(R.id.Date_tv3);
        humidity_tv3 = findViewById(R.id.humidity_tv3);
        pressure_tv3 = findViewById(R.id.pressure_tv3);
        windspeed_tv3 = findViewById(R.id.windspeed_tv3);
        weather_imageView3 = findViewById(R.id.weather_imageView3);
        desc3 = findViewById(R.id.desc_tv3);

        showOtherDetails();






    }


    public void showOtherDetails(){
        Intent intent = getIntent();
        city = intent.getStringExtra(WeatherShowActivity.CITY_NAME);
        showToday();
        showTomorrow();
        showDayAfterTomorrow();
    }

    public void showToday(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(city , "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();

                String date = (modelClasses.list.get(0).dt_txt).substring(0,10);
                Date_tv1.setText(date);

                double humidity = (modelClasses.list.get(0).main.humidity);
                double pressure = Math.round(modelClasses.list.get(0).main.pressure);
                double windSpeed = Math.round(modelClasses.list.get(0).wind.speed);
                double windDeg = (modelClasses.list.get(0).wind.deg);
                String dir = "";

                if(windDeg >= 0 && windDeg < 90){
                    dir = "North";
                }

                if(windDeg >= 90 && windDeg < 180){
                    dir = "East";
                }

                if(windDeg >= 180 && windDeg < 270){
                    dir = "South";
                }

                if(windDeg >= 270 && windDeg < 360){
                    dir = "West";
                }

                humidity_tv1.setText(humidity + "%");
                pressure_tv1.setText(pressure + "hpa");
                windspeed_tv1.setText(windSpeed + " m/s"+ "("+dir + ")");



                String desc = (modelClasses.list.get(0).weather.get(0).description);

                if(desc.equals("moderate rain") || desc.equals("light rain")|| desc.equals("shower rain") || desc.equals("heavy intensity shower rain")||desc.equals("light intensity drizzle")||desc.equals("heavy intensity rain")){

                    weather_imageView1.setImageResource(R.drawable.rainyday);
                }

                if(desc.equals("clear sky")){
                    weather_imageView1.setImageResource(R.drawable.clearday);
                }

                if(desc.equals("overcast clouds")||desc.equals("scattered clouds")||desc.equals("broken clouds") || desc.equals("few clouds")){
                    weather_imageView1.setImageResource(R.drawable.cloudyday);
                }

                if(desc.equals("haze")||desc.equals("fog")||desc.equals("mist")){
                    weather_imageView1.setImageResource(R.drawable.foggyday);
                }

                desc1.setText(desc);



            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d ("Error Message",t.getLocalizedMessage());
            }
        });
    }


    public void showTomorrow(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(city , "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();


                String date = (modelClasses.list.get(8).dt_txt).substring(0,10);
                Date_tv2.setText(date);

                double humidity = (modelClasses.list.get(8).main.humidity);
                double pressure = Math.round(modelClasses.list.get(8).main.pressure);
                double windSpeed = Math.round(modelClasses.list.get(8).wind.speed);
                double windDeg = (modelClasses.list.get(8).wind.deg);
                String dir = "";

                if(windDeg >= 0 && windDeg < 90){
                    dir = "North";
                }

                if(windDeg >= 90 && windDeg < 180){
                    dir = "East";
                }

                if(windDeg >= 180 && windDeg < 270){
                    dir = "South";
                }

                if(windDeg >= 270 && windDeg < 360){
                    dir = "West";
                }

                humidity_tv2.setText(humidity + "%");
                pressure_tv2.setText(pressure + "hpa");
                windspeed_tv2.setText(windSpeed + " m/s"+ "("+dir + ")");

                String desc = (modelClasses.list.get(8).weather.get(0).description);

                if(desc.equals("moderate rain") || desc.equals("light rain")|| desc.equals("shower rain") || desc.equals("heavy intensity shower rain")||desc.equals("light intensity drizzle")||desc.equals("heavy intensity rain")){

                    weather_imageView2.setImageResource(R.drawable.rainyday);
                }

                if(desc.equals("clear sky")){
                    weather_imageView2.setImageResource(R.drawable.clearday);
                }

                if(desc.equals("overcast clouds")||desc.equals("scattered clouds")||desc.equals("broken clouds") || desc.equals("few clouds")){
                    weather_imageView2.setImageResource(R.drawable.cloudyday);
                }

                if(desc.equals("haze")||desc.equals("fog")||desc.equals("mist")){
                    weather_imageView2.setImageResource(R.drawable.foggyday);
                }

                desc2.setText(desc);



            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d ("Error Message",t.getLocalizedMessage());
            }
        });
    }


    public void showDayAfterTomorrow(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelClass> details = apiInterface.getWeatherDetails(city , "b10055a15ba615092f8cbb5686aa831f");

        details.enqueue(new Callback<ModelClass>() {
            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {


                ModelClass modelClasses = response.body();


                String date = (modelClasses.list.get(16).dt_txt).substring(0,10);
                Date_tv3.setText(date);

                double humidity = (modelClasses.list.get(16).main.humidity);
                double pressure = Math.round(modelClasses.list.get(16).main.pressure);
                double windSpeed = Math.round(modelClasses.list.get(16).wind.speed);
                double windDeg = (modelClasses.list.get(16).wind.deg);
                String dir = "";

                if(windDeg >= 0 && windDeg < 90){
                    dir = "North";
                }

                if(windDeg >= 90 && windDeg < 180){
                    dir = "East";
                }

                if(windDeg >= 180 && windDeg < 270){
                    dir = "South";
                }

                if(windDeg >= 270 && windDeg < 360){
                    dir = "West";
                }

                humidity_tv3.setText(humidity + "%");
                pressure_tv3.setText(pressure + "hpa");
                windspeed_tv3.setText(windSpeed + " m/s"+ "("+dir + ")");

                String desc = (modelClasses.list.get(16).weather.get(0).description);

                if(desc.equals("moderate rain") || desc.equals("light rain")|| desc.equals("shower rain") || desc.equals("heavy intensity shower rain")||desc.equals("light intensity drizzle")||desc.equals("heavy intensity rain")){

                    weather_imageView3.setImageResource(R.drawable.rainyday);
                }

                if(desc.equals("clear sky")){
                    weather_imageView3.setImageResource(R.drawable.clearday);
                }

                if(desc.equals("overcast clouds")||desc.equals("scattered clouds")||desc.equals("broken clouds") || desc.equals("few clouds")){
                    weather_imageView3.setImageResource(R.drawable.cloudyday);
                }

                if(desc.equals("haze")||desc.equals("fog")||desc.equals("mist")){
                    weather_imageView3.setImageResource(R.drawable.foggyday);
                }

                desc3.setText(desc);





            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

                Log.d ("Error Message",t.getLocalizedMessage());
            }
        });
    }
}
