package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class QtdCaracteres implements Regra<String, Long> {

    @Override
    public Long aplicaRegra(String senha) {
        return senha.length() * 4L;
    }
}
