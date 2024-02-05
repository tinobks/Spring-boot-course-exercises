package com.develhope.tino.es12test;

import com.develhope.tino.es12test.user.User;
import com.develhope.tino.es12test.user.UserRepository;
import com.develhope.tino.es12test.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc

public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void checkUserNicknameTest() throws Exception {
        User user = new User();
        user.setName("Pippo");
        user.setSurname("Baudo");
        user.setNickname("xXSnipeRXx");

        User userFromDB = userRepository.save(user);
        assertThat(userFromDB.getId()).isNotNull();

        User userFromService = userService.updateNickname(user.getId(), user);
        assertThat(userFromService.getId()).isNotNull();
        assertThat(userFromService.getNickname()).isNotNull();

        User userById = userRepository.findById(userFromDB.getId()).get();
        assertThat(userById).isNotNull();
        assertThat(userById.getId()).isNotNull();
        assertThat(userById.getId()).isEqualTo(userFromDB.getId());
        assertThat(userById.getNickname()).isNotNull();
    }
}
