package com.educandoweb.configurations;

import com.educandoweb.entities.User;
import com.educandoweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User userOne = new User(
                null, "Maria Bronw", "maria@gmail.com", "98888888888", "123456"
        );
        User userTwo = new User(
                null, "Alex Green", "alex@gmail.com", "0000000000", "123456"
        );

        userRepository.saveAll(Arrays.asList(userOne, userTwo));
    }
}
