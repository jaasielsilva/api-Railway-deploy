package com.api.app.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produtos", uniqueConstraints = {
    @UniqueConstraint(columnNames = "codigoBarras")
})
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @Column(nullable = false, unique = true)
    private String codigoBarras;
}
