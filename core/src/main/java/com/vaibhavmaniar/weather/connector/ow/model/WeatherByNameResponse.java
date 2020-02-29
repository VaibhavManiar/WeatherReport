package com.vaibhavmaniar.weather.connector.ow.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class WeatherByNameResponse {
    public String cod;
    public double message;
    public int cnt;
    public List<WeatherDetails> list;

    @JsonCreator
    public WeatherByNameResponse(@JsonProperty("cod") String cod, @JsonProperty("message") double message, @JsonProperty("cnt") int cnt, @JsonProperty("list") List<WeatherDetails> list) {
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherDetails> getList() {
        return list;
    }

    public static class WeatherDetails {
        public Date dt;
        public Main main;
        public List<Weather> weather;
        public Clouds clouds;
        public Wind wind;
        public Sys sys;
        public String dt_txt;

        @JsonCreator
        public WeatherDetails(@JsonProperty("dt") Date dt, @JsonProperty("main") Main main, @JsonProperty("weather") List<Weather> weather, @JsonProperty("clouds") Clouds clouds, @JsonProperty("wind") Wind wind, @JsonProperty("sys") Sys sys, @JsonProperty("dt_txt") String dt_txt) {
            this.dt = dt;
            this.main = main;
            this.weather = weather;
            this.clouds = clouds;
            this.wind = wind;
            this.sys = sys;
            this.dt_txt = dt_txt;
        }

        public Date getDt() {
            return dt;
        }

        public Main getMain() {
            return main;
        }

        public List<Weather> getWeather() {
            return weather;
        }

        public Clouds getClouds() {
            return clouds;
        }

        public Wind getWind() {
            return wind;
        }

        public Sys getSys() {
            return sys;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public static class Main {
            public float temp;
            public float temp_min;
            public float temp_max;
            public float pressure;
            public float sea_level;
            public float grnd_level;
            public int humidity;
            public float temp_kf;

            @JsonCreator
            public Main(@JsonProperty("temp") float temp, @JsonProperty("temp_min") float temp_min, @JsonProperty("temp_max") float temp_max, @JsonProperty("pressure") float pressure, @JsonProperty("sea_level") float sea_level, @JsonProperty("grnd_level") float grnd_level, @JsonProperty("humidity") int humidity, @JsonProperty("temp_kf") float temp_kf) {
                this.temp = temp;
                this.temp_min = temp_min;
                this.temp_max = temp_max;
                this.pressure = pressure;
                this.sea_level = sea_level;
                this.grnd_level = grnd_level;
                this.humidity = humidity;
                this.temp_kf = temp_kf;
            }

            public float getTemp() {
                return temp;
            }

            public float getTemp_min() {
                return temp_min;
            }

            public float getTemp_max() {
                return temp_max;
            }

            public float getPressure() {
                return pressure;
            }

            public float getSea_level() {
                return sea_level;
            }

            public float getGrnd_level() {
                return grnd_level;
            }

            public int getHumidity() {
                return humidity;
            }

            public float getTemp_kf() {
                return temp_kf;
            }
        }

        public static class Weather {
            public String id;
            public String main;
            public String description;
            public String icon;

            @JsonCreator
            public Weather(@JsonProperty("id") String id, @JsonProperty("main") String main, @JsonProperty("description") String description, @JsonProperty("icon") String icon) {
                this.id = id;
                this.main = main;
                this.description = description;
                this.icon = icon;
            }

            public String getId() {
                return id;
            }

            public String getMain() {
                return main;
            }

            public String getDescription() {
                return description;
            }

            public String getIcon() {
                return icon;
            }
        }

        public static class Clouds {
            public int all;

            @JsonCreator
            public Clouds(@JsonProperty("all") int all) {
                this.all = all;
            }

            public int getAll() {
                return all;
            }
        }

        public static class Wind {
            public float speed;
            public float deg;

            @JsonCreator
            public Wind(@JsonProperty("speed") float speed, @JsonProperty("deg") float deg) {
                this.speed = speed;
                this.deg = deg;
            }

            public float getSpeed() {
                return speed;
            }

            public float getDeg() {
                return deg;
            }
        }

        public static class Sys {
            public String pod;

            @JsonCreator
            public Sys(@JsonProperty("pod") String pod) {
                this.pod = pod;
            }

            public String getPod() {
                return pod;
            }
        }

    }
}
