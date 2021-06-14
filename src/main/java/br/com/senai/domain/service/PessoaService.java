package br.com.senai.domain.service;


import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.EntregaModel;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
        boolean emailValidation = pessoaRepository.findByEmail(pessoa.getEmail()).isPresent();

        if (emailValidation){
            throw new NegocioException("Ja existe uma pessoa com esse e-mail cadastrado");
        }

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId){
    pessoaRepository.deleteById(pessoaId);
    }

    public List<PessoaModel> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    public Pessoa buscar(Long pessoaId){
       return pessoaRepository.findById(pessoaId).orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<PessoaModel> procurar(Long pessoaId){
        return pessoaRepository.findById(pessoaId).map(entrega ->
            ResponseEntity.ok(pessoaAssembler.toModel(entrega))
        ).orElse(ResponseEntity.notFound().build());
    }
}
