package com.itis.course.Service;

import com.itis.course.Enum.Role;
import com.itis.course.Model.Publication;
import com.itis.course.Model.Users;
import com.itis.course.ModelForm.UserForm;
import com.itis.course.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UserServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Captor
        private ArgumentCaptor<Users> customerArgumentCaptor;

        private UserService underTest;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.initMocks(this);
                underTest = new UserService(userRepository, passwordEncoder);
        }

        List<Users> generateUserList() {
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

                Users newUser2 = new Users("test2",
                        "test2",
                        "test2@mail.com",
                        "password2", roles,
                        true, publications);

                return new ArrayList<>(List.of(newUser, newUser2));
        }

        @Test
        void itShouldSaveUserWhenEmailIsNotTaken() throws Exception {
                //Given
                String mail = "test@mail.com";
                UserForm userForm = new UserForm("test", "test", mail, "password");
                //no user with this email
                given(userRepository.findByEmail(mail)).willReturn(Optional.empty());
                //When
                underTest.register(userForm);

                //Then
                assertThat(userRepository.findByEmail(mail).isPresent()).isTrue();
        }

        @Test
        void itShouldRetrieveAllTheUsers() {
                //Given
                List<Users> usersList = generateUserList();
                userRepository.saveAll(usersList);
                //When
                List<Users> usersListRetrieved = underTest.retrieveAll();
                //Then
                assertThat(usersList.size()).isGreaterThan(0);
                assertThat(usersList.size()).isEqualTo(2);
        }

        @Test
        void itShouldThrowExceptionWhenRegisterAndUserAlreadyExists() {
                //Given
                String mail = "test@mail.com";
                List<Role> roles = new ArrayList<>();
                roles.add(Role.USER);
                roles.add(Role.ADMIN);
                roles.add(Role.SPECIALIST);

                List<Publication> publications = new ArrayList<>();

                Users newUser = new Users("test",
                        "test",
                        mail,
                        "password",
                        roles,
                        true,
                        publications);

                userRepository.save(newUser);
                //When
                //Then
                assertThatThrownBy(() -> underTest
                        .register(new UserForm("test", "test", mail, "password")))
                        .isInstanceOf(Exception.class)
                        .hasMessageContaining("User already exists with this email");
                //Then
                then(userRepository).shouldHaveNoInteractions();
        }

        @Test
        void itShouldDeleteUserById() {
                //Given
                String mail = "test@mail.com";
                List<Role> roles = new ArrayList<>();
                roles.add(Role.USER);
                roles.add(Role.ADMIN);
                roles.add(Role.SPECIALIST);

                List<Publication> publications = new ArrayList<>();

                Users newUser = new Users("test",
                        "test",
                        mail,
                        "password",
                        roles,
                        true,
                        publications);

                userRepository.save(newUser);
                Optional<Users> optionalUsers = userRepository.findByEmail(newUser.getEmail());
                //Then
                assertThat(optionalUsers.isPresent()).isTrue();
                underTest.deleteOneById(optionalUsers.get().getId());
                assertThat(userRepository.findByEmail(mail).isEmpty()).isTrue();
                then(userRepository).shouldHaveNoInteractions();
        }
}