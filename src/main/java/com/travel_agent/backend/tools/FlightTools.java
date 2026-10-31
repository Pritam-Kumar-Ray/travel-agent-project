package com.travel_agent.backend.tools;

import com.travel_agent.backend.model.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FlightTools {

    List<Flight> flights = new ArrayList<>(List.of(
            new Flight("Indigo", "Kolkata", "Delhi", "2023-10-01", 30000),
            new Flight("Air India", "Delhi", "Kolkata", "2023-10-02", 21000),
            new Flight("SpiceJet", "Kolkata", "Mumbai", "2023-10-03", 15000),
            new Flight("Vistara", "Mumbai", "Kolkata", "2023-10-04", 20050),
            new Flight("GoAir", "Delhi", "Mumbai", "2023-10-05", 18000),
            new Flight("IndiGo", "Bangalore", "Mumbai", "2023-10-06", 22000),
            new Flight("Air India", "Mumbai", "Chennai", "2023-10-07", 28000)
    ));

    // Search flight
    // APIs to search flight
    @Tool(
            name = "searchFlight",
            description = "Search for flights based on source, destination and return the airline and price of the available flights"
    )

    public String searchFlights(String source, String destination) {
        log.info("Searching for flights from {} to {}", source, destination);

        List<Flight> filteredFlights = flights.stream()
                .filter(f ->
                        f.getDestination().equalsIgnoreCase(destination) &&
                                f.getSource().equalsIgnoreCase(source))
                .toList();

        if (filteredFlights.isEmpty()) {
            return "No flights are available for the given criteria.";
        }

        return filteredFlights.stream()
                .map(f -> "Airline: " + f.getAirline() + ", Price: " + f.getPrice() + ", Date: " + f.getDate())
                .collect(Collectors.joining(", "));
    }


    // Flight status


    // Book flight


}
