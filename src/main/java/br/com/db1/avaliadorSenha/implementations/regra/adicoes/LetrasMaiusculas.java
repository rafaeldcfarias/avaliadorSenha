package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

import static java.lang.Character.isUpperCase;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class LetrasMaiusculas implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        if (senha.chars().mapToObj(c -> (char) c).anyMatch(Character::isUpperCase))
            if (senha.chars().mapToObj(c -> (char) c).anyMatch(c -> !isUpperCase(c))) {
                return (senha.length() - senha.chars().mapToObj(e -> (char) e).filter(Character::isUpperCase).count()) * 2L;
            }
        return 0L;
    }
}
