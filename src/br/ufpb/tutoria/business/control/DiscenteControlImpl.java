package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.infra.repositorio.DiscenteRepositorio;
import br.ufpb.tutoria.infra.repositorio.DiscenteRepositorioImpl;
import br.ufpb.tutoria.util.Warning;

import java.util.SortedSet;

public class DiscenteControlImpl implements DiscenteControl {

    public static DiscenteControl discenteControl;

    private final DiscenteRepositorio discenteRepositorio;

    private DiscenteControlImpl() {
        this.discenteRepositorio = DiscenteRepositorioImpl.getInstance();
    }

    public boolean create(String nomeExibicao, String matricula, String dataIngresso) {
        Discente discente = new Discente(nomeExibicao, matricula, new Data(dataIngresso));
        return save(discente);
    }

    public Discente getByNomeExibicao(String nome){
        try {
            discenteRepositorio.procurar(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean save(Discente discente) {
        try {
            discenteRepositorio.gravar(discente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean delete(String nome){
        try {
            discenteRepositorio.apagar(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean update(Discente discente){
        try {
            discenteRepositorio.atualizar(discente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public SortedSet<Discente> getDiscentes() {
        return discenteRepositorio.getList();
    }

    public void setDiscentes(SortedSet<Discente> discentes) {
        discenteRepositorio.setList(discentes);
    }

    public void inserirDiscentes(SortedSet<Discente> discentes) {
        for(Discente discente : discentes){
            create(discente.getNomeExibicao(), discente.getMatricula(), discente.getDataIngresso().toString());
        }
    }

    public static DiscenteControl getInstance(){
        if(discenteControl == null){
            discenteControl = new DiscenteControlImpl();
        }
        return discenteControl;
    }
}
