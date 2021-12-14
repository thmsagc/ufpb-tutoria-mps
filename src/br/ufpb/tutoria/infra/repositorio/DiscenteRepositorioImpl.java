package br.ufpb.tutoria.infra.repositorio;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Discente;
import br.ufpb.tutoria.exception.ExistingUserException;
import br.ufpb.tutoria.exception.NoUserException;
import br.ufpb.tutoria.util.Warning;

import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;

public class DiscenteRepositorioImpl implements DiscenteRepositorio {

    private SortedSet<Discente> discentes = new TreeSet<>();

    private static DiscenteRepositorio discenteeRepositorio;

    private DiscenteRepositorioImpl() {
        this.carregar();
    }

    @Override
    public boolean gravar(Discente discente) throws Exception {
        try {
            for(Discente d : discentes){
                if(d.getNomeExibicao().equals(discente.getNomeExibicao()))
                    throw new Exception("Erro.");
            }
            discentes.add(new Discente(discente.getNomeExibicao(), discente.getMatricula(), discente.getDataIngresso()));
            gravarArquivo(discente);
            return true;
        } catch (Exception e){
            throw new ExistingUserException("Erro: Existe um usuário com nome " + discente.getNomeExibicao() + " nos arquivos.");
        }
    }

    @Override
    public void gravarArquivo(Discente discente) throws Exception {
        String pathCompleto = UfpbTutoriaConfig.pathDiscentes + discente.getNomeExibicao() + ".txt";
        FileOutputStream arq = new FileOutputStream(pathCompleto);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        gravarArq.writeUTF(discente.getNomeExibicao());
        gravarArq.writeUTF(discente.getMatricula());
        gravarArq.writeUTF(discente.getDataIngresso().toString());

        arq.close();
        gravarArq.close();
    }

    @Override
    public void carregar() {
        try {
            File folder = new File(UfpbTutoriaConfig.pathDiscentes);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                assert listOfFiles != null;
                for (File file : listOfFiles) {
                    if (file.isFile()) {

                        FileInputStream arq = new FileInputStream(file);
                        DataInputStream lerArq = new DataInputStream(arq);

                        String nomeExibicao = lerArq.readUTF();
                        String matricula = lerArq.readUTF();
                        String dataIngresso = lerArq.readUTF();

                        arq.close();
                        lerArq.close();
                        discentes.add(new Discente(nomeExibicao, matricula, new Data(dataIngresso)));
                    }
                }
            } else {
                Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
                Warning.warn("Certifique-se de que a pasta_discentes está criada corretamente.");
                System.exit(1);
            }
        } catch(Exception e){
            Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
            System.exit(1);
        }
        setList(discentes);
    }

    @Override
    public boolean apagar(String nomeExibicao) throws Exception {
        try {
            Discente finded = this.procurar(nomeExibicao);
            String pathCompleto = UfpbTutoriaConfig.pathDiscentes + nomeExibicao + ".txt";
            File f = new File(pathCompleto);
            if (f.delete()) {
                discentes.remove(finded);
                return true;
            }
        } catch (Exception e) {
            throw new NoUserException("Erro: Falha ao deletar o usuário de nome " + nomeExibicao + ".");
        }
        return false;
    }

    @Override
    public Discente procurar(String nomeExibicao) throws Exception {
        for(Discente d : discentes){
            if(d.getNomeExibicao().equals(nomeExibicao)){
                return d;
            }
        }
        throw new NoUserException("Erro: Não existe um usuário com nome " + nomeExibicao + ".");
    }

    @Override
    public void atualizar(Discente discente) throws Exception {
        try {
            procurar(discente.getNomeExibicao());
            gravarArquivo(discente);
        } catch (Exception e){
            throw new NoUserException("Erro: Não existe um usuário com nome " + discente.getNomeExibicao() + " nos arquivos.");
        }
    }

    @Override
    public SortedSet<Discente> getList() {
        return discentes;
    }

    @Override
    public void setList(SortedSet<Discente> discentes) {
        this.discentes = discentes;
    }

    public static DiscenteRepositorio getInstance() {
        if(discenteeRepositorio == null)
            discenteeRepositorio = new DiscenteRepositorioImpl();
        return discenteeRepositorio;
    }
}
