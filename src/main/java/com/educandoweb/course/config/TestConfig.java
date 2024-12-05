package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void run(String... args) throws Exception {
		
		Product produto1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product produto2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product produto3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product produto4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product produto5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		productRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
		Category category1 = new Category(null, "Electronics");
		Category category2 = new Category(null, "Books");
		Category category3 = new Category(null, "Computers");
		categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order order1 = new Order(null, Instant.parse("2024-12-05T11:04:07Z"),OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-12-05T11:04:10Z"),OrderStatus.WAITING_PAYMENT, user2);
		Order order3 = new Order(null, Instant.parse("2024-12-05T11:04:22Z"),OrderStatus.WAITING_PAYMENT, user1);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3));		
	}
}
