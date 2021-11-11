package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Usuario;

import java.io.*;
import java.util.List;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {

    private final List<Usuario> usuarios;

    public UsuarioRepositorioImpl(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean gravaUsuario(Usuario usuario) throws Exception {

        try {
            for(Usuario u : usuarios){
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
                usuarios.remove(finded);
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Erro: Falha ao deletar o usuário de nome " + usuario + ".");
        }
        return false;
    }

    @Override
    public Usuario findByName(String name) throws Exception {
        for(Usuario usuario : usuarios){
            if(usuario.getUsuario().equals(name)){
                return usuario;
            }
        }
        throw new Exception("Erro: Não existe um usuário com nome " + name + ".");
    }
}