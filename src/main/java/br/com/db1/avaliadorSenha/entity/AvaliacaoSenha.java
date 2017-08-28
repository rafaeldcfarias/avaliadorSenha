package br.com.db1.avaliadorSenha.entity;

import java.io.Serializable;

/**
 * Created by rafaeldcfarias on 25/08/17.
 */
public class AvaliacaoSenha implements Serializable {
    private Long pontuacao;
    private String complexidade;

    public Long getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Long pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }
}
