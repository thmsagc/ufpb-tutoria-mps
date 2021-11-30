package br.ufpb.tutoria.business.model;

public class Docente {
    private String nomeExibicao;
    private String titulo;
    private String departamento;

    private Usuario usuario;

    public Docente(String nomeExibicao, String titulo, String departamento, Usuario usuario) {
        this.nomeExibicao = nomeExibicao;
        this.titulo = titulo;
        this.departamento = departamento;
        this.usuario = usuario;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
