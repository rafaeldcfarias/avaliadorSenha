package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class NumerosConcecutivos implements Regra<String, Long> {

    @Override
    public Long aplicaRegra(String senha) {
        char[] caracteres = senha.toCharArray();
        long qtdNumerosConsecutivos = 0;
        boolean isNumero = false;
        for (int i = 0; i < senha.length(); i++) {
            if (Character.isDigit(caracteres[i])) {
                if (isNumero) {
                    qtdNumerosConsecutivos++;
                } else {
                    isNumero = true;
                }
            } else {
                isNumero = false;
            }
        }
        return qtdNumerosConsecutivos * 2;
    }
}
