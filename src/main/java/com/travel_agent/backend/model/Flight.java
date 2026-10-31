package com.travel_agent.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String airline;
    private String source;
    private String destination;
    private String date;
    private Integer price;
}
