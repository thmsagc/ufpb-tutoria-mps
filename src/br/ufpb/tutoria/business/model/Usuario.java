package br.ufpb.tutoria.business.model;

import java.text.Collator;
import java.util.Locale;

public class Usuario implements Comparable<Usuario>{
    private String usuario;
    private String senha;
    private Data dataNascimento;

    public Usuario(String usuario, String senha, Data dataNascimento) {
        this.usuario = usuario;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getSenha() {
        return this.senha;
    }

    @Override
    public int compareTo(Usuario usuario) {
        Collator cot = Collator.getInstance(new Locale("pt","BR"));
        if(usuario != null)
            return cot.compare(this.getUsuario(), usuario.getUsuario());
        else
            return 0;
    }
}


