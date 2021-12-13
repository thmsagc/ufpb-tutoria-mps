package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Docente;
import br.ufpb.tutoria.infra.repositorio.DocenteRepositorio;
import br.ufpb.tutoria.infra.repositorio.DocenteRepositorioImpl;
import br.ufpb.tutoria.util.Warning;

import java.util.SortedSet;

public class DocenteControlImpl implements DocenteControl {

    public static DocenteControl docenteControl;

    private final DocenteRepositorio docenteRepositorio;

    private DocenteControlImpl() {
        this.docenteRepositorio = DocenteRepositorioImpl.getInstance();
    }

    public boolean create(String nomeExibicao, String titulo, String departamento) {
        Docente docente = new Docente(nomeExibicao, titulo, departamento);
        return save(docente);
    }

    public Docente getByNomeExibicao(String nome){
        try {
            docenteRepositorio.procurar(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean save(Docente docente) {
        try {
            docenteRepositorio.gravar(docente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean delete(String nome){
        try {
            docenteRepositorio.procurar(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean update(Docente docente){
        try {
            docenteRepositorio.atualizar(docente);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public SortedSet<Docente> getDocentes() {
        return docenteRepositorio.getList();
    }

    public void setDocentes(SortedSet<Docente> docentes) {
        docenteRepositorio.setList(docentes);
    }

    public void inserirDocentes(SortedSet<Docente> docentes) {
        for(Docente docente : docentes){
            create(docente.getNomeExibicao(), docente.getTitulo(), docente.getDepartamento());
        }
    }

    public static DocenteControl getInstance(){
        if(docenteControl == null){
            docenteControl = new DocenteControlImpl();
        }
        return docenteControl;
    }
}
