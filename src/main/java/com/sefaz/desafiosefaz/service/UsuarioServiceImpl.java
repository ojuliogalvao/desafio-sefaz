package com.sefaz.desafiosefaz.service;

import com.sefaz.desafiosefaz.controller.DTO.UsuarioDto;
import com.sefaz.desafiosefaz.converter.UsuarioDtoToUsuarioEntity;
import com.sefaz.desafiosefaz.converter.UsuarioEntityToUsuarioDto;
import com.sefaz.desafiosefaz.model.UsuarioEntity;
import com.sefaz.desafiosefaz.repository.UsuarioRepository;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    private final UsuarioDtoToUsuarioEntity usuarioDtoToUsuarioEntity = new UsuarioDtoToUsuarioEntity();
    private final UsuarioEntityToUsuarioDto usuarioEntityToUsuarioDto = new UsuarioEntityToUsuarioDto();

    @Override
    public UsuarioDto cadastrarUsuario(UsuarioDto usuarioDto) {
        //TODO COLOCAR VALIDAÇAO PARA VER SE JA NAO EXISTE O USUARIO

        final var usuarioConverted = usuarioDtoToUsuarioEntity.convert(usuarioDto);
        usuarioRepository.save(usuarioConverted);
        return usuarioEntityToUsuarioDto.convert(usuarioConverted);
    }

    @Override
    public UsuarioDto buscarUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY); //TODO COLOCAR UMA VALIDAÇÃO DESCENTE AQUI, STATUS CODE 422 FAZ SENTIDO NESSE CASO(EU ACHO)
        }

        final var response = usuarioRepository.findById(id);
        return Optional.ofNullable(response)
                .flatMap(usuarioEntity -> usuarioEntity)
                .map(usuarioEntityToUsuarioDto::convert)
                .orElse(UsuarioDto.builder().build());
    }

    @Override
   // @Transactional
    public UsuarioDto atualizarUsuario(UsuarioDto usuarioDto, Long id) throws NotFoundException {
        Optional<UsuarioEntity> usuarioOptinal = usuarioRepository.findById(id);

        if (usuarioOptinal.isPresent()) {
            final var usuarioConverted = usuarioDtoToUsuarioEntity.convert(usuarioDto);

            final var usuarioEntity = usuarioRepository.save(usuarioConverted);
            return usuarioEntityToUsuarioDto.convert(usuarioEntity);
        }
        //TODO COLOCAR A VALIDAÇÃO DAS EXCEPTIONS
        throw new NotFoundException("Nao Existe");
    }

    @Override
    public void removerUsuario(Long id) throws NotFoundException {

        final var usuarioEntity = usuarioRepository.findById(id);
        if (usuarioEntity.isEmpty()) {

           
            throw new NotFoundException("Nao Existe");
            
        }

        usuarioRepository.deleteById(id);

    }
}
