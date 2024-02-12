package com.develhope.es15customqueries.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> all() {
        return flightRepository.getAll();
    }

    //create a random flight
    public Flight generateFlight() {
        String[] descriptions = {"Business Trip", "Vacation", "Shipment"};
        String[] airportsFrom = {"CAG", "ROM", "MIL", "TOR", "NAP"};
        String[] airportsTo = {"BAR", "MDR", "LDN", "BRU", "LIS"};
        Random random = new Random();
        Flight flight = new Flight();
        flight.setDescription(descriptions[random.nextInt(descriptions.length)]);
        flight.setFromAirport(airportsFrom[random.nextInt(airportsFrom.length)]);
        flight.setToAirport(airportsTo[random.nextInt(airportsTo.length)]);
        flight.setStatus(FlightStatus.ONTIME);
        flightRepository.save(flight);
        return flight;
    }
}
