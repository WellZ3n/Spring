package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nomePessoa;
    private String telefonePessoa;
    private UsuarioDTO usuario;

}
