package com.joshuamatos.spring.flights;

import org.springframework.stereotype.Service;

@Service
public class FlightService {
    public Result returnSumOfTickets(Flights flight) {
        Result results = new Result();
        int sum = 0;
        for (int i = 0; i < flight.getTickets().size(); i++) {
            sum += flight.getTickets().get(i).getPrice();
        }
        results.setResult(sum);
        return results;
    }
}
