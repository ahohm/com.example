package com.example.demo;

import com.example.demo.repository.RoleRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserService userService, RoleRepository roleRepository, PasswordEncoder encoder){
        return args ->{
            Role role = roleRepository.findByName("ROLE_ADMIN");

            if (role == null) {
                role = new Role();
                role.setName("ROLE_ADMIN");
            }
//            userRepository.save(User.builder().email("adm54in@admin.te").username("admin21").password(encoder.encode("123456")).roles(Set.of(roleRepository.getReferenceById(role.getRoleId()))).build());
//            userRepository.save(User.builder().email("adm69in1@admin.te").username("admin81").password(encoder.encode("123456")).roles(Set.of(roleRepository.getReferenceById(role.getRoleId()))).build());
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(encoder.encode("admin123"));
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(role);
            adminUser.setRoles(adminRoles);

            userService.saveUserWithNewRoles(adminUser, Set.of(role));

        };

    }
}
