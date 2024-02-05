package com.develhope.tino.es11test;


import com.develhope.tino.es11test.student.Student;
import com.develhope.tino.es11test.student.StudentController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired StudentController studentController;

	@Autowired
	private ObjectMapper objectMapper;

	private Student createStudent() throws Exception {
		Student student = new Student();
		student.setName("Pippo");
		student.setSurname("Baudo");
		student.setWorking(true);
		return createStudentWithAttribute(student);
	}

	private Student createStudentWithAttribute(Student student) throws Exception {
		MvcResult result = createStudentRequest();

        return objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
	}
	private MvcResult createStudentRequest() throws Exception {
		Student student = new Student();
		student.setName("Pippo");
		student.setSurname("Baudo");
		student.setWorking(true);

		String studentJSON = objectMapper.writeValueAsString(student);

		return this.mockMvc.perform(post("/v1/students/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(studentJSON))
				.andDo(print())
				.andReturn();
	}

	private Student getStudentNoResponse(Long id) throws Exception {
		MvcResult result = this.mockMvc.perform(get("/v1/students/"+id))
				.andDo(print())
				.andReturn();

		if (result.getResponse().getContentLength() == 0)
			return null;

		return objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
	}

	@Test
	void createStudentTest() throws Exception {
		MvcResult result = createStudentRequest();

		Student responseStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(responseStudent.getId()).isNotNull();
	}

	@Test
	void readStudentListTest() throws Exception {
		createStudentRequest();

		MvcResult result = this.mockMvc.perform(get("/v1/students/all"))
				.andDo(print())
				.andReturn();

		List<Student> responseStudents = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertThat(responseStudents.size()).isNotZero();
	}

	@Test
	void readStudentByIdTest() throws Exception {
		Student student = createStudent();

		MvcResult result = this.mockMvc.perform(get("/v1/students/"+student.getId()))
				.andDo(print())
				.andReturn();

		Student responseStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(responseStudent.getId()).isEqualTo(student.getId());
	}

	@Test
	void updateStudentStatusTest() throws Exception {
		Student student = createStudent();

		boolean status = false;
		student.setWorking(status);

		MvcResult result = this.mockMvc.perform(patch("/v1/students/update/"+student.getId()))
				.andDo(print())
				.andReturn();

		Student responseStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(responseStudent.getId()).isEqualTo(student.getId());
		assertThat(responseStudent.getName()).isEqualTo(student.getName());
		assertThat(responseStudent.isWorking()).isEqualTo(false);
	}

	@Test
	void deleteStudentTest() throws Exception{
		Student student = createStudent();

		this.mockMvc.perform(delete("/v1/student/delete/"+student.getId()))
				.andDo(print())
				.andReturn();

		Student studentNull = getStudentNoResponse(student.getId());

		assertThat(studentNull).isNull();
	}

}
