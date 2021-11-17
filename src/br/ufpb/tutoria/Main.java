package br.ufpb.tutoria;

import br.ufpb.tutoria.business.control.UsuarioControl;
import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UsuarioRepositorio;
import br.ufpb.tutoria.infra.UsuarioRepositorioImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioImpl(usuarios);
        UsuarioControl usuarioControl = new UsuarioControlImpl(usuarios, usuarioRepositorio);
        usuarioControl.createUser("teste", "1sdfdsf2");
    }
}
