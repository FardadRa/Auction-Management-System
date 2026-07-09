package com.yorku.auction_system;

import com.yorku.auction_system.model.User;
import com.yorku.auction_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuctionSystemApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AuctionSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
