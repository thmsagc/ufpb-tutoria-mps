package br.ufpb.tutoria.infra.repositorio;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Docente;
import br.ufpb.tutoria.exception.ExistingUserException;
import br.ufpb.tutoria.exception.NoUserException;
import br.ufpb.tutoria.util.Warning;

import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;

public class DocenteRepositorioImpl implements DocenteRepositorio {

    private SortedSet<Docente> docentes = new TreeSet<>();

    private static DocenteRepositorio docenteRepositorio;

    private DocenteRepositorioImpl() {
        this.carregar();
    }

    @Override
    public boolean gravar(Docente docente) throws ExistingUserException {

        try {
            for(Docente d : docentes){
                if(d.getNomeExibicao().equals(docente.getNomeExibicao()))
                    throw new Exception("Erro.");
            }
            docentes.add(new Docente(docente.getNomeExibicao(), docente.getTitulo(), docente.getDepartamento()));
            gravarArquivo(docente);
            return true;
        } catch (Exception e){
            throw new ExistingUserException("Erro: Existe um usuário com nome " + docente.getNomeExibicao() + " nos arquivos.");
        }
    }

    @Override
    public void atualizar(Docente docente) throws NoUserException {
        try {
            procurar(docente.getNomeExibicao());
            gravarArquivo(docente);
        } catch (Exception e){
            throw new NoUserException("Erro: Não existe um usuário com nome " + docente.getNomeExibicao() + " nos arquivos.");
        }
    }

    public void gravarArquivo(Docente docente) throws IOException {
        String pathCompleto = UfpbTutoriaConfig.pathDocentes + docente.getNomeExibicao() + ".txt";
        FileOutputStream arq = new FileOutputStream(pathCompleto);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        gravarArq.writeUTF(docente.getNomeExibicao());
        gravarArq.writeUTF(docente.getTitulo());
        gravarArq.writeUTF(docente.getDepartamento());

        arq.close();
        gravarArq.close();
    }

    @Override
    public void carregar() {
        try {
            File folder = new File(UfpbTutoriaConfig.pathDocentes);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                assert listOfFiles != null;
                for (File file : listOfFiles) {
                    if (file.isFile()) {

                        FileInputStream arq = new FileInputStream(file);
                        DataInputStream lerArq = new DataInputStream(arq);

                        String nomeExibicao = lerArq.readUTF();
                        String titulo = lerArq.readUTF();
                        String departamento = lerArq.readUTF();

                        arq.close();
                        lerArq.close();
                        docentes.add(new Docente(nomeExibicao, titulo, departamento));
                    }
                }
            } else {
                Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
                Warning.warn("Certifique-se de que a pasta_docentes está criada corretamente.");
                System.exit(1);
            }
        } catch(Exception e){
                Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
                System.exit(1);
        }
        setList(docentes);
    }

    public boolean apagar(String nomeExibicao) throws NoUserException {
        try {
            Docente finded = procurar(nomeExibicao);
            String pathCompleto = UfpbTutoriaConfig.pathDocentes + nomeExibicao + ".txt";
            File f = new File(pathCompleto);
            if (f.delete()) {
                docentes.remove(finded);
                return true;
            }
        } catch (Exception e) {
            throw new NoUserException("Erro: Falha ao deletar o usuário de nome " + nomeExibicao + ".");
        }
        return false;
    }

    @Override
    public Docente procurar(String nomeExibicao) throws NoUserException {
        for(Docente d : docentes){
            if(d.getNomeExibicao().equals(nomeExibicao)){
                return d;
            }
        }
        throw new NoUserException("Erro: Não existe um usuário com nome " + nomeExibicao + ".");
    }

    @Override
    public SortedSet<Docente> getList() {
        return docentes;
    }

    @Override
    public void setList(SortedSet<Docente> docentes) {
        this.docentes = docentes;
    }

    public static DocenteRepositorio getInstance(){
        if(docenteRepositorio == null)
            docenteRepositorio = new DocenteRepositorioImpl();
        return docenteRepositorio;
    }
}
