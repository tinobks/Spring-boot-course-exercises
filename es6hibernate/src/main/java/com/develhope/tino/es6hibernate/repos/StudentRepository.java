package com.develhope.tino.es6hibernate.repos;

import com.develhope.tino.es6hibernate.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
