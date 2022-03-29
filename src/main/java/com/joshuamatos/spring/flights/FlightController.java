package com.joshuamatos.spring.flights;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/flights/tickets/total")
    public Result returnSumOfTickets(@RequestBody Flights flight) {
        return flightService.returnSumOfTickets(flight);
    }
}
