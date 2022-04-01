package com.joshuamatos.spring.flights;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flights {
    List<Tickets> tickets;
}
