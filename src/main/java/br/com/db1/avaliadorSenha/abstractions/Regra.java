package br.com.db1.avaliadorSenha.abstractions;

/**
 * Created by rafaeldcfarias on 26/08/17.
 */
@FunctionalInterface
public interface Regra<T, R> {
    R aplicaRegra(T t);
}
