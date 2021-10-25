package br.ufpb.tutoria.business.control;

public interface UsuarioControl {
    public boolean inserirUsuario(String usuario, String senha) throws Exception;
    public boolean existsUsuario(String usuario, String senha);
}
