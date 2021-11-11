package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.business.model.Usuario;

import java.io.IOException;
import java.util.List;

public interface UsuarioRepositorio {
    boolean gravaUsuario(Usuario usuario) throws Exception;

    List<Usuario> carregarUsuarios() throws IOException;

    boolean apagarUsuarioByName(String usuario) throws Exception;

    Usuario findByName(String username) throws Exception;

    boolean atualizarUsuario(Usuario usuario) throws Exception;
}
