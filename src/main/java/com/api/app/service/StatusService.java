package com.api.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.app.repository.UserRepository;
import com.api.app.model.User;

@Service
public class StatusService {

    @Autowired
    private UserRepository userRepository;

     // Método para buscar o status do usuário pelo nome de usuário
    public String getStatusByUsername(String username) {
        // Buscar o usuário pelo nome de usuário
        User user = userRepository.findByUsername(username);

        // Se o usuário for encontrado, retornar o status
        if (user != null) {
            return user.getStatus();
        }

        // Se o usuário não for encontrado, retornar uma mensagem padrão
        return "Status não encontrado";
    }
}
