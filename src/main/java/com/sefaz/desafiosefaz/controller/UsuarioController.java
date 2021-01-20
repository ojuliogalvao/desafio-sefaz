package com.sefaz.desafiosefaz.controller;

import com.sefaz.desafiosefaz.controller.DTO.UsuarioDto;
import com.sefaz.desafiosefaz.model.UsuarioEntity;
import com.sefaz.desafiosefaz.repository.UsuarioRepository;
import com.sefaz.desafiosefaz.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDto> incluirUsuario(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(usuarioDto));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

    
    @GetMapping
    public List<UsuarioEntity> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioDto, id));
    }

    @DeleteMapping("/remover-usuario/{id}")
    public ResponseEntity<UsuarioDto> removerUsuario(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
