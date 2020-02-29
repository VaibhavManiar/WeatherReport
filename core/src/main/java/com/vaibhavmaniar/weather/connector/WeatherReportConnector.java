package com.vaibhavmaniar.weather.connector;

import com.vaibhavmaniar.weather.WeatherResponce;
public interface WeatherReportConnector {
    WeatherResponce get(String cityName);
}
