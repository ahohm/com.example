package com.example.demo;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@SpringBootApplication
@EnableWebMvc
public class DemoApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public List<?> init(){
		List<User> initialUserList = List.of(
				User.builder().email("admin@admin.te").username("admin").password(encoder.encode("123456")).build(),
				User.builder().email("admin1@admin.te").username("admin1").password(encoder.encode("123456")).build()
		);

		return userRepository.saveAll(initialUserList);
	}

}
