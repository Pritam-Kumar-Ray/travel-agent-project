package com.travel_agent.backend.tools;

import com.travel_agent.backend.model.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HotelTools {

    List<Hotel> hotels = new ArrayList<>(List.of(
            new Hotel("Taj Hotel", "Mumbai", "Marine Drive", 10000, 4.5),
            new Hotel("Oberoi", "Delhi", "Connaught Place", 15000, 4.8),
            new Hotel("ITC Maurya", "Bangalore", "Indiranagar", 12000, 4.6),
            new Hotel("The Leela", "Kolkata", "Park Street", 9000, 4.4),
            new Hotel("Hyatt Regency", "Chennai", "Anna Salai", 11000, 4.7),
            new Hotel("Radisson Blu", "Pune", "Koregaon Park", 8000, 4.3),
            new Hotel("Novotel", "Kolkata", "Newtown", 9500, 4.5)
    ));

    // Search hotel
    // APIs to search hotel
    @Tool(
            name = "searchHotel",
            description = "Search for hotels based on city and max price and return the hotel name, location, price, and rating of the available hotels"
    )
    public String searchHotel(String city, String maxPrice){

        List<Hotel> filteredHotels = hotels.stream()
                .filter(f -> f.city().equalsIgnoreCase(city) &&
                        f.price() <= Integer.parseInt(maxPrice))
                .toList();

        if(filteredHotels.isEmpty()){
            return "No hotels are available for the given criteria.";
        }

        String result = filteredHotels.stream()
                .map(f -> f.name() + ", Location: " + f.location() + ", Price: " + f.price() + ", Rating: " + f.rating())
                .reduce((a, b) -> a + "; \n" + b)
                .orElse("");

        return result;
    }


    // Hotel status


    // Book hotel


}
