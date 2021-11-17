package br.ufpb.tutoria;

import br.ufpb.tutoria.business.control.UsuarioControl;
import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.business.control.criteriosstring.*;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UsuarioRepositorio;
import br.ufpb.tutoria.infra.UsuarioRepositorioImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{

        List<Usuario> usuarios = new ArrayList<Usuario>();

        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioImpl(usuarios);

        CS_ConterDoisNumerosOuMais cs_conterDoisNumerosOuMais = new CS_ConterDoisNumerosOuMais();
        CS_MaiorQueSeteCaracteres cs_maiorQueSeteCaracteres = new CS_MaiorQueSeteCaracteres();
        CS_MenorQueTrezeCaracteres cs_menorQueTrezeCaracteres = new CS_MenorQueTrezeCaracteres();
        CS_MenorQueVinteUmCaracteres cs_MenorQueVinteUmCaracteres = new CS_MenorQueVinteUmCaracteres();
        CS_NaoConterNumeros cs_naoConterNumeros = new CS_NaoConterNumeros();
        CS_NaoVazio cs_naoVazio = new CS_NaoVazio();

        UsuarioControl usuarioControl = new UsuarioControlImpl(
                usuarios,
                usuarioRepositorio,
                cs_conterDoisNumerosOuMais,
                cs_maiorQueSeteCaracteres,
                cs_menorQueTrezeCaracteres,
                cs_MenorQueVinteUmCaracteres,
                cs_naoConterNumeros,
                cs_naoVazio
        );

        usuarioControl.setUsuarios(usuarioRepositorio.carregarUsuarios());

        Usuario usuario1 = new Usuario("testeum", "1GFdmglG1");
        Usuario usuario2 = new Usuario("testedois", "1jfffffm2");
        Usuario usuario3 = new Usuario("testetres", "1hjfffff3");

        usuarioControl.createUser(usuario1);
        usuarioControl.createUser(usuario2);
        usuarioControl.createUser(usuario3);

        //usuarioControl.listarUsuarios();

    }
}
