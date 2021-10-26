package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UserRepository;
import br.ufpb.tutoria.infra.UserRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class UsuarioControlImpl {
    public List<Usuario> usuarios;

    private UserRepository userRepository;
    private CriteriosString criteriosString;

    public boolean inserirUsuario() throws Exception {

        for(Usuario user : usuarios) {
            criteriosString.naoContemNumeros("NOME DE USUARIO", user.getUsuario());
            criteriosString.menorQueTrezeCaracteres("NOME DE USUARIO", user.getUsuario());
            criteriosString.naoVazio("NOME DE USUARIO", user.getUsuario());

            criteriosString.maiorQueSeteCaracteres("SENHA", user.getSenha());
            criteriosString.menorQueVinteEUmCaracteres("SENHA", user.getSenha());
            criteriosString.contemDoisNumeros("SENHA", user.getSenha());

            try {
                userRepository.gravaUsuario(user);
            }catch (Exception e){
                throw new Exception(e);
            }
        }
        usuarios.clear();
        return true;
    }

    public List<Usuario> listarUsuarios() throws Exception{
        try {
            usuarios = userRepository.carregarUsuarios();

            for(Usuario user: usuarios){
                System.out.println("Login: "+user.getUsuario()+" senha: "+user.getSenha());
            }
        }catch(Exception e){
            throw new Exception(e);
        }
        return usuarios;
    }

    public void deletarUsuario(String login) throws Exception{
         if(!userRepository.apagarUsuarios(login)) throw new Exception("Erro ao deletar usuario");
    }
}
