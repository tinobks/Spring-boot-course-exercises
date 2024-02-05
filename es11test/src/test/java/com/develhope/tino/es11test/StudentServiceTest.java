package com.develhope.tino.es11test;

import com.develhope.tino.es11test.student.Student;
import com.develhope.tino.es11test.student.StudentRepository;
import com.develhope.tino.es11test.student.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void checkStudentStatusTest() throws Exception{
        Student student = new Student();
        student.setName("Pippo");
        student.setSurname("Baudo");
        student.setWorking(true);

        Student studentFromDB = studentRepository.save(student);
        assertThat(studentFromDB.getId()).isNotNull();

        Student studentFromService = studentService.updateWorkingStatus(student.getId(), true);
        assertThat(studentFromService.getId()).isNotNull();
        assertThat(studentFromService.isWorking()).isTrue();

        Student studentById = studentRepository.findById(studentFromDB.getId()).get();
        assertThat(studentById).isNotNull();
        assertThat(studentById.getId()).isNotNull();
        assertThat(studentById.getId()).isEqualTo(studentFromDB.getId());
        assertThat(studentById.isWorking()).isTrue();
    }
}
