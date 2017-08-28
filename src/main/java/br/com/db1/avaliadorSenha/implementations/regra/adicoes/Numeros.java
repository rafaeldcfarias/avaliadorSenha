package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

import static java.lang.Character.isDigit;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class Numeros implements Regra<String, Long> {

    @Override
    public Long aplicaRegra(String senha) {
        if (senha.chars().mapToObj(c -> (char) c).anyMatch(c -> !isDigit(c)) &&
                senha.chars().mapToObj(c -> (char) c).anyMatch(Character::isDigit)) {
            return senha.chars().mapToObj(e -> (char) e).filter(Character::isDigit).count() * 4L;
        }
        return 0L;

    }
}
