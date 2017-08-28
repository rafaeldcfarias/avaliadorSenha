package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class LetrasMinusculas implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        if (senha.chars().mapToObj(c -> (char) c).anyMatch(Character::isLowerCase))
            if (senha.chars().mapToObj(c -> (char) c).anyMatch(c -> !isLowerCase(c))) {
                return (senha.length() - senha.chars().mapToObj(e -> (char) e).filter(Character::isLowerCase).count()) * 2L;
            }
        return 0L;
    }
}
