package com.itis.course.Repository;

import com.itis.course.Enum.Role;
import com.itis.course.Model.Publication;
import com.itis.course.Model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    @Test
    void itShouldSaveUser() {
        //Given
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        roles.add(Role.SPECIALIST);

        List<Publication> publications = new ArrayList<>();

        Users newUser = new Users("test",
                "test",
                "test@mail.com",
                "password", roles,
                true, publications);
        //When
        underTest.save(newUser);
        Optional<Users> res = underTest.findByEmail(newUser.getEmail());
        //Then
        assertThat(res.isPresent()).isEqualTo(true);
    }

    @Test
    void itShouldFindUserByMail() {
        //Given
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        roles.add(Role.SPECIALIST);

        List<Publication> publications = new ArrayList<>();

        Users newUser = new Users("test",
                "test",
                "test@mail.com",
                "password", roles,
                true, publications);
        //When
        underTest.save(newUser);
        Optional<Users> res = underTest.findByEmail(newUser.getEmail());
        //Then
        assertThat(res.isPresent()).isEqualTo(true);
        assertThat(res.get()).isEqualToComparingFieldByField(newUser);
    }
}