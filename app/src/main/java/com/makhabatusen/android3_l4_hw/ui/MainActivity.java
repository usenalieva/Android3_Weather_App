package com.makhabatusen.android3_l4_hw.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.makhabatusen.android3_l4_hw.R;
import com.makhabatusen.android3_l4_hw.databinding.ActivityMainBinding;
import com.makhabatusen.android3_l4_hw.model.WeatherByCity;
import com.makhabatusen.android3_l4_hw.model.WeeklyReport;
import com.makhabatusen.android3_l4_hw.remote.RetrofitBuilder;
import com.makhabatusen.android3_l4_hw.weeklyReport.RecyclerDecoration;
import com.makhabatusen.android3_l4_hw.weeklyReport.WeeklyReportAdapter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        setAdapter();
        getWeather();
    }

    private void setAdapter() {
        ui.rvWeeklyReport.setAdapter(new WeeklyReportAdapter(
                List.of(new WeeklyReport("13 March", R.drawable.ic_clouds, "12", "7"),
                        new WeeklyReport("14 March", R.drawable.ic_rain, "10", "5"),
                        new WeeklyReport("15 March", R.drawable.ic_mist, "1", "0"),
                        new WeeklyReport("16 March", R.drawable.ic_snow, "-5", "-2"),
                        new WeeklyReport("17 March", R.drawable.ic_clear_day, "4", "9"),
                        new WeeklyReport("18 March", R.drawable.ic_sunny, "17", "10")
                )));

        int sidePadding = getResources().getDimensionPixelSize(R.dimen.sidePadding);
        int topPadding = getResources().getDimensionPixelSize(R.dimen.topPadding);
        ui.rvWeeklyReport.addItemDecoration(  new RecyclerDecoration(sidePadding,topPadding ));
    }

    private void getWeather() {
        String API_KEY = "4bbf5a1ed98cd9f688ebb3651474cdd2";
        RetrofitBuilder.getInstance().getWeather("Guangzhou", API_KEY, "metric").enqueue(new Callback<WeatherByCity>() {
            @Override
            public void onResponse(Call<WeatherByCity> call, Response<WeatherByCity> response) {
                setViews(response.body());
            }

            @Override
            public void onFailure(Call<WeatherByCity> call, Throwable t) {
            }
        });
    }

    private void setViews(WeatherByCity body) {
        setWeatherIndicators(body);
        setTextViews(body);
        setTiming(body);
    }

    private void setWeatherIndicators(WeatherByCity body) {
        String result = "https://openweathermap.org/img/wn/" + body.getWeather().get(0).getIcon() + "@2x.png";
        Glide
                .with(this)
                .load(result)
                .centerCrop()
                .into(ui.ivWeatherIndicator);
    }


    private void setTiming(WeatherByCity body) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ROOT);
        SimpleDateFormat timeFormatAmPm = new SimpleDateFormat("hh:mm a", Locale.ROOT);
        long getDateAndTime = Long.valueOf(body.getDt()) * 1000;

        ui.tvDate.setText(dateFormat.format(getDateAndTime));
        ui.tvTime.setText(timeFormatAmPm.format(getDateAndTime));
        ui.tvSunrise.setText(timeFormatAmPm.format(Long.valueOf(body.getSys().getSunrise()) * 1000));
        ui.tvSunset.setText(timeFormatAmPm.format(Long.valueOf(body.getSys().getSunset()) * 1000));

        double dayTime = (Double.valueOf(body.getSys().getSunset()) - Double.valueOf(body.getSys().getSunrise())) / 3600;
        String stringDayTime = new DecimalFormat("##.##").format(dayTime);

        ui.tvDaytimeHr.setText(stringDayTime.substring(0, 2));
        ui.tvDaytimeMinutes.setText(stringDayTime.substring(3));

        SimpleDateFormat timeFormat24hr = new SimpleDateFormat("HH:mm", Locale.ROOT);
        String timeDayNight = timeFormat24hr.format(getDateAndTime);
        String hourOfDayTime = timeDayNight.substring(0, 2);
        boolean isDayTime = Integer.parseInt(hourOfDayTime) >= 5 && Integer.parseInt(hourOfDayTime) <= 18;
        if (isDayTime)
            ui.ivCity.setImageResource(R.drawable.ic_day_city);
        else ui.ivCity.setImageResource(R.drawable.ic_night_city);
    }


    private void setTextViews(WeatherByCity body) {
        ui.tvCityName.setText(String.format("%s, %s", body.getName(), body.getSys().getCountry()));

        ui.tvTemp.setText(String.valueOf((int) Math.round(body.getMain().getTemp())));
        ui.tvWeatherIndicator.setText(body.getWeather().get(0).getMain());

        ui.tvMaxTemp.setText(String.valueOf((int) Math.round(body.getMain().getTempMax())));
        ui.tvMinTemp.setText(String.valueOf((int) Math.round(body.getMain().getTempMin())));

        ui.tvHumidity.setText(String.valueOf(body.getMain().getHumidity()));

        ui.tvPressure.setText(String.valueOf((double) body.getMain().getPressure() / 1000));

        ui.tvWind.setText(String.valueOf(Double.valueOf(body.getWind().getSpeed())));
    }
}
