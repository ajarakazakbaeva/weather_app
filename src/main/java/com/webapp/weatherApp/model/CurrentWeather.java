package com.webapp.weatherApp.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

//@Entity
public class CurrentWeather implements Serializable {

    private String city;

    private String descriptionNight;

    private String descriptionDay;
    private String temperatureNight;
    private String temperatureDay;


    public CurrentWeather(String city, String descriptionDay, String descriptionNight, String temperatureDay, String temperatureNight) {
        this.city = city;
        this.descriptionDay = descriptionDay;
        this.descriptionNight = descriptionNight;
        this.temperatureNight = temperatureNight;
        this.temperatureDay = temperatureDay;


    }

    public CurrentWeather() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescriptionNight() {
        return descriptionNight;
    }

    public void setDescriptionNight(String descriptionNight) {
        this.descriptionNight = descriptionNight;
    }

    public String getDescriptionDay() {
        return descriptionDay;
    }

    public void setDescriptionDay(String descriptionDay) {
        this.descriptionDay = descriptionDay;
    }

    public String getTemperatureNight() {
        return temperatureNight;
    }

    public void setTemperatureNight(String temperatureNight) {
        this.temperatureNight = temperatureNight;
    }

    public String getTemperatureDay() {
        return temperatureDay;
    }

    public void setTemperatureDay(String temperatureDay) {
        this.temperatureDay = temperatureDay;
    }
}
