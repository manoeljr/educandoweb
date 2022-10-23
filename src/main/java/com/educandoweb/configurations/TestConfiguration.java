package com.educandoweb.configurations;

import com.educandoweb.entities.Order;
import com.educandoweb.entities.User;
import com.educandoweb.repositories.OrderRepository;
import com.educandoweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;


@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User userOne = new User(
                null, "Maria Bronw", "maria@gmail.com", "98888888888", "123456"
        );
        User userTwo = new User(
                null, "Alex Green", "alex@gmail.com", "0000000000", "123456"
        );

        Order orderOne = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), userOne);
        Order orderTwo = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), userTwo);
        Order orderThree = new Order(null, Instant.parse("2019-06-22T15:21:22Z"), userOne);

        userRepository.saveAll(Arrays.asList(userOne, userTwo));
        orderRepository.saveAll(Arrays.asList(orderOne, orderTwo, orderThree));
    }
}
