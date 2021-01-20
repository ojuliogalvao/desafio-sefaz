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

@Component
@Slf4j
@AllArgsConstructor
public class UsuarioDtoToUsuarioEntity {

    public UsuarioEntity convert(UsuarioDto usuarioDto) {
        return UsuarioEntity
                .builder()
                .email(usuarioDto.getEmail())
                .nome(usuarioDto.getNome())
                .senha(usuarioDto.getSenha())
                .telefone(getTelefones(usuarioDto.getTelefone()))
                .build();
    }

    private List<TelefoneEntity> getTelefones(List<TelefoneDto> telefoneDtos) {
        return Optional.ofNullable(telefoneDtos)
                .stream()
                .flatMap(Collection::stream)
                .map(this::getTelefone)
                .collect(Collectors.toUnmodifiableList());
    }

    private TelefoneEntity getTelefone(TelefoneDto telefoneDto) {
        return TelefoneEntity
                .builder()
                .ddd(telefoneDto.getDdd())
                .numero(telefoneDto.getNumero())
                .tipo(telefoneDto.getTipo())
                .build();
    }
}
