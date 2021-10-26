package br.ufpb.tutoria.business.control;

public interface CriteriosString {
    public void maiorQueSeteCaracteres(String titulo, String string) throws Exception;
    public void menorQueVinteEUmCaracteres(String titulo, String string) throws Exception;
    public void menorQueTrezeCaracteres(String titulo, String string) throws Exception;
    public void naoContemNumeros(String titulo, String string) throws Exception;
    public void naoVazio(String titulo, String string) throws Exception;
    public void contemDoisNumeros(String titulo, String string) throws Exception;

}
