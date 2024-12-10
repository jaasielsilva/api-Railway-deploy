package com.api.app.repository;

import com.api.app.model.ActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Long> {

    // Método para buscar por user_id
    List<ActionHistory> findByUserId(Long userId);

    // Método para buscar por tipo de ação
    List<ActionHistory> findByAction(String action);

    // Método para buscar por user_id e tipo de ação
    List<ActionHistory> findByUserIdAndAction(Long userId, String action);
}
