package br.com.db1.avaliadorSenha.service;

import br.com.db1.avaliadorSenha.abstractions.Regra;
import br.com.db1.avaliadorSenha.entity.AvaliacaoSenha;
import br.com.db1.avaliadorSenha.implementations.regra.adicoes.*;
import br.com.db1.avaliadorSenha.implementations.regra.deducoes.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rafaeldcfarias on 25/08/17.
 */
@Service
public class AvaliacaoSenhaService {

    public AvaliacaoSenha avaliarSenha(String senha) {
        AvaliacaoSenha avaliacaoSenha = new AvaliacaoSenha();
        avaliacaoSenha.setPontuacao(determinaPontuacao(senha));
        avaliacaoSenha.setComplexidade(determinaComplexidade(avaliacaoSenha.getPontuacao()));
        return avaliacaoSenha;
    }

    private String determinaComplexidade(Long pontuacao) {
        String complexidade = "Muito curta";
        if (pontuacao < 20) {
            complexidade = "Muito fraca";
        } else if (pontuacao >= 20 && pontuacao < 40) {
            complexidade = "Fraca";
        } else if (pontuacao >= 40 && pontuacao < 60) {
            complexidade = "Boa";
        } else if (pontuacao >= 60 && pontuacao < 80) {
            complexidade = "Forte";
        } else if (pontuacao >= 80) {
            complexidade = "Muito forte";
        }
        return complexidade;
    }

    private Long determinaPontuacao(final String senha) {
        Long adicoes = getRegrasAdicoes().stream().mapToLong(r -> r.aplicaRegra(senha)).sum();
        Long deducoes = getRegrasDeducoes().stream().mapToLong(r -> r.aplicaRegra(senha)).sum();
        long pontuacao = adicoes - deducoes;
        if (pontuacao > 100) {
            pontuacao = 100;
        } else if (pontuacao < 0) {
            pontuacao = 0;
        }
        return pontuacao;
    }

    private List<Regra<String, Long>> getRegrasAdicoes() {
        List<Regra<String, Long>> regrasAdicoes = new LinkedList<>();
        regrasAdicoes.add(new QtdCaracteres());
        regrasAdicoes.add(new LetrasMaiusculas());
        regrasAdicoes.add(new LetrasMinusculas());
        regrasAdicoes.add(new Numeros());
        regrasAdicoes.add(new Simbolos());
        regrasAdicoes.add(new SimboloOuNumeroAoMeio());
        regrasAdicoes.add(new Requesitos());
        return regrasAdicoes;
    }

    private List<Regra<String, Long>> getRegrasDeducoes() {
        List<Regra<String, Long>> regrasDeducoes = new LinkedList<>();
        regrasDeducoes.add(new ApenasLetras());
        regrasDeducoes.add(new ApenasNumeros());
        regrasDeducoes.add(new CaracteresRepetidos());
        regrasDeducoes.add(new CaracteresMaiusculosConcecutivos());
        regrasDeducoes.add(new CaracteresMinusculosConcecutivos());
        regrasDeducoes.add(new NumerosConcecutivos());
        regrasDeducoes.add(new Sequencia3Letras());
        regrasDeducoes.add(new Sequencia3Numeros());
        regrasDeducoes.add(new Sequencia3Simbolos());
        return regrasDeducoes;
    }


}
