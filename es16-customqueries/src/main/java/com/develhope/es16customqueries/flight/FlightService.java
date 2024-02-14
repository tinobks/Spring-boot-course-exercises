package com.develhope.es16customqueries.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> allPaged(int page, int size) {
        return flightRepository.getAllPageable(Pageable.ofSize(size).withPage(page));
    }

    //create a random flight
    public void generateFlight() {
        String[] descriptions = {"Business Trip", "Vacation", "Shipment"};
        String[] airportsFrom = {"CAG", "ROM", "MIL", "TOR", "NAP"};
        String[] airportsTo = {"BAR", "MDR", "LDN", "BRU", "LIS"};
        FlightStatus[] statuses = FlightStatus.values(); //aggiungo array con i possibili status dell enum
        Random random = new Random();
        Flight flight = new Flight();
        flight.setDescription(descriptions[random.nextInt(descriptions.length)]);
        flight.setFromAirport(airportsFrom[random.nextInt(airportsFrom.length)]);
        flight.setToAirport(airportsTo[random.nextInt(airportsTo.length)]);
        flight.setStatus(statuses[random.nextInt(statuses.length)]); //status is chosen randomly
        flightRepository.save(flight);
    }

    //get all flights ONTIME
    public List<Flight> onTimeFlights() {
        return flightRepository.findByStatus(FlightStatus.ONTIME);
    }

    public List<Flight> getFlightsByCustomStatus(FlightStatus p1, FlightStatus p2) {
        return flightRepository.findByCustomStatuses(p1.toString(),p2.toString());
    }
}
