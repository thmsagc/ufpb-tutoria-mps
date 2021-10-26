package br.ufpb.tutoria;

import br.ufpb.tutoria.business.control.UsuarioControl;
import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UserRepository;
import br.ufpb.tutoria.infra.UserRepositoryImpl;

public class Main {

    public static UserRepository userRepository = new UserRepositoryImpl();
    public static UsuarioControl usuarioControl = new UsuarioControlImpl();;

    public static void main(String[] args) throws Exception{

        usuarioControl.setUsuarios(userRepository.carregarUsuarios());

        Usuario usuario1 = new Usuario("testeum", "1GFdmglG1");
        Usuario usuario2 = new Usuario("testedois", "1jfffffm2");
        Usuario usuario3 = new Usuario("testetres", "1hjfffff3");

        usuarioControl.createUser(usuario1);
        usuarioControl.createUser(usuario2);
        usuarioControl.createUser(usuario3);

        //usuarioControl.listarUsuarios();

    }
}
