package com.nouboudem.user_service.service;

import com.nouboudem.user_service.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user); // Créer un nouvel utilisateur
    List<User> getAllUsers(); // Récupérer la liste de tous les utilisateurs
    Optional<User> getUserById(Long id); // Récupérer un utilisateur par son ID
    Optional<User> getUserByEmail(String email); // Récupérer un utilisateur par son email
    User updateUser(Long id, User user); // Mettre à jour un utilisateur
    void deleteUser(Long id); // Supprimer un utilisateur
} 