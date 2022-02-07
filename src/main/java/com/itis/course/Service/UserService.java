package com.itis.course.Service;

import com.itis.course.Enum.Role;
import com.itis.course.Model.Users;
import com.itis.course.ModelForm.UserForm;
import com.itis.course.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Users> retrieveAll() {
        return userRepository.findAll();
    }

    public void register(UserForm userForm) throws Exception {
        if(checkIfUserExist(userForm.getEmail())) throw new Exception("User already exists with this email");
        Users user = new Users();
        BeanUtils.copyProperties(userForm, user);
        encodePassword(user, userForm);
        user.setAccountVerified(true);
        user.setRoles(new ArrayList<Role>(Collections.singleton(Role.USER)));
        userRepository.save(user);
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void encodePassword( Users user, UserForm userForm){
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
    }

    public Optional<Users> retrieveOne(long id) {
        Optional<Users> optionalUser = userRepository.findById(id);
        log.info("user retrieved : {}", optionalUser);
        return optionalUser;
    }

    public String deleteOneById (long id) {
        try{
            userRepository.deleteById(id);
            return "User "+ id + " deleted";
        } catch (Exception e ) {
            return e.getMessage();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUser = userRepository.findByEmail(email);
        optionalUser.orElseThrow(() ->
            new UsernameNotFoundException("User does not exists")
        );
        log.info(optionalUser.toString());
        return optionalUser.get();
    }
}
