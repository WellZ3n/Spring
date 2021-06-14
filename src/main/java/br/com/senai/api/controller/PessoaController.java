package br.com.senai.api.controller;


import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
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

    @GetMapping()
    public List<PessoaModel> listar(){
        return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<Pessoa> listarPorNome(@PathVariable String pessoaNome){
        return pessoaRepository.findByNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<Pessoa> listarNomeContaining(@PathVariable String nomeContaining) {
        return pessoaRepository.findByNomeContaining(nomeContaining);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> buscar(@PathVariable Long pessoaId){
        return pessoaService.procurar(pessoaId);
    }

    @PostMapping
    public Pessoa cadastrar(@Valid @RequestBody Pessoa pessoa) {
        return pessoaService.cadastrar(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> editar(@Valid @PathVariable Long pessoaId, @RequestBody Pessoa pessoa) {

        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }
        pessoa.setId(pessoaId);
        pessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoa);
    }
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@Valid @PathVariable Long pessoaId) {

        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }

}
