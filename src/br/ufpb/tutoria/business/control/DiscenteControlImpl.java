package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.infra.DiscenteRepositorio;
import br.ufpb.tutoria.util.Warning;

import java.util.SortedSet;

public class DiscenteControlImpl implements DiscenteControl {

    public static DiscenteControl discenteControl;

    private final DiscenteRepositorio discenteRepositorio;

    private DiscenteControlImpl(DiscenteRepositorio discenteRepositorio) {
        this.discenteRepositorio = discenteRepositorio;
    }

    public boolean create(String nomeExibicao, String matricula, String dataIngresso) {
        Discente discente = new Discente(nomeExibicao, matricula, new Data(dataIngresso));
        return save(discente);
    }

    public Discente getByNomeExibicao(String nome){
        try {
            discenteRepositorio.findByNomeExibicao(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean save(Discente discente) {
        try {
            discenteRepositorio.gravaDiscente(discente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean delete(String nome){
        try {
            discenteRepositorio.apagarDiscenteByNomeExibicao(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean update(Discente discente){
        try {
            discenteRepositorio.atualizarDiscente(discente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public SortedSet<Discente> getDiscentes() {
        return discenteRepositorio.getDiscentes();
    }

    public void setDiscentes(SortedSet<Discente> discentes) {
        discenteRepositorio.setDiscentes(discentes);
    }

    public void inserirDiscentes(SortedSet<Discente> discentes) {
        for(Discente discente : discentes){
            create(discente.getNomeExibicao(), discente.getMatricula(), discente.getDataIngresso().toString());
        }
    }

    public static DiscenteControl getInstance(DiscenteRepositorio discenteRepositorio){
        if(discenteControl == null){
            discenteControl = new DiscenteControlImpl(discenteRepositorio);
        }
        return discenteControl;
    }
}
