package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;

public class CS_NaoConterNumeros implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws Exception {
        if(string.matches(".*\\d.*"))
            throw new Exception("Erro: A string "+ titulo + " não deve conter números.");
    }
}