package br.com.senai.api.controller;


import br.com.senai.domain.model.Pessoa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class PessoaController {

    @GetMapping("/pessoas")
    public List<Pessoa> listar(){
        Pessoa pessoa1 = new Pessoa(
                1L,
                "Wellynton",
                "well@gmail.com",
                "(47)99968-8802"
        );

        Pessoa pessoa2 = new Pessoa(
                2L,
                "Wudke",
                "wudke@gmail.com",
                "(47)99167-6849"
        );

        return Arrays.asList(pessoa1, pessoa2);
    }

}
