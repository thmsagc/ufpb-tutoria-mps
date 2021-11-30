package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.business.model.Usuario;

import java.util.SortedSet;

public interface DiscenteControl {
    public boolean create(String nome, String senha, String dataNascimento);
    public Discente getByNomeExibicao(String nome);
    public boolean save(Discente discente);
    public boolean delete(String nome);
    public boolean update(Discente nomeExibicao);
    public SortedSet<Discente> getDiscentes();
    public void setDiscentes(SortedSet<Discente> discentes);
    public void inserirDiscentes (SortedSet<Discente> discentes);
}
