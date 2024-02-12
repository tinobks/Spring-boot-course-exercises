package com.develhope.es15customqueries.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAll() {
        return ResponseEntity.ok(flightService.all());
    }

    @PostMapping("/provision50flights")
    public ResponseEntity<Void> flights50() {
        for (int i = 0; i < 50; i++) {
            flightService.generateFlight();
        }
        return ResponseEntity.ok().build();
    }
}
