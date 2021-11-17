package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.exception.InternalErrorException;

import java.io.IOException;
import java.util.List;

public interface UsuarioRepositorio {
    boolean gravaUsuario(Usuario usuario) throws Exception;

    List<Usuario> carregarUsuarios() throws IOException, InternalErrorException;

    boolean apagarUsuarioByName(String usuario) throws Exception;

    Usuario findByName(String username) throws Exception;

    boolean atualizarUsuario(Usuario usuario) throws Exception;
}
