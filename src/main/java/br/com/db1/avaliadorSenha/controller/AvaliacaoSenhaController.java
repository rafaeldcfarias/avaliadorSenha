package br.com.db1.avaliadorSenha.controller;

import br.com.db1.avaliadorSenha.entity.AvaliacaoSenha;
import br.com.db1.avaliadorSenha.service.AvaliacaoSenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created by rafaeldcfarias on 25/08/17.
 */

@RestController
@RequestMapping("/avalia_senha")
public class AvaliacaoSenhaController {

    private AvaliacaoSenhaService avaliacaoSenhaService;

    @PostMapping("/")
    public ResponseEntity<AvaliacaoSenha> avaliarSenha(@RequestParam(value = "senha") String senha) {
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        return new ResponseEntity<AvaliacaoSenha>(avaliacaoSenha, OK);
    }
}
