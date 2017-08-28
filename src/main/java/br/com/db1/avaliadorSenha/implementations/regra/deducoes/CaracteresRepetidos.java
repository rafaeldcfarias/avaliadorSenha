package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class CaracteresRepetidos implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        int length = senha.length();
        char[] caracteres = senha.toCharArray();
        double qtdRepeticoes = 0;
        int qtdCaracteresRepetidos = 0;
        int qtdCaracteresUnicos = 0;
        for (int i = 0; i < length; i++) {
            boolean isRepetido = false;
            for (int j = 0; j < length; j++) {
                if (caracteres[i] == caracteres[j] && i != j) {
                    isRepetido = true;
                    qtdRepeticoes += Math.abs(((double) length / (j - i)));
                }
            }
            if (isRepetido) {
                qtdCaracteresRepetidos++;
                qtdCaracteresUnicos = length - qtdCaracteresRepetidos;
                if (qtdCaracteresUnicos > 0) {
                    qtdRepeticoes = Math.ceil(qtdRepeticoes / qtdCaracteresUnicos);
                } else {
                    qtdRepeticoes = Math.ceil(qtdRepeticoes);
                }
            }
        }
        return Math.round(qtdRepeticoes);
    }
}
