package com.sefaz.desafiosefaz.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDto {
    private Long id;
    private Long ddd;
    private String numero;
    private String tipo;
}
