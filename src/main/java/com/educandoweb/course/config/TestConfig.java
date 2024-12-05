package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.Product;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderItemRepository;
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
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
		
		produto1.getCategories().add(category2);
		produto2.getCategories().add(category1);
		produto3.getCategories().add(category3);
		produto4.getCategories().add(category3);
		produto5.getCategories().add(category2);
		
		productRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
		User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		User user3 = new User(null, "Bob Grey", "bob@gmail.com", "9666666", "123456");
		
		Order order1 = new Order(null, Instant.parse("2024-12-05T11:04:07Z"),OrderStatus.PAID, user1);
		Order order2 = new Order(null, Instant.parse("2024-12-05T11:04:10Z"),OrderStatus.WAITING_PAYMENT, user2);
		Order order3 = new Order(null, Instant.parse("2024-12-05T11:04:22Z"),OrderStatus.WAITING_PAYMENT, user1);
		Order order4 = new Order(null, Instant.parse("2024-12-05T16:38:22Z"),OrderStatus.PAID, user3);
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		orderRepository.saveAll(Arrays.asList(order1, order2, order3, order4));		
		
		OrderItem oi1 = new OrderItem(order1, produto1, 2, produto1.getPrice());
		OrderItem oi2 = new OrderItem(order1, produto3, 1, produto3.getPrice());
		OrderItem oi3 = new OrderItem(order2, produto3, 2, produto3.getPrice());
		OrderItem oi4 = new OrderItem(order3, produto5, 2, produto5.getPrice()); 
		OrderItem oi5 = new OrderItem(order4, produto1, 2, produto4.getPrice());
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5));
	}
}
