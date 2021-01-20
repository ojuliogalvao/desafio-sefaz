package com.sefaz.desafiosefaz.repository;

import com.sefaz.desafiosefaz.model.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    List<UsuarioEntity> findByNome(String nome);
}
