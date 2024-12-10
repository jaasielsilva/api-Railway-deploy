package com.api.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List; // Corrigido para importação correta de List

import com.api.app.model.ActionHistory;
import com.api.app.repository.ActionHistoryRepository;

@Service
public class ActionHistoryService {

    @Autowired
    private ActionHistoryRepository actionHistoryRepository;

    // Método que retorna o histórico de ações de um usuário
    public List<ActionHistory> getHistorico(Long userId) {
        return actionHistoryRepository.findByUserId(userId); // Filtra pelo ID do usuário
    }
}
