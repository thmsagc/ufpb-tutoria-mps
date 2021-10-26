package br.ufpb.tutoria.infra;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.ufpb.tutoria.Main;
import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Usuario;

public class UserRepositoryImpl implements UserRepository{

    @Override
    public boolean gravaUsuario(Usuario usuario) throws Exception {

        try {
            for(Usuario u : Main.usuarioControl.getUsuarios()){
                if(u.getUsuario().equals(usuario.getUsuario()))
                    throw new Exception("Erro.");
            }
            salvarArquivoUsuario(usuario);
            return true;
        } catch (Exception e){
            throw new Exception("Erro: Existe um usuário com nome " + usuario.getUsuario() + " nos arquivos.");
        }
    }

    @Override
    public boolean atualizarUsuario(Usuario usuario) throws Exception {
        try {
            findByName(usuario.getUsuario());
            return salvarArquivoUsuario(usuario);
        } catch (Exception e){
            throw new Exception("Erro: Existe um usuário com nome " + usuario.getUsuario() + " nos arquivos.");
        }
    }

    private boolean salvarArquivoUsuario(Usuario usuario) throws IOException {
        String pathCompleto = UfpbTutoriaConfig.path + usuario.getUsuario() + ".txt";
        FileOutputStream arq = new FileOutputStream(pathCompleto);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        gravarArq.writeUTF(usuario.getUsuario());
        gravarArq.writeUTF(usuario.getSenha());

        arq.close();
        gravarArq.close();
        return true;
    }

    @Override
    public List<Usuario> carregarUsuarios() throws IOException {
        File folder = new File(UfpbTutoriaConfig.path);
        File[] listOfFiles = folder.listFiles();
        List<Usuario> usuarios = new ArrayList<Usuario>();;

        assert listOfFiles != null;
        for (File file : listOfFiles) {

            if (file.isFile()) {

                FileInputStream arq = new FileInputStream(file);
                DataInputStream lerArq = new DataInputStream(arq);

                String usuarioArquivo = lerArq.readUTF();
                String senhaArquivo = lerArq.readUTF();

                arq.close();
                lerArq.close();
                usuarios.add(new Usuario(usuarioArquivo, senhaArquivo));
            }
        }
        return usuarios;
    }

    public boolean apagarUsuarioByName(String usuario) throws Exception {
        try {
            Usuario finded = findByName(usuario);
            String pathCompleto = UfpbTutoriaConfig.path + usuario + ".txt";
            File f = new File(pathCompleto);
            if (f.delete()) {
                Main.usuarioControl.getUsuarios().remove(finded);
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Erro: Falha ao deletar o usuário de nome " + usuario + ".");
        }
        return false;
    }

    @Override
    public Usuario findByName(String name) throws Exception {
        for(Usuario usuario : Main.usuarioControl.getUsuarios()){
            if(usuario.getUsuario().equals(name)){
                return usuario;
            }
        }
        throw new Exception("Erro: Não existe um usuário com nome " + name + ".");
    }
}
