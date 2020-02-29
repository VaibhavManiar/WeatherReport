package com.vaibhavmaniar.weather.connector.ow;

import com.vaibhavmaniar.weather.WeatherResponce;
import com.vaibhavmaniar.weather.connector.WeatherReportConnector;
import com.vaibhavmaniar.weather.connector.ow.model.WeatherByNameResponse;
import com.vaibhavmaniar.weather.util.JettyRestUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class OpenWeatherConnector implements WeatherReportConnector {
    private static final String apiKey = "aa1a04c506c92757cdb020912a9f2cc1";
    private final JettyRestUtil restUtil;

    public OpenWeatherConnector(JettyRestUtil restUtil) {
        this.restUtil = restUtil;
    }

    @Override
    public WeatherResponce get(String cityName) {
        WeatherByNameResponse weatherByNameResponse = restUtil.get("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey, WeatherByNameResponse.class);
        WeatherByNameResponse.WeatherDetails weatherDetails = weatherByNameResponse.getList().get(0);
        WeatherByNameResponse.WeatherDetails.Main main = weatherDetails.getMain();
        WeatherByNameResponse.WeatherDetails.Clouds clouds = weatherDetails.getClouds();
        String message = clouds.getAll() > 0 ? "Carry umbrella" : " 'Use sunscreen lotion";
        return new WeatherResponce(main.getTemp_min(), main.getTemp_max(), WeatherResponce.Unit.KELVIN, clouds.all, main.humidity, message, zonedDateTime(weatherDetails.dt));
    }

    ZonedDateTime zonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
    }
}
