package br.ufpb.tutoria.infra.relatorio;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Relatorio;
import br.ufpb.tutoria.util.Warning;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelatorioTXT extends RelatorioAbstrato {

    public RelatorioTXT() {
        this.dataGeracao = new Data(new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString());
    }

    public void SalvarRelatorio(Relatorio relatorio){
        try {
            File arquivoRelatorio = new File(UfpbTutoriaConfig.pathRelatorios+"relatorio_"+dataGeracao.toString()+".txt");
            if (!arquivoRelatorio.createNewFile()) {
                System.out.println("O relatório do dia "+dataGeracao+" já existe na pasta de relatórios.");
            }

            FileWriter fileWriter = new FileWriter(arquivoRelatorio);

            for(var linha : super.ObterDados())
                fileWriter.write(linha);

            fileWriter.close();

        } catch (IOException e) {
            Warning.warn("Ocorreu um erro na geração do relatório do dia "+dataGeracao.toString()+".");
            e.printStackTrace();
        }
    };
}
