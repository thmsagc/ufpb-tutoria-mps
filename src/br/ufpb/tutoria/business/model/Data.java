package br.ufpb.tutoria.business.model;

import br.ufpb.tutoria.exception.InvalidDateFormatException;
import br.ufpb.tutoria.util.Warning;

import java.util.List;

public class Data {
    private Integer dia;
    private Integer mes;
    private Integer ano;

    public Data(String data) {
        try {
            var dataSeparada = data.split("/", 3);
            dia = Integer.parseInt(dataSeparada[0]);
            mes = Integer.parseInt(dataSeparada[1]);
            ano = Integer.parseInt(dataSeparada[2]);
        } catch (Exception e) {
            //throw new InvalidDateFormatException("Erro: Formato de data inválido.");
            Warning.warn("Erro: Formato de data inválido.");
        }
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString(){
        return dia+"/"+mes+"/"+ano;
    }
}
