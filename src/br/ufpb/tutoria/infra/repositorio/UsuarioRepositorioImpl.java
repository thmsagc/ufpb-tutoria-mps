package br.ufpb.tutoria.infra.repositorio;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.exception.ExistingUserException;
import br.ufpb.tutoria.exception.NoUserException;
import br.ufpb.tutoria.infra.adapter.AdaptadorLeitorXML;
import br.ufpb.tutoria.util.Warning;

import java.io.*;
import java.util.SortedSet;
import java.util.TreeSet;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {

    public static UsuarioRepositorio usuarioRepositorio;

    private SortedSet<Usuario> usuarios = new TreeSet<>();

    @Override
    public SortedSet<Usuario> getList() {
        return usuarios;
    }

    @Override
    public void setList(SortedSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private UsuarioRepositorioImpl() {
        this.carregar();
    }

    @Override
    public boolean gravar(Usuario usuario) throws ExistingUserException {

        try {
            for(Usuario u : usuarios){
                if(u.getUsuario().equals(usuario.getUsuario()))
                    throw new Exception("Erro.");
            }
            usuarios.add(new Usuario(usuario.getUsuario(), usuario.getSenha(), usuario.getDataNascimento()));
            gravarArquivo(usuario);
            return true;
        } catch (Exception e){
            throw new ExistingUserException("Erro: Existe um usuário com nome " + usuario.getUsuario() + " nos arquivos.");
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws NoUserException {
        try {
            procurar(usuario.getUsuario());
            gravarArquivo(usuario);
        } catch (Exception e){
            throw new NoUserException("Erro: Não existe um usuário com nome " + usuario.getUsuario() + " nos arquivos.");
        }
    }

    public void gravarArquivo(Usuario usuario) throws IOException {
        String pathCompleto = UfpbTutoriaConfig.pathUsuarios + usuario.getUsuario() + ".txt";
        FileOutputStream arq = new FileOutputStream(pathCompleto);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        gravarArq.writeUTF(usuario.getUsuario());
        gravarArq.writeUTF(usuario.getSenha());
        gravarArq.writeUTF(usuario.getDataNascimento().toString());

        arq.close();
        gravarArq.close();
    }

    @Override
    public void carregar() {
        try {
            File folder = new File(UfpbTutoriaConfig.pathUsuarios);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles != null) {
                assert listOfFiles != null;
                for (File file : listOfFiles) {
                    if (file.isFile()) {

                        FileInputStream arq = new FileInputStream(file);
                        DataInputStream lerArq = new DataInputStream(arq);

                        String nomeArquivo = file.getName();;
                        int pontoFormato = nomeArquivo.lastIndexOf('.');
                        String formato =  (pontoFormato == -1) ? "" : nomeArquivo.substring(pontoFormato + 1);

                        if(formato.equals("xml")) {
                            usuarios = AdaptadorLeitorXML.adaptadorLeitorXML.adaptar(file.toString());
                            return;
                        }
                        else if (formato.equals("txt"))
                        {

                            String usuarioArquivo = lerArq.readUTF();
                            String senhaArquivo = lerArq.readUTF();

                            String dataNascimento = lerArq.readUTF();

                            arq.close();
                            lerArq.close();
                            usuarios.add(new Usuario(usuarioArquivo, senhaArquivo, new Data(dataNascimento)));
                        }
                        else {
                            Warning.warn("Formato de arquivo '"+ formato +"' não esperado [arquivo:'"+nomeArquivo+"']");
                        }
                    }
                }
            } else {
                Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
                Warning.warn("Certifique-se de que a pasta_usuarios está criada corretamente.");
                System.exit(1);
            }
        } catch(Exception e){
                Warning.warn("ERRO CRÍTICO: Ocorreu um problema no carregamento de usuários.");
                System.exit(1);
        }
        setList(usuarios);
    }

    public boolean apagar(String usuario) throws NoUserException {
        try {
            Usuario finded = procurar(usuario);
            String pathCompleto = UfpbTutoriaConfig.pathUsuarios + usuario + ".txt";
            File f = new File(pathCompleto);
            if (f.delete()) {
                usuarios.remove(finded);
                return true;
            }
        } catch (Exception e) {
            throw new NoUserException("Erro: Falha ao deletar o usuário de nome " + usuario + ".");
        }
        return false;
    }

    @Override
    public Usuario procurar(String name) throws NoUserException {
        for(Usuario usuario : usuarios){
            if(usuario.getUsuario().equals(name)){
                return usuario;
            }
        }
        throw new NoUserException("Erro: Não existe um usuário com nome " + name + ".");
    }

    public static UsuarioRepositorio getInstance(){
        if(usuarioRepositorio == null){
            usuarioRepositorio = new UsuarioRepositorioImpl();
        }
        return usuarioRepositorio;
    }
}
