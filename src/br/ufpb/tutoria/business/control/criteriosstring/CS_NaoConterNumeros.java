package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;
import br.ufpb.tutoria.exception.UnexpectedStringException;

public class CS_NaoConterNumeros implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws UnexpectedStringException {
        if(string.matches(".*\\d.*"))
            throw new UnexpectedStringException("Erro: A string "+ titulo + " não deve conter números.");
    }
}