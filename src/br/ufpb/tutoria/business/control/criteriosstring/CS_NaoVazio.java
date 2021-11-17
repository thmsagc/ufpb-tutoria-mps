package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;

public class CS_NaoVazio implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws Exception {
        if(string.isEmpty())
            throw new Exception("Erro: A string "+ titulo + " não pode ser vazia.");
    }
}