package com.develhope.tino.es6hibernate.entities;

import com.develhope.tino.es6hibernate.entities.Class;
import com.develhope.tino.es6hibernate.entities.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student enrolled_student;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Class enrolled_class;
}
