package br.ufpb.tutoria;

import br.ufpb.tutoria.business.control.UsuarioControl;
import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.infra.repositorio.UsuarioRepositorio;
import br.ufpb.tutoria.infra.repositorio.UsuarioRepositorioImpl;

public class Main {

    public static void main(String[] args) {

        UsuarioRepositorio usuarioRepositorio = UsuarioRepositorioImpl.getInstance();
        UsuarioControl usuarioControl = UsuarioControlImpl.getInstance();

        usuarioControl.createUser("zezin", "1sdfdsf2", "15/12/1992");
        usuarioControl.createUser("teste", "1sdfdsf2", "19/10/2001");
        usuarioControl.createUser("alfred", "1sdfdsf2", "31/11/1995");
        usuarioControl.listarUsuarios();
    }
}
