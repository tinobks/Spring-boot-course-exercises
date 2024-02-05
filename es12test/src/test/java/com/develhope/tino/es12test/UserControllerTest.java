package com.develhope.tino.es12test;

import com.develhope.tino.es12test.user.User;
import com.develhope.tino.es12test.user.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Autowired
	private ObjectMapper objectMapper;

	private MvcResult createUserRequest() throws Exception {
		User user = new User();
		user.setName("Pippo");
		user.setSurname("Baudo");
		user.setNickname("xXSnipeRXx");

		String userJSON = objectMapper.writeValueAsString(user);

		return this.mockMvc.perform(post("/v1/users/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	private User createUserWithParameter(User user) throws Exception {
		MvcResult result = createUserRequest();
        return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
	}

	private User createUserNoParameter() throws Exception {
		User user = new User();
		user.setName("Pippo");
		user.setSurname("Baudo");
		user.setNickname("xXSnipeRXx");
		return createUserWithParameter(user);
	}

	private User getUserNoResponse(Long id) throws Exception {
		MvcResult result = this.mockMvc.perform(get("/v1/users/" + id))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		if (result.getResponse().getContentLength() == 0)
			return null;

		return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
	}

	@Test
	void getAllUserTest() throws Exception {
		createUserRequest();
		MvcResult result = this.mockMvc.perform(get("/v1/users/all"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		List<User> userList = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		assertThat(userList.size()).isNotZero();
	}

	@Test
	void getUserByIdTest() throws Exception {
		User user = createUserNoParameter();
		assertThat(user.getId()).isNotNull();

		MvcResult result = this.mockMvc.perform(get("/v1/users/" + user.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		User responseUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

		assertThat(responseUser.getId()).isEqualTo(user.getId());
	}

	@Test
	void createUser() throws Exception {
		MvcResult result = createUserRequest();

		User responseUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

		assertThat(responseUser.getId()).isNotNull();
	}

	@Test
	void updateNicknameTest() throws Exception {
		User user = createUserNoParameter();
		String newNickname = "CR7";
		user.setNickname(newNickname);

		String userJSON = objectMapper.writeValueAsString(user);

		MvcResult result = this.mockMvc.perform(patch("/v1/users/update/" + user.getId())
						.contentType(MediaType.APPLICATION_JSON).content(userJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		User responseUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertThat(responseUser.getId()).isEqualTo(user.getId());
		assertThat(responseUser.getNickname()).isEqualTo(user.getNickname());
	}

	@Test
	void deleteUserTest() throws Exception {
		User user = createUserNoParameter();
		this.mockMvc.perform(delete("/v1/users/delete/" + user.getId()))
				.andDo(print())
				.andReturn();

		User userNull = getUserNoResponse(user.getId());
		assertThat(userNull).isNull();
	}
}
