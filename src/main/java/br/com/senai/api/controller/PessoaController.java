package br.com.senai.api.controller;


import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.EntregaModel;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.EntregaInput;
import br.com.senai.api.model.input.PessoaInput;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;
    //Pronto
    @GetMapping()
    public List<PessoaModel> listar(){
        return pessoaService.listar();
    }
    //Em andamento
    @GetMapping("/nome/{pessoaNome}")
    public List<Pessoa> listarPorNome(@PathVariable String pessoaNome){
        return pessoaRepository.findByNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<Pessoa> listarNomeContaining(@PathVariable String nomeContaining) {
        return pessoaRepository.findByNomeContaining(nomeContaining);
    }
    //Pronto
    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> buscar(@PathVariable Long pessoaId){
        return pessoaService.procurar(pessoaId);
    }
    //Pronto
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaModel cadastrar(@Valid @RequestBody PessoaInput pessoaInput){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }
    //Pronto
    @PutMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaModel editar(@Valid @RequestBody PessoaInput pessoaInput, @PathVariable Long pessoaId){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        ResponseEntity<Pessoa> pessoaResponseEntity = pessoaService.editar(pessoaId, novaPessoa);

        return pessoaAssembler.toModel(pessoaResponseEntity.getBody());
    }

    //Pronto
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@Valid @PathVariable Long pessoaId) {

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }

}
