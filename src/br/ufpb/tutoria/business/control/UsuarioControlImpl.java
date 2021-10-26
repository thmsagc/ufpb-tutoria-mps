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

    public boolean inserirUsuario(String usuario, String senha) throws Exception {

        criteriosString.naoContemNumeros("NOME DE USUARIO", usuario);
        criteriosString.menorQueTrezeCaracteres("NOME DE USUARIO", usuario);
        criteriosString.naoVazio("NOME DE USUARIO", usuario);

        criteriosString.maiorQueSeteCaracteres("SENHA", senha);
        criteriosString.menorQueVinteEUmCaracteres("SENHA", senha);
        criteriosString.contemDoisNumeros("SENHA", senha);

        Usuario user = new Usuario(usuario, senha);
        userRepository.gravaUsuario(user);

        return true;
    }

    public List<Usuario> listarUsuarios() throws Exception{
        try {
            usuarios = userRepository.carregarUsuarios();
        }catch(Exception e){
            throw new Exception(e);
        }
        return usuarios;
    }

    public void deletarUsuario(String login) throws Exception{
         if(!userRepository.apagarUsuarios(login)) throw new Exception("Erro ao deletar usuario");
    }
}
