package com.develhope.es16customqueries.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/all")
    public ResponseEntity<Page<Flight>> getAllPaged(@RequestParam(name = "page",defaultValue = "0") int page,
                                                    @RequestParam(name = "size",defaultValue = "10") int size) {
        return ResponseEntity.ok(flightService.allPaged(page, size));
    }

    @PostMapping("/provisionFlights")
    public ResponseEntity<Void> flights(@RequestParam(name = "n", defaultValue = "100") int n) {
        for (int i = 0; i < n; i++) {
            flightService.generateFlight();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ontime")
    public ResponseEntity<List<Flight>> onTimeFlights() {
        return ResponseEntity.ok(flightService.onTimeFlights());
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<List<Flight>> getByCustomStatus(@RequestParam(name = "p1") FlightStatus p1,
                                                          @RequestParam(name = "p2") FlightStatus p2) {
        return ResponseEntity.ok(flightService.getFlightsByCustomStatus(p1,p2));
    }
}
