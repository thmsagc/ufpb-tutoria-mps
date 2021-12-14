package br.ufpb.tutoria.business.model;

import java.util.List;

public class Relatorio {
    private List<String> linhas;

    public Relatorio(List<String> linhas) {
        this.linhas = linhas;
    }

    public List<String> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<String> linhas) {
        this.linhas = linhas;
    }
}
