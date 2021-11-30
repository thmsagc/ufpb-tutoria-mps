package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Docente;

import java.util.SortedSet;

public interface DocenteControl {
    public boolean create(String nome, String senha, String dataNascimento);
    public Docente getByNomeExibicao(String nome);
    public boolean save(Docente docente);
    public boolean delete(String nome);
    public boolean update(Docente nomeExibicao);
    public SortedSet<Docente> getDocentes();
    public void setDocentes(SortedSet<Docente> docentes);
    public void inserirDocentes (SortedSet<Docente> docentes);
}
