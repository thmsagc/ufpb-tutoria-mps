package br.ufpb.tutoria.business.model;

public class Discente {
    private String nomeExibicao;
    private String matricula;
    private Data dataIngresso;

    private Usuario usuario;

    public Discente(String nomeExibicao, String matricula, Data dataIngresso) {
        this.nomeExibicao = nomeExibicao;
        this.matricula = matricula;
        this.dataIngresso = dataIngresso;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Data getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Data dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
