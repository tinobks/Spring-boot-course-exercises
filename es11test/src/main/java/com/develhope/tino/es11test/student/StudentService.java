package com.develhope.tino.es11test.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateWorkingStatus(Long id, boolean isWorking) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setWorking(isWorking);
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
