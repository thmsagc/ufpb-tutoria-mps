package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.business.model.Docente;
import br.ufpb.tutoria.exception.InternalErrorException;

import java.io.IOException;
import java.util.SortedSet;

public interface DocenteRepositorio {
    boolean gravaDocente(Docente docente) throws Exception;

    void carregarDocentes() throws IOException, InternalErrorException;

    boolean apagarDocenteByNomeExibicao(String nomeExibicao) throws Exception;

    Docente findByNomeExibicao(String nomeExibicao) throws Exception;

    boolean atualizarDocente(Docente docente) throws Exception;

    SortedSet<Docente> getDocentes();

    void setDocentes(SortedSet<Docente> docentes);
}
