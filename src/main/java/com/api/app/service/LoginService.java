package com.api.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.app.repository.UserRepository;
import com.api.app.model.User;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                // Se a senha estiver correta, atualizar o último acesso
                userRepository.updateLastAccess(user.getId());                
                return true; // Se a senha for correta
            } else {
                System.out.println("Senha incorreta para o usuário: " + username);
            }
        } else {
            System.out.println("Usuário não encontrado: " + username);
        }
        return false; // Se o usuário ou senha estiverem incorretos
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username); // Retorna o usuário encontrado pelo nome de usuário
    }
}
