package br.ufpb.tutoria.infra;

import br.ufpb.tutoria.business.model.Usuario;

import java.io.IOException;
import java.util.List;

public interface UserRepository {
    void gravaUsuario(Usuario usuario) throws IOException;
    List<Usuario> carregarUsuarios() throws IOException;
    boolean apagarUsuarios(String usuario) throws IOException;
}
