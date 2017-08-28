package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class Sequencia3Simbolos implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        String simbolos = ")!@#$%^&*()";
        long qtdSequenciaSimbolos = 0;
        for (int i = 0; i < simbolos.length() - 3; i++) {
            String secao = simbolos.substring(i, i + 3);
            String sesaoReversa = new StringBuilder(secao).reverse().toString();
            if (senha.toLowerCase().contains(secao) || senha.toLowerCase().contains(sesaoReversa)) {
                qtdSequenciaSimbolos++;
            }
        }
        return qtdSequenciaSimbolos * 3;
    }
}
