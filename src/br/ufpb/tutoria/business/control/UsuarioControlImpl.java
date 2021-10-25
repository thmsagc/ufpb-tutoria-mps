package br.ufpb.tutoria.business.control;

public class UsuarioControlImpl implements UsuarioControl{
    private String usuario;
    private String senha;

    public boolean inserirUsuario(String usuario, String senha) throws Exception {
        if(usuario.matches(".*\\d.*"))
            throw new Exception("O NOME DO USUÁRIO NÃO PODE CONTER NÚMEROS.");
        if(usuario.length() > 20)
            throw new Exception("O NOME DO USUÁRIO NÃO PODE EXCEDER 20 CARACTERES.");
        if(usuario.length() < 8)
            throw new Exception("O NOME DO USUÁRIO DEVE CONTER PELO MENOS 8 CARACTERES.");

        if(senha.matches(".*\\d.*"))
            throw new Exception("O NOME DO USUÁRIO NÃO PODE CONTER NÚMEROS.");
        if(senha.length() > 20)
            throw new Exception("O NOME DO USUÁRIO NÃO PODE EXCEDER 20 CARACTERES.");
        if(senha.length() < 8)
            throw new Exception("O NOME DO USUÁRIO DEVE CONTER PELO MENOS 8 CARACTERES.");

        return true;
    }

    public boolean existsUsuario(String usuario, String senha){
        return true;
    }
}
