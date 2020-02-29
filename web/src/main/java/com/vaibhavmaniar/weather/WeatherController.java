package com.vaibhavmaniar.weather;


import com.vaibhavmaniar.weather.connector.WeatherReportConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    public WeatherReportConnector weatherReportConnector;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponce> getWeatherReport(@PathVariable("city") String city) {
        WeatherResponce weatherResponce = weatherReportConnector.get(city);
        return new ResponseEntity<>(weatherResponce, HttpStatus.OK);
    }

}
