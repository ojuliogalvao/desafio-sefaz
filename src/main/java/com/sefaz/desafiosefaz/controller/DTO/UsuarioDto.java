package com.sefaz.desafiosefaz.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nome ;
    private String email;
    private String senha;
    private List<TelefoneDto> telefone;
	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}
