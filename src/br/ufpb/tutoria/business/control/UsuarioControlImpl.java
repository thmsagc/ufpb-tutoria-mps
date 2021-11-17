package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.control.criteriosstring.*;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.exception.UnexpectedStringException;
import br.ufpb.tutoria.infra.UsuarioRepositorio;
import br.ufpb.tutoria.util.Warning;

import java.util.List;

public class UsuarioControlImpl implements UsuarioControl {
    private List<Usuario> usuarios;

    private UsuarioRepositorio usuarioRepositorio;

    private CS_ConterDoisNumerosOuMais cs_conterDoisNumerosOuMais = new CS_ConterDoisNumerosOuMais();
    private CS_MaiorQueSeteCaracteres cs_maiorQueSeteCaracteres = new CS_MaiorQueSeteCaracteres();
    private CS_MenorQueTrezeCaracteres cs_menorQueTrezeCaracteres = new CS_MenorQueTrezeCaracteres();
    private CS_MenorQueVinteUmCaracteres cs_MenorQueVinteUmCaracteres = new CS_MenorQueVinteUmCaracteres();
    private CS_NaoConterNumeros cs_naoConterNumeros = new CS_NaoConterNumeros();
    private CS_NaoVazio cs_naoVazio = new CS_NaoVazio();

    public UsuarioControlImpl(List<Usuario> usuarios, UsuarioRepositorio usuarioRepositorio) {
        this.usuarios = usuarios;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public boolean createUser(String username, String senha) {

        Usuario usuario = new Usuario(username, senha);

        try {
            cs_naoConterNumeros.criterio("NOME DE USUARIO", usuario.getUsuario());
            cs_menorQueTrezeCaracteres.criterio("NOME DE USUARIO", usuario.getUsuario());
            cs_naoVazio.criterio("NOME DE USUARIO", usuario.getUsuario());

            cs_maiorQueSeteCaracteres.criterio("SENHA", usuario.getSenha());
            cs_MenorQueVinteUmCaracteres.criterio("SENHA", usuario.getSenha());
            cs_conterDoisNumerosOuMais.criterio("SENHA", usuario.getSenha());
        } catch (UnexpectedStringException e){
            Warning.warn(e.getMessage());
            return false;
        }
        return saveUser(usuario);
    }

    public List<Usuario> listarUsuarios() {
        try {
            usuarios = usuarioRepositorio.carregarUsuarios();

            for(Usuario user: usuarios){
                System.out.println("Login: "+user.getUsuario()+" senha: "+user.getSenha());
            }
        } catch(Exception e){
            Warning.warn(e.getMessage());
        }
        return usuarios;
    }

    public Usuario getUser(String nome){
        try {
            usuarioRepositorio.findByName(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean saveUser(Usuario usuario) {
        try {
            usuarioRepositorio.gravaUsuario(usuario);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(String nome){
        try {
            usuarioRepositorio.apagarUsuarioByName(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean updateUser(Usuario usuario){
        try {
            usuarioRepositorio.atualizarUsuario(usuario);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void inserirUsuarios(List<Usuario> usuarios) {
        for(Usuario usuario : usuarios){
            createUser(usuario.getUsuario(), usuario.getSenha());
        }
    }
}
