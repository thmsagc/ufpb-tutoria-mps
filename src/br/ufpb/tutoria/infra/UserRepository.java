package br.ufpb.tutoria.infra;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import br.ufpb.tutoria.business.model.Usuario;

public class UserRepository {
    public static void LeArquivo(String[] args) throws IOException {
        String login;
        String senha;
    
        FileInputStream arq = new FileInputStream("d:\\arquivo.dat");
        DataInputStream lerArq = new DataInputStream(arq);

        do {
            login = lerArq.readUTF();
            senha = lerArq.readUTF();
    
            System.out.printf("Nome..................: %s\n", login);
            System.out.printf("Sexo..................: %c\n", senha);
        
        } while(login != null || senha != null);
    
        arq.close();
    }

    public static void GravaArquivos(List <Usuario> usuarios) throws IOException {
        FileOutputStream arq = new FileOutputStream("d:\\arquivo.dat");
        DataOutputStream gravarArq = new DataOutputStream(arq);

        for(Usuario user: usuarios) {
            gravarArq.writeUTF(user.getUsuario());
            gravarArq.writeUTF(user.getSenha());
        }

        arq.close();
    
        System.out.printf("\nDados gravados com sucesso em \"d:\\arquivo.dat\".\n");
    }  
}
