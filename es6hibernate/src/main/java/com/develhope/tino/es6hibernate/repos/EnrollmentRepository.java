package com.develhope.tino.es6hibernate.repos;

import com.develhope.tino.es6hibernate.entities.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
