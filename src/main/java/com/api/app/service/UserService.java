package com.api.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.app.model.User;
import com.api.app.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Método para salvar o usuário
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Método para obter o total de pessoas cadastradas
    public long getTotalPessoasCadastradas() {
        return userRepository.count(); // Retorna o total de usuários cadastrados
    }

    // Método para obter a última atividade
    public LocalDateTime getLastActivity() {
        User lastUser = userRepository.findTopByOrderByCreatedAtDesc();
        if (lastUser != null) {
            return lastUser.getCreatedAt(); // Retorna o campo createdAt do último usuário
        }
        return null; // Caso não exista usuários, retorna null
    }

    // Método para buscar novos usuários cadastrados após uma data específica (uma semana atrás)
    public List<User> getNewUsersSince(LocalDateTime date) {
        return userRepository.findByCreatedAtAfter(date); // Retorna lista de usuários após a data informada
    }
    // Método para buscar o usuário pelo nome
    public User buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para atualizar o último acesso
    public void updateLastAccess(Long userId) {
        userRepository.updateLastAccess(userId);  // Chama o método no repositório para atualizar no banco de dados
    }

    // Método para obter o último acesso de um usuário
    public LocalDateTime getLastAccess(Long userId) {
        User user = userRepository.findById(userId).orElse(null);  // Procura o usuário no banco de dados
        if (user != null) {
            return user.getUltimoAcesso();  // Aqui usamos 'getUltimoAcesso' para acessar o campo correto
        }
        return null;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
