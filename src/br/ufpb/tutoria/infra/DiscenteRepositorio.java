package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.exception.InternalErrorException;

import java.io.IOException;
import java.util.SortedSet;

public interface DiscenteRepositorio {
    boolean gravaDiscente(Discente discente) throws Exception;

    void carregarDiscentes() throws IOException, InternalErrorException;

    boolean apagarDiscenteByNomeExibicao(String nomeExibicao) throws Exception;

    Discente findByNomeExibicao(String nomeExibicao) throws Exception;

    boolean atualizarDiscente(Discente discente) throws Exception;

    SortedSet<Discente> getDiscentes();

    void setDiscentes(SortedSet<Discente> discentes);
}
