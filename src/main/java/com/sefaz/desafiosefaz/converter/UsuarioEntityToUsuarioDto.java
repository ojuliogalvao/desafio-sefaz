package com.sefaz.desafiosefaz.converter;

import com.sefaz.desafiosefaz.controller.DTO.TelefoneDto;
import com.sefaz.desafiosefaz.controller.DTO.UsuarioDto;
import com.sefaz.desafiosefaz.model.TelefoneEntity;
import com.sefaz.desafiosefaz.model.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class UsuarioEntityToUsuarioDto {
    public UsuarioDto convert(UsuarioEntity usuarioEntity) {
        return UsuarioDto
                .builder()
                .id(usuarioEntity.getId())
                .email(usuarioEntity.getEmail())
                .nome(usuarioEntity.getNome())
                .senha(usuarioEntity.getSenha())
                .telefone(getTelefones(usuarioEntity.getTelefone()))
                .build();
    }

    private List<TelefoneDto> getTelefones(List<TelefoneEntity> telefone) {
        return Optional.ofNullable(telefone)
                .stream()
                .flatMap(Collection::stream)
                .map(this::getTelefone)
                .collect(Collectors.toUnmodifiableList());
    }

    private TelefoneDto getTelefone(TelefoneEntity telefoneEntity) {
        return TelefoneDto
                .builder()
                .id(telefoneEntity.getId())
                .ddd(telefoneEntity.getDdd())
                .numero(telefoneEntity.getNumero())
                .tipo(telefoneEntity.getTipo())
                .build();
    }
}
