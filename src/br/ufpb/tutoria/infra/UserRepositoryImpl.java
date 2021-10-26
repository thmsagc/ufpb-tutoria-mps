package br.ufpb.tutoria.infra;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import br.ufpb.tutoria.Main;
import br.ufpb.tutoria.business.model.Usuario;

public class UserRepositoryImpl implements UserRepository{

    public void gravaUsuario(Usuario usuario) throws IOException {

        String pathCompleto = Main.path+ usuario.getUsuario() +".txt";

        FileOutputStream arq = new FileOutputStream(pathCompleto);
        DataOutputStream gravarArq = new DataOutputStream(arq);

        gravarArq.writeUTF(usuario.getUsuario());
        gravarArq.writeUTF(usuario.getSenha());

        arq.close();
        gravarArq.close();

        System.out.print("\nDados do usuário "+ usuario.getUsuario() +" com sucesso.");
    }

    public List<Usuario> carregarUsuarios() throws IOException {
        File folder = new File(Main.path);
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

    public boolean apagarUsuarios(String usuario) throws IOException {

        File folder = new File(Main.path);
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {

            if (file.getName().equals(Main.path+ usuario +".txt")) {

                return file.delete();
            }
        }

        return false;

    }
}
