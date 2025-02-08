package com.realworld_java.repository;

import com.realworld_java.controller.user.req.UpdateUserReq;
import com.realworld_java.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void save() {
        // given
        User user = User.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();

        //when
        User savedUser = userRepository.save(user);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
    }

    @Test
    void findByEmail() {
        // given
        User user = User.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();

        //when
        User savedUser = userRepository.save(user);
        Optional<User> findByEmailUser = userRepository.findByEmail(savedUser.getEmail());

        // then
        assertThat(findByEmailUser).isPresent();
        assertThat(findByEmailUser.get().getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    void findByUsername() {
        // given
        User user = User.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();

        //when
        User savedUser = userRepository.save(user);
        Optional<User> findByUsernameUser = userRepository.findByEmail(savedUser.getEmail());

        // then
        assertThat(findByUsernameUser).isPresent();
        assertThat(findByUsernameUser.get().getUsername()).isEqualTo(savedUser.getUsername());
    }

    @Test
    void update() {
        // given
        User user = User.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();

        // when
        User savedUser = userRepository.save(user);
        UpdateUserReq updateUser = UpdateUserReq.builder()
                .email("update@example.com")
                .build();

        savedUser.updated(updateUser);
        userRepository.save(savedUser);

        Optional<User> findByEmailUser = userRepository.findByEmail(savedUser.getEmail());

        // then
        assertThat(findByEmailUser).isPresent();
        assertThat(findByEmailUser.get().getEmail()).isEqualTo(savedUser.getEmail());
    }
}