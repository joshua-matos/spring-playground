package com.joshuamatos.spring.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    Passenger passenger;
    private int price;
}
