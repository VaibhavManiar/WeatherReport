package com.vaibhavmaniar.weather.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhavmaniar.weather.connector.WeatherReportConnector;
import com.vaibhavmaniar.weather.connector.ow.OpenWeatherConnector;
import com.vaibhavmaniar.weather.util.JettyRestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherReportConfiguration {

    @Bean("weatherReportConnector")
    public WeatherReportConnector getWeatherReportConnector(JettyRestUtil jettyRestUtil) {
        return new OpenWeatherConnector(jettyRestUtil);
    }

    @Bean("jettyRestUtil")
    public JettyRestUtil getJettyRestUtil(ObjectMapper mapper) {
        return new JettyRestUtil(mapper, 10, 1000, 10000, 10);
    }
}