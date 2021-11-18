package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Usuario;

import java.util.List;
import java.util.SortedSet;

public interface UsuarioControl {
    public boolean createUser(String nome, String senha, String dataNascimento);
    public void listarUsuarios();
    public Usuario getUser(String nome);
    public boolean saveUser(Usuario usuario);
    public boolean deleteUser(String nome);
    public boolean updateUser(Usuario usuario);
    public SortedSet<Usuario> getUsuarios();
    public void setUsuarios(SortedSet<Usuario> usuarios);
    public void inserirUsuarios (SortedSet<Usuario> usuarios);
}
