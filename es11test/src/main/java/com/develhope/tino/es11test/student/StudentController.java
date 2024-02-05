package com.develhope.tino.es11test.student;

import com.develhope.tino.es11test.student.Student;
import com.develhope.tino.es11test.student.StudentRepository;
import com.develhope.tino.es11test.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/create")
    public @ResponseBody ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> allStudents = studentService.getAll();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<Student> updateStatus(@PathVariable Long id, boolean isWorking) {
        Student student = studentService.updateWorkingStatus(id, isWorking);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
