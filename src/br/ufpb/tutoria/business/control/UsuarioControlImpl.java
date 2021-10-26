package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UserRepository;

import java.io.IOException;

public class UsuarioControlImpl implements UsuarioControl{
    private String usuario;
    private String senha;

    private CriteriosString criteriosString;

    public boolean inserirUsuario(String usuario, String senha) throws Exception {

        criteriosString.naoContemNumeros("NOME DE USUARIO", usuario);
        criteriosString.menorQueTrezeCaracteres("NOME DE USUARIO", usuario);
        criteriosString.naoVazio("NOME DE USUARIO", usuario);

        criteriosString.maiorQueSeteCaracteres("SENHA", senha);
        criteriosString.menorQueVinteEUmCaracteres("SENHA", senha);
        criteriosString.contemDoisNumeros("SENHA", senha);

        return true;
    }
}
