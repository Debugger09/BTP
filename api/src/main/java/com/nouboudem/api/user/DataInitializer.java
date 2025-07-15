package com.nouboudem.api.user;

import com.nouboudem.api.user.entity.Role;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDefaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User user = userRepository.findByEmail("admin@example.com").orElse(null);
            if (user == null) {
                user = User.builder()
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .statut(true)
                        .nom("Admin")
                        .prenom("Super")
                        .build();
                userRepository.save(user);
                System.out.println("Utilisateur admin créé par défaut !");
                System.out.println("Nom : " + user.getNom());
                System.out.println("Email : " + user.getEmail());
                System.out.println("Mot de passe : admin123");
            } else {
                user.setStatut(true);
                userRepository.save(user);
                System.out.println("Statut de l'utilisateur admin mis à jour à actif.");
            }
        };
    }
} 