package br.ufpb.tutoria;

import br.ufpb.tutoria.business.control.UsuarioControl;
import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.infra.UsuarioRepositorio;
import br.ufpb.tutoria.infra.UsuarioRepositorioImpl;

public class Main {

    public static void main(String[] args) {


        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioImpl();
        UsuarioControl usuarioControl = new UsuarioControlImpl(usuarioRepositorio);
        usuarioControl.createUser("zezin", "1sdfdsf2", "15/12/1992");
        usuarioControl.createUser("teste", "1sdfdsf2", "19/10/2001");
        usuarioControl.createUser("alfred", "1sdfdsf2", "31/11/1995");
        usuarioControl.listarUsuarios();
    }
}
