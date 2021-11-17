package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;
import br.ufpb.tutoria.exception.UnexpectedStringException;

public class CS_NaoVazio implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws UnexpectedStringException {
        if(string.isEmpty())
            throw new UnexpectedStringException("Erro: A string "+ titulo + " não pode ser vazia.");
    }
}