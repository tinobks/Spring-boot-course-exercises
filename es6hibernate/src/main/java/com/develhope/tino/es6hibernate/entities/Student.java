package com.develhope.tino.es6hibernate.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "enrolled_student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollment;
}
