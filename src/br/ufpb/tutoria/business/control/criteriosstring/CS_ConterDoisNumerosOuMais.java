package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;

public class CS_ConterDoisNumerosOuMais implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws Exception {
        if(!string.matches("[0-9].*[0-9]"))
            throw new Exception("Erro: A string "+ titulo + " deve conter pelo menos dois números.");
    }
}