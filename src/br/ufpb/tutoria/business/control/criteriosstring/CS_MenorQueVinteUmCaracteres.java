package br.ufpb.tutoria.business.control.criteriosstring;

import br.ufpb.tutoria.business.control.CriterioString;

public class CS_MenorQueVinteUmCaracteres implements CriterioString {
    @Override
    public void criterio(String titulo, String string) throws Exception {
        if(string.length() > 20){
            throw new Exception("Erro: A string "+ titulo + " deve conter no máximo 20 caracteres.");
        }
    }
}