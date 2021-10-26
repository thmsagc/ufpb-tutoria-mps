package br.ufpb.tutoria.business.control;

public class CriteriosStringImpl implements CriteriosString{
    @Override
    public void maiorQueSeteCaracteres(String titulo, String string) throws Exception {
        if(string.length() > 7)
            throw new Exception("Erro: A string "+ titulo + " deve conter pelo menos 8 caracteres.");
    }

    @Override
    public void menorQueVinteEUmCaracteres(String titulo, String string) throws Exception {
        if(string.length() < 21)
            throw new Exception("Erro: A string "+ titulo + " deve ser menor que 20 caracteres.");
    }

    @Override
    public void menorQueTrezeCaracteres(String titulo, String string) throws Exception {
        if(string.length() < 13)
            throw new Exception("Erro: A string "+ titulo + " deve ser menor que 20 caracteres.");
    }

    @Override
    public void naoContemNumeros(String titulo, String string) throws Exception {
        if(string.matches(".*\\d.*"))
            throw new Exception("Erro: A string "+ titulo + " não deve conter números.");
    }

    @Override
    public void naoVazio(String titulo, String string) throws Exception {
        if(string.isEmpty())
            throw new Exception("Erro: A string "+ titulo + " não pode ser vazia.");
    }

    @Override
    public void contemDoisNumeros(String titulo, String string) throws Exception {
        if(string.matches("^(?=.*?\\\\d.*\\\\d)[a-zA-Z0-9]$"))
            throw new Exception("Erro: A string "+ titulo + " não deve conter pelo menos dois números.");
    }
}
