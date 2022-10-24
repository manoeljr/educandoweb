package com.educandoweb.configurations;

import com.educandoweb.entities.*;
import com.educandoweb.entities.enums.OrderStatus;
import com.educandoweb.repositories.*;
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

    @Autowired
    private OrderItemRepository orderItemRepository;

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

        productOne.getCategories().add(categoryTwo);
        productTwo.getCategories().add(categoryOne);
        productTwo.getCategories().add(categoryThree);
        productThree.getCategories().add(categoryThree);
        productFour.getCategories().add(categoryThree);
        productFive.getCategories().add(categoryTwo);

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

        OrderItem orderItemOne = new OrderItem(orderOne, productOne, 2, productOne.getPrice());
        OrderItem orderItemTwo = new OrderItem(orderOne, productThree, 1, productFour.getPrice());
        OrderItem orderItemThree = new OrderItem(orderTwo, productThree, 2, productOne.getPrice());
        OrderItem orderItemFour = new OrderItem(orderThree, productFive, 2, productFive.getPrice());

        orderItemRepository.saveAll(Arrays.asList(orderItemOne, orderItemTwo, orderItemThree, orderItemFour));
    }
}
