package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class CaracteresMinusculosConcecutivos implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        char[] caracteres = senha.toCharArray();
        long qtdCaracteresMinusculosConsecutivos = 0;
        boolean isMinusculo = false;
        for (int i = 0; i < senha.length(); i++) {
            if (Character.isLowerCase(caracteres[i])) {
                if (isMinusculo) {
                    qtdCaracteresMinusculosConsecutivos++;
                } else {
                    isMinusculo = true;
                }
            } else {
                isMinusculo = false;
            }
        }
        return qtdCaracteresMinusculosConsecutivos * 2;
    }
}
