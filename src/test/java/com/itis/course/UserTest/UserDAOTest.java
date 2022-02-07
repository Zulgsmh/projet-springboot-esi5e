package com.itis.course.UserTest;

import com.itis.course.Repository.UserRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class UserDAOTest {

    private UserRepository userRepository;

    public UserDAOTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Given("my UserRepository class exists")
    public void my_UserRepository_class_exists() {
        userRepository.getClass();
        throw new cucumber.api.PendingException();
    }

    @When("get users count")
    public void get_users_count() {
        userRepository.findAll().size();
        throw new cucumber.api.PendingException();
    }

    @Then("users count should be positif number")
    public void users_count_should_be_positif_number() {
        assert userRepository.findAll().stream().count() > 0;
        throw new cucumber.api.PendingException();
    }


    @When("get all users")
    public void get_all_users() {
        userRepository.findAll();
        throw new cucumber.api.PendingException();
    }

    @Then("users should be list of size zero or plus")
    public void users_should_be_list_of_size_zero_or_plus() {
        assert userRepository.findAll().size() == 0 || userRepository.findAll().size() > 0;
        throw new cucumber.api.PendingException();
    }


}
