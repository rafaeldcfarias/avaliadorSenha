package br.com.db1.avaliadorSenha.implementations.regra.deducoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class Sequencia3Letras implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        String letras = "abcdefghijklmnopqrstuvwxyz";
        long qtdSequenciaLetras = 0;
        for (int i = 0; i < letras.length() - 3; i++) {
            String secao = letras.substring(i, i + 3);
            String sesaoReversa = new StringBuilder(secao).reverse().toString();
            if (senha.toLowerCase().contains(secao) || senha.toLowerCase().contains(sesaoReversa)) {
                qtdSequenciaLetras++;
            }
        }
        return qtdSequenciaLetras * 3;
    }
}
