package br.com.db1.avaliadorSenha.implementations.regra.adicoes;

import br.com.db1.avaliadorSenha.abstractions.Regra;

/**
 * Created by rafael.farias on 28/08/17.
 */
public class SimboloOuNumeroAoMeio implements Regra<String, Long> {
    @Override
    public Long aplicaRegra(String senha) {
        if (senha.length() < 3)
            return 0L;
        char[] caracteres = senha.toCharArray();
        int qtdSimboloOuNumeroAoMeio = 0;
        for (int i = 1; i < senha.length() - 1; i++) {
            char caractere = caracteres[i];
            if (Character.isDigit(caractere) || (!Character.isLetterOrDigit(caractere))) {
                qtdSimboloOuNumeroAoMeio++;
            }
        }
        return qtdSimboloOuNumeroAoMeio * 2L;
    }
}
