package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class ApenasLetras implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        if (senha.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isLetter(c))) {
            return 0L;
        }
        return ((long) senha.length());
    }
}
