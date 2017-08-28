package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class Requesitos implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        int length = senha.length();
        if (length < 8) {
            return 0L;
        }
        boolean minusculo = false;
        boolean maiusculo = false;
        boolean numero = false;
        boolean simbolo = false;
        long bonusPorRequesitosMinimos = 10L;

        char[] caracteres = senha.toCharArray();

        for (int i = 0; i < length; i++) {
            char caractere = caracteres[i];
            if (Character.isLowerCase(caractere)) {
                minusculo = true;
            }
            if (Character.isUpperCase(caractere)) {
                maiusculo = true;
            }
            if (Character.isDigit(caractere)) {
                numero = true;
            }
            if (Character.isDigit(caractere) || (!Character.isLetterOrDigit(caractere))) {
                simbolo = true;
            }
        }
        if (minusculo && maiusculo && numero && simbolo) {
            return bonusPorRequesitosMinimos;
        }
        return 0L;
    }
}
