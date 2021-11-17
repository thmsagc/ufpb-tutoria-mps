package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;
import br.ufpb.tutoria.exception.UnexpectedStringException;

public class CS_MenorQueTrezeCaracteres implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws UnexpectedStringException {
        if(string.length() > 12){
            throw new UnexpectedStringException("Erro: A string "+ titulo + " deve conter no máximo 12 caracteres.");
        }
    }
}