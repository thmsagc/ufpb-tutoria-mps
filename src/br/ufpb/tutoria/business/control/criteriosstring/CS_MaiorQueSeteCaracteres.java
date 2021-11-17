package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;

public class CS_MaiorQueSeteCaracteres implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws Exception {
        if(string.length() < 8){
            throw new Exception("Erro: A string "+ titulo + " deve conter pelo menos 8 caracteres.");
        }
    }
}