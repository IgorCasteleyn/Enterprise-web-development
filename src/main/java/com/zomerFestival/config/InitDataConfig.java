package com.zomerFestival.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domein.*;
import repository.*;

@Component
public class InitDataConfig implements CommandLineRunner {

        private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        @Autowired
        private UserRepository userRepository;

        @Override
        public void run(String... args) {

                // Gebruikers
                User user1 = new User(1, "user1", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_USER"));
                User user2 = new User(2, "user2", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_USER"));

                User admin = new User(3, "admin", passwordEncoder.encode("1234"), true,
                                Collections.singletonList("ROLE_ADMIN"));

                userRepository.save(user1);
                userRepository.save(user2);
                userRepository.save(admin);

        }
}
