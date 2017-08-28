package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class CaracteresMaiusculosConcecutivos implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        char[] caracteres = senha.toCharArray();
        long qtdCaracteresMaiusculosConsecutivos = 0;
        boolean isMaiusculo = false;
        for (int i = 0; i < senha.length(); i++) {
            if (Character.isUpperCase(caracteres[i])) {
                if (isMaiusculo) {
                    qtdCaracteresMaiusculosConsecutivos++;
                } else {
                    isMaiusculo = true;
                }
            } else {
                isMaiusculo = false;
            }
        }
        return qtdCaracteresMaiusculosConsecutivos * 2;
    }
}
