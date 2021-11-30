package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Docente;
import br.ufpb.tutoria.infra.DocenteRepositorio;
import br.ufpb.tutoria.infra.UsuarioRepositorio;
import br.ufpb.tutoria.util.Warning;

import java.util.SortedSet;

public class DocenteControlImpl implements DocenteControl {

    public static DocenteControl docenteControl;

    private final DocenteRepositorio docenteRepositorio;

    private DocenteControlImpl(DocenteRepositorio docenteRepositorio) {
        this.docenteRepositorio = docenteRepositorio;
    }

    public boolean create(String nomeExibicao, String titulo, String departamento) {
        Docente docente = new Docente(nomeExibicao, titulo, departamento);
        return save(docente);
    }

    public Docente getByNomeExibicao(String nome){
        try {
            docenteRepositorio.findByNomeExibicao(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean save(Docente docente) {
        try {
            docenteRepositorio.gravaDocente(docente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean delete(String nome){
        try {
            docenteRepositorio.apagarDocenteByNomeExibicao(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean update(Docente docente){
        try {
            docenteRepositorio.atualizarDocente(docente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public SortedSet<Docente> getDocentes() {
        return docenteRepositorio.getDocentes();
    }

    public void setDocentes(SortedSet<Docente> docentes) {
        docenteRepositorio.setDocentes(docentes);
    }

    public void inserirDocentes(SortedSet<Docente> docentes) {
        for(Docente docente : docentes){
            create(docente.getNomeExibicao(), docente.getTitulo(), docente.getDepartamento());
        }
    }

    public static DocenteControl getInstance(DocenteRepositorio docenteRepositorio){
        if(docenteControl == null){
            docenteControl = new DocenteControlImpl(docenteRepositorio);
        }
        return docenteControl;
    }
}
