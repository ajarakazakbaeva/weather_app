package com.webapp.weatherApp.controller;

import com.webapp.weatherApp.model.CurrentWeather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

@Controller
public class WeatherController {
        @Value("${weatherURL}")
        private String url;
        private String location;
        private String description;
        private String temperature;

        @GetMapping("/current-weather")
        public String getCurrentWeather(@RequestParam(required = false) String city, Model model) throws IOException {

        CurrentWeather currentWeather = new CurrentWeather();


        // Parsing the XML
        Document doc = Jsoup
        .connect(url)
        .parser(Parser.xmlParser())
        .get();

        // Searching for the city
        if (city == null) {
                city = "Tartu";
        }
        // Getting all elements with "night" tag
        for(Element element: doc.getElementsByTag("night")){
                outerloop:
                for(Element e: element.getElementsByTag("place")) {
                        // For each element with "place" tag search for name, phenomenon, temparature
                        for (Element loc_elem : e.getElementsByTag("name")) {
                                // Reading values
                                location = loc_elem.ownText();
                                // Filtering values
                                if (location.equals(city)) {
                                        Element descr_elem = e.getElementsByTag("phenomenon").first();
                                        if (descr_elem == null) {
                                                description = "not available";
                                        } else {
                                                description = descr_elem.ownText();
                                        }
                                        Element temp_elem = e.getElementsByTag("tempmin").first();
                                        if (temp_elem == null) {
                                                temperature = "not available";
                                        } else {
                                                temperature = temp_elem.ownText();
                                        }

                                        currentWeather.setCity(location);
                                        currentWeather.setDescriptionNight(description);
                                        currentWeather.setTemperatureNight(temperature);
                                        break outerloop;
                                }

                        }
                }
        }

        // day
        // Getting all elements with "day" tag
        for(Element element: doc.getElementsByTag("day")){
                outerloop:
                for(Element e: element.getElementsByTag("place")) {
                        // For each element with "place" tag search for name, phenomenon, temparature
                        for (Element loc_elem : e.getElementsByTag("name")) {
                                // Reading values
                                location = loc_elem.ownText();
                                // Filtering values
                                if (location.equals(city)) {
                                        Element descr_elem = e.getElementsByTag("phenomenon").first();
                                        if (descr_elem == null) {
                                                description = "not available";
                                        } else {
                                                description = descr_elem.ownText();
                                        }
                                        Element temp_elem = e.getElementsByTag("tempmax").first();
                                        if (temp_elem == null) {
                                                temperature = "not available";
                                        } else {
                                                temperature = temp_elem.ownText();
                                        }

                                        currentWeather.setDescriptionDay(description);
                                        currentWeather.setTemperatureDay(temperature);
                                        break outerloop;
                                }

                        }
                }
        }

        model.addAttribute("currentWeather", currentWeather);
        return "currentWeather";

    }
}
