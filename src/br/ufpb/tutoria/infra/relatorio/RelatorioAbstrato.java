package br.ufpb.tutoria.infra.relatorio;

import br.ufpb.tutoria.business.control.UsuarioControlImpl;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Relatorio;
import br.ufpb.tutoria.infra.UsuarioRepositorioImpl;

import java.util.List;

public abstract class RelatorioAbstrato {

    protected Data dataGeracao;

    public Relatorio GerarRelatorio(){
        List<String> dados = this.ObterDados();
        Relatorio relatorio = new Relatorio(dados);
        return relatorio;
    }

    abstract public void SalvarRelatorio(Relatorio relatorio);

    protected List<String> ObterDados(){
        List<String> dados = null;
        dados.add("\nRELATÓRIO DE USUÁRIOS DO UFPB TUTORIA\n" +
                "DATA DE GERAÇÃO: "+dataGeracao.toString()+"\n");
        dados.add("Número de Usuários: "+ UsuarioControlImpl.getInstance(UsuarioRepositorioImpl.getInstance()).getUsuarios().size() +"\n");
        dados.add("Lista de Usuários:\n"+ UsuarioControlImpl.getInstance(UsuarioRepositorioImpl.getInstance()).getUsuarios());
        for(var usuario : UsuarioControlImpl.getInstance(UsuarioRepositorioImpl.getInstance()).getUsuarios()){
            dados.add(usuario.getUsuario()+"\n");
        }
        return dados;
    }
}
