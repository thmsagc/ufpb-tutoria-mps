package br.ufpb.tutoria;

import br.ufpb.tutoria.business.model.Usuario;
import br.ufpb.tutoria.infra.UserRepository;

import java.io.IOException;
import java.util.List;

public class Main {

    //TODO VERIFICAR SE O NOME DE USUÁRIO JÁ EXISTE. GERAR EXCEÇÃO CASO JÁ EXISTIR E SALVAR CASO CONTRÁRIO.
    // VERIFICAR SE O NOME EXISTE NA LISTA usuarios e retornar true ou false;

    //TODO CRIAR MÉTODO PARA LER UM USUÁRIO NA CLASSE USERREPOSITORY get(String nome)
    // VERIFICAR SE O NOME EXISTE NA LISTA usuarios e retornar o usuário.

    //TODO CRIAR MÉTODO PARA DELETAR UM USUÁRIO NA CLASSE USERREPOSITORY deletar(String nome)
    // https://www.javatpoint.com/how-to-delete-a-file-in-java#:~:text=In%20Java%2C%20we%20can%20delete,must%20be%20empty%20to%20delete.

    //TODO CRIAR MÉTODO PARA ATUALIZAR UM USUÁRIO NA CLASSE USERREPOSITORY update(String nome, Usuario usuario)
    // verificar se o usuário existe na lista de usuarios e sobreescrever

    //TODO CRIAR MÉTODO PARA IMPRIMIR OS ATRIBUTOS DE UM USUÁRIO printUser(String nome)

    //TODO CRIAR MÉTODO PARA VERIFICAR SE UM USUÁRIO NA CLASSE USER REPOSITORY
    // EXISTE E COLOCAR NO INSERIRUSUARIO existeUsuario(String nome)

    //TODO CRIAR MÉTODOS PARA ENCAPSULAR O USERREPOSITORY NA CLASSE USUARIOCONTROL
    // EXEMPLO  public void salvar(Usuario usuario) throws IOException { UserRepository.GravaUsuario(usuario); }

    public static List<Usuario> usuarios;

    //TODO ALTERAR PARA TESTAR EM SEU PC
    //TODO FAZER UM ARQUIVO DE CONFIGURAÇÃO PARA O PROJETO
    public static final String path = "/home/local/CONDUCTOR/thomas.chaves/dev/git/UFPB-TUTORIA/pasta_usuarios/";

    public static void main(String[] args) throws IOException {

        usuarios = UserRepository.CarregarUsuarios();


        Usuario usuario1 = new Usuario("teste1", "123");
        Usuario usuario2 = new Usuario("teste2", "123");
        Usuario usuario3 = new Usuario("teste3", "123");

        UserRepository.GravaUsuario(usuario1);
        UserRepository.GravaUsuario(usuario2);
        UserRepository.GravaUsuario(usuario3);
    }
}
