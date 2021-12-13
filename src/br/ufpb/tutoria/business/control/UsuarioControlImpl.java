package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.business.control.criteriosstring.*;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.exception.UnexpectedStringException;
import br.ufpb.tutoria.infra.repositorio.UsuarioRepositorio;
import br.ufpb.tutoria.infra.repositorio.UsuarioRepositorioImpl;
import br.ufpb.tutoria.util.Warning;

import java.util.SortedSet;

public class UsuarioControlImpl implements UsuarioControl {

    public static UsuarioControl usuarioControl;

    private final UsuarioRepositorio usuarioRepositorio;

    private final CS_ConterDoisNumerosOuMais cs_conterDoisNumerosOuMais = new CS_ConterDoisNumerosOuMais();
    private final CS_MaiorQueSeteCaracteres cs_maiorQueSeteCaracteres = new CS_MaiorQueSeteCaracteres();
    private final CS_MenorQueTrezeCaracteres cs_menorQueTrezeCaracteres = new CS_MenorQueTrezeCaracteres();
    private final CS_MenorQueVinteUmCaracteres cs_MenorQueVinteUmCaracteres = new CS_MenorQueVinteUmCaracteres();
    private final CS_NaoConterNumeros cs_naoConterNumeros = new CS_NaoConterNumeros();
    private final CS_NaoVazio cs_naoVazio = new CS_NaoVazio();

    private UsuarioControlImpl() {
        this.usuarioRepositorio = UsuarioRepositorioImpl.getInstance();
    }

    public boolean createUser(String username, String senha, String dataNascimento) {

        Usuario usuario = new Usuario(username, senha, new Data(dataNascimento));

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

    public void listarUsuarios() {
        for(Usuario user: usuarioRepositorio.getList()){
            System.out.println("Login: "+user.getUsuario()+" senha: "+user.getSenha() + " data nascimento: "+ user.getDataNascimento());
        }
    }

    public Usuario getUser(String nome){
        try {
            usuarioRepositorio.procurar(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean saveUser(Usuario usuario) {
        try {
            usuarioRepositorio.gravar(usuario);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(String nome){
        try {
            usuarioRepositorio.apagar(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean updateUser(Usuario usuario){
        try {
            usuarioRepositorio.atualizar(usuario);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public SortedSet<Usuario> getUsuarios() {
        return usuarioRepositorio.getList();
    }

    public void setUsuarios(SortedSet<Usuario> usuarios) {
        usuarioRepositorio.setList(usuarios);
    }

    public void inserirUsuarios(SortedSet<Usuario> usuarios) {
        for(Usuario usuario : usuarios){
            createUser(usuario.getUsuario(), usuario.getSenha(), usuario.getDataNascimento().toString());
        }
    }

    public static UsuarioControl getInstance(){
        if(usuarioControl == null){
            usuarioControl = new UsuarioControlImpl();
        }
        return usuarioControl;
    }
}
