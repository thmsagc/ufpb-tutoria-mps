package br.ufpb.tutoria.business.control;

import br.ufpb.tutoria.Main;
import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.util.Warning;

import java.util.List;

public class UsuarioControlImpl implements UsuarioControl {
    private List<Usuario> usuarios;

    private final CriteriosString criteriosString = new CriteriosStringImpl();

    public boolean createUser(Usuario usuario) {
        try {
            criteriosString.naoContemNumeros("NOME DE USUARIO", usuario.getUsuario());
            criteriosString.maximoDozeCaracteres("NOME DE USUARIO", usuario.getUsuario());
            criteriosString.naoVazio("NOME DE USUARIO", usuario.getUsuario());

            criteriosString.maiorQueOitoCaracteres("SENHA", usuario.getSenha());
            criteriosString.menorQueVinteEUmCaracteres("SENHA", usuario.getSenha());
            criteriosString.contemDoisNumeros("SENHA", usuario.getSenha());
        } catch (Exception e){
            Warning.warn(e.getMessage());
            return false;
        }
        return saveUser(usuario);
    }

    public List<Usuario> listarUsuarios() {
        try {
            usuarios = Main.userRepository.carregarUsuarios();

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
            Main.userRepository.findByName(nome);
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return null;
    }

    public boolean saveUser(Usuario usuario) {
        try {
            Main.userRepository.gravaUsuario(usuario);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(String nome){
        try {
            Main.userRepository.apagarUsuarioByName(nome);
            return true;
        } catch (Exception e){
            Warning.warn(e.getMessage());
        }
        return false;
    }

    public boolean updateUser(Usuario usuario){
        try {
            Main.userRepository.atualizarUsuario(usuario);
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
}
