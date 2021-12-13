package br.ufpb.tutoria.infra.repositorio;

import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.exception.InternalErrorException;

import java.io.IOException;
import java.util.SortedSet;

public class RepositorioAbstratoImpl <T> implements RepositorioAbstrato<T>{


    @Override
    public boolean gravar(T t) throws Exception {
        return false;
    }

    @Override
    public void gravarArquivo(T t) throws Exception {

    }

    @Override
    public void carregar() throws IOException, InternalErrorException {

    }

    @Override
    public boolean apagar(String chave) throws Exception {
        return false;
    }

    @Override
    public T procurar(String chave) throws Exception {
        return null;
    }

    @Override
    public void atualizar(T t) throws Exception {

    }

    @Override
    public SortedSet<T> getList() {
        return null;
    }

    @Override
    public void setList(SortedSet<T> list) {

    }
}
