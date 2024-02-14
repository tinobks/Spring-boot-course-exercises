package com.develhope.es16customqueries.flight;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    //retrieving all the flights in the db using pagination and returning them in ascending order by fromAirport
    @Query(value = "SELECT * FROM flights ORDER BY from_airport ASC", nativeQuery = true)
    Page<Flight> getAllPageable(Pageable pageable);

    List<Flight> findByStatus(FlightStatus status);

    @Query(value = "SELECT * FROM flights WHERE status = :status1 OR status = :status2", nativeQuery = true)
    List<Flight> findByCustomStatuses(@Param("status1") String status1, @Param("status2") String status2);

}
