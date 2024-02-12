package com.develhope.es15customqueries.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flights", nativeQuery = true)
    public List<Flight> getAll();
}
