package br.ufpb.tutoria.infra.repositorio;

import br.ufpb.tutoria.exception.InternalErrorException;

import java.io.IOException;
import java.util.SortedSet;

public interface RepositorioAbstrato <T>{
    boolean gravar(T t) throws Exception;

    void gravarArquivo(T t) throws Exception;

    void carregar() throws IOException, InternalErrorException;

    boolean apagar(String chave) throws Exception;

    T procurar(String chave) throws Exception;

    void atualizar(T t) throws Exception;

    SortedSet<T> getList();

    void setList(SortedSet<T> list);

}
