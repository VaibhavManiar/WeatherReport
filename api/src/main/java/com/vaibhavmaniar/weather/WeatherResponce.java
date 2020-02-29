package com.vaibhavmaniar.weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public class WeatherResponce {
    public final float minTemprature;
    public final float maxTemprature;
    public final Unit measureUnit;
    public final int cloudPercentage;
    public final int humidityPercentage;
    public final String message;
    public final ZonedDateTime dateTime;

    @JsonCreator
    public WeatherResponce(@JsonProperty("minTemprature") float minTemprature,
                           @JsonProperty("maxTemprature") float maxTemprature,
                           @JsonProperty("measureUnit") Unit measureUnit,
                           @JsonProperty("cloudPercentage") int cloudPercentage,
                           @JsonProperty("humidityPercentage") int humidityPercentage,
                           @JsonProperty("message") String message,
                           @JsonProperty("dateTime") ZonedDateTime dateTime) {
        this.minTemprature = minTemprature;
        this.maxTemprature = maxTemprature;
        this.measureUnit = measureUnit;
        this.cloudPercentage = cloudPercentage;
        this.humidityPercentage = humidityPercentage;
        this.message = message;
        this.dateTime = dateTime;
    }

    public float getMinTemprature() {
        return minTemprature;
    }

    public float getMaxTemprature() {
        return maxTemprature;
    }

    public Unit getMeasureUnit() {
        return measureUnit;
    }

    public int getCloudPercentage() {
        return cloudPercentage;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public String getMessage() {
        return message;
    }

    public enum Unit {
        CELCIUS, FARANITE, KELVIN;
    }

}
