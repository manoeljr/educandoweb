package com.educandoweb.configurations;

import com.educandoweb.entities.Category;
import com.educandoweb.entities.Order;
import com.educandoweb.entities.Product;
import com.educandoweb.entities.User;
import com.educandoweb.entities.enums.OrderStatus;
import com.educandoweb.repositories.CategoryRepository;
import com.educandoweb.repositories.OrderRepository;
import com.educandoweb.repositories.ProductRepository;
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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category categoryOne = new Category(null, "Electronics");
        Category categoryTwo = new Category(null, "Books");
        Category categoryThree = new Category(null, "Computers");

        Product productOne = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet", 90.5, "");
        Product productTwo = new Product(null, "Smart TV", "Nulla eu imperdiet purus", 2190.0, "");
        Product productThree = new Product(null, "Macbook Pro", "Nam eleifend maximus", 1250.0, "");
        Product productFour = new Product(null, "PC Gamer", "Donec aliquet odio ac", 1200.0, "");
        Product productFive = new Product(null, "Rails for Dummies", "Cras fringilla convallis", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(categoryOne, categoryTwo, categoryThree));
        productRepository.saveAll(Arrays.asList(productOne, productTwo, productThree, productFour, productFive));

        User userOne = new User(
                null, "Maria Bronw", "maria@gmail.com", "98888888888", "123456"
        );
        User userTwo = new User(
                null, "Alex Green", "alex@gmail.com", "0000000000", "123456"
        );

        Order orderOne = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, userOne);
        Order orderTwo = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, userTwo);
        Order orderThree = new Order(null, Instant.parse("2019-06-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, userOne);

        userRepository.saveAll(Arrays.asList(userOne, userTwo));
        orderRepository.saveAll(Arrays.asList(orderOne, orderTwo, orderThree));
    }
}
