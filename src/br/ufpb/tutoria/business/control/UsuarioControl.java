package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Usuario;

import java.util.List;

public interface UsuarioControl {
    public boolean createUser(String nome, String senha);
    public List<Usuario> listarUsuarios();
    public Usuario getUser(String nome);
    public boolean saveUser(Usuario usuario);
    public boolean deleteUser(String nome);
    public boolean updateUser(Usuario usuario);
    public List<Usuario> getUsuarios();
    public void setUsuarios(List<Usuario> usuarios);
    public void inserirUsuarios (List<Usuario> usuarios);
}
