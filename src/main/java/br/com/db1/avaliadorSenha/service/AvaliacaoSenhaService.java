package br.com.db1.avaliadorSenha.service;

import br.com.db1.avaliadorSenha.abstractions.Regra;
import br.com.db1.avaliadorSenha.entity.AvaliacaoSenha;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rafaeldcfarias on 25/08/17.
 */
@Service
public class AvaliacaoSenhaService {
    public AvaliacaoSenha avaliarSenha(String senha) {
        AvaliacaoSenha avaliacaoSenha = new AvaliacaoSenha();
        avaliacaoSenha.setPontuacao(determinaPontuacao(senha));
        avaliacaoSenha.setComplexidade(determinaComplexidade(avaliacaoSenha.getPontuacao()));
        return avaliacaoSenha;
    }

    private String determinaComplexidade(Long pontuacao) {
        String complexidade = "Muito curta";
        if (pontuacao < 20) {
            complexidade = "Muito fraca";
        } else if (pontuacao >= 20 && pontuacao < 40) {
            complexidade = "Fraca";
        } else if (pontuacao >= 40 && pontuacao < 60) {
            complexidade = "Boa";
        } else if (pontuacao >= 60 && pontuacao < 80) {
            complexidade = "Forte";
        } else if (pontuacao >= 80) {
            complexidade = "Muito forte";
        }
        return complexidade;
    }

    private Long determinaPontuacao(final String senha) {
        Long adicoes = getRegrasAdicoes().stream().mapToLong(r -> r.aplicaRegra(senha)).sum();
        Long deducoes = getRegrasDeducoes().stream().mapToLong(r -> r.aplicaRegra(senha)).sum();
        long pontuacao = adicoes - deducoes;
        if (pontuacao > 100) {
            pontuacao = 100;
        } else if (pontuacao < 0) {
            pontuacao = 0;
        }
        return pontuacao;
    }

    private List<Regra<String, Long>> getRegrasAdicoes() {
        List<Regra<String, Long>> regrasAdicoes = new LinkedList<>();
        Regra<String, Long> qtdCaracteres = x -> x.length() * 4L;
        regrasAdicoes.add(qtdCaracteres);
        Regra<String, Long> letrasMaiusculas = x -> {
            if (x.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isUpperCase(c)) &&
                    x.chars().mapToObj(c -> (char) c).anyMatch(c -> Character.isUpperCase(c))) {
                return (x.length() - x.chars().mapToObj(e -> (char) e).filter(Character::isUpperCase).count()) * 2L;
            }
            return 0L;
        };
        regrasAdicoes.add(letrasMaiusculas);
        Regra<String, Long> letrasMinusculas = x -> {
            if (x.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isLowerCase(c)) &&
                    x.chars().mapToObj(c -> (char) c).anyMatch(c -> Character.isLowerCase(c))) {
                return (x.length() - x.chars().mapToObj(e -> (char) e).filter(Character::isLowerCase).count()) * 2L;
            }
            return 0L;
        };
        regrasAdicoes.add(letrasMinusculas);
        Regra<String, Long> numeros = x -> {
            if (x.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isDigit(c)) &&
                    x.chars().mapToObj(c -> (char) c).anyMatch(c -> Character.isDigit(c))) {
                return x.chars().mapToObj(e -> (char) e).filter(Character::isDigit).count() * 4L;
            }
            return 0L;
        };
        regrasAdicoes.add(numeros);
        Regra<String, Long> simbolos = x -> x.chars().mapToObj(e -> (char) e).filter(f -> !Character.isLetterOrDigit(f)).count() * 6L;
        regrasAdicoes.add(simbolos);
        Regra<String, Long> simboloOuNumeroAoMeio = x -> {
            int length = x.length();
            if (length < 3)
                return 0L;
            char[] caracteres = x.toCharArray();
            int qtdSimboloOuNumeroAoMeio = 0;
            for (int i = 1; i < length - 1; i++) {
                char caractere = caracteres[i];
                if (Character.isDigit(caractere) || (!Character.isLetterOrDigit(caractere))) {
                    qtdSimboloOuNumeroAoMeio++;
                }
            }
            return qtdSimboloOuNumeroAoMeio * 2L;
        };
        regrasAdicoes.add(simboloOuNumeroAoMeio);
        //Regra<String, Long> requesitos = x -> x.chars().mapToObj(e -> (char) e).filter(f -> !Character.isAlphabetic(f)).count() * 6;
        return regrasAdicoes;
    }

    private List<Regra<String, Long>> getRegrasDeducoes() {
        List<Regra<String, Long>> regrasDeducoes = new LinkedList<>();
        Regra<String, Long> apenasLetras = x -> {
            if (x.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isLetter(c))) {
                return 0L;
            }
            return ((long) x.length());
        };
        regrasDeducoes.add(apenasLetras);
        Regra<String, Long> apenasNumeros = x -> {
            if (x.chars().mapToObj(c -> (char) c).anyMatch(c -> !Character.isDigit(c))) {
                return 0L;
            }
            return ((long) x.length());
        };
        regrasDeducoes.add(apenasNumeros);

        Regra<String, Long> caracteresRepeticos = x -> {

            int length = x.length();
            char[] caracteres = x.toCharArray();
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
        };
        regrasDeducoes.add(caracteresRepeticos);

        Regra<String, Long> caracteresMaiusculosConcecutivos = x -> {
            char[] caracteres = x.toCharArray();
            long qtdCaracteresMaiusculosConsecutivos = 0;
            boolean isMaiusculo = false;
            for (int i = 0; i < x.length(); i++) {
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
        };
        regrasDeducoes.add(caracteresMaiusculosConcecutivos);

        Regra<String, Long> caracteresMinusculosConcecutivos = x -> {
            char[] caracteres = x.toCharArray();
            long qtdCaracteresMinusculosConsecutivos = 0;
            boolean isMinusculo = false;
            for (int i = 0; i < x.length(); i++) {
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
        };
        regrasDeducoes.add(caracteresMinusculosConcecutivos);

        Regra<String, Long> numerosConcecutivos = x -> {
            char[] caracteres = x.toCharArray();
            long qtdNumerosConsecutivos = 0;
            boolean isNumero = false;
            for (int i = 0; i < x.length(); i++) {
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
        };
        regrasDeducoes.add(numerosConcecutivos);

        Regra<String, Long> sequencia3Letras = (String senha) -> {
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
        };
        regrasDeducoes.add(sequencia3Letras);

        Regra<String, Long> sequencia3Numeros = (String senha) -> {
            String numeros = "01234567890";
            long qtdSequenciaNumeros = 0;
            for (int i = 0; i < numeros.length() - 3; i++) {
                String secao = numeros.substring(i, i + 3);
                String sesaoReversa = new StringBuilder(secao).reverse().toString();
                if (senha.toLowerCase().contains(secao) || senha.toLowerCase().contains(sesaoReversa)) {
                    qtdSequenciaNumeros++;
                }
            }
            return qtdSequenciaNumeros * 3;
        };
        regrasDeducoes.add(sequencia3Numeros);

        Regra<String, Long> sequencia3Simbolos = (String senha) -> {
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
        };
        regrasDeducoes.add(sequencia3Simbolos);


        return regrasDeducoes;
    }


}
