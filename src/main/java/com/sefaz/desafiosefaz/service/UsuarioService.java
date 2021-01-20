package com.sefaz.desafiosefaz.service;

import com.sefaz.desafiosefaz.controller.DTO.UsuarioDto;

import javassist.NotFoundException;

public interface UsuarioService{
    UsuarioDto cadastrarUsuario(UsuarioDto usuarioDto);

    UsuarioDto buscarUsuario(Long id);

    UsuarioDto atualizarUsuario(UsuarioDto usuarioDto,Long id) throws NotFoundException;

    void removerUsuario (Long id) throws NotFoundException;
}
