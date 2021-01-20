package com.sefaz.desafiosefaz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Builder

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome ;
    private String email;
    private String senha;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "telefone_id", insertable = true)
    private List<TelefoneEntity> telefone;
}
