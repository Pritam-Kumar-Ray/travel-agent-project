package com.travel_agent.backend.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WeatherTools {

    // Get weather forecast
    // APIs to get forecast

    @Tool(description = "Get weather forecast for a given city and date, if not taken from the user, then use the flight date and the destination city to get the weather forecast")
    public String getWeatherForecast(String city, String date) {
        log.info("Fetching weather forecast for city: {}, date: {}", city, date);
        return "On " + date + ", the weather in " + city + " is sunny with a high of 25°C and a low of 15°C.";
    }


}
