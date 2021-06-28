package br.com.senai.domain.service;

import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@AllArgsConstructor
@Service
public class RoleUsuariosService {

//    public ResponseEntity<Role> editar(@Valid @PathVariable Long usuario_id, @RequestBody String role_nome_role) {

//        if(!pessoaRepository.existsById(pessoaId)){
//            return ResponseEntity.notFound().build();
//        }
//        pessoa.setId(pessoaId);
//        pessoa = pessoaRepository.save(pessoa);
//
//        return ResponseEntity.ok(edicao);
//    }

}
