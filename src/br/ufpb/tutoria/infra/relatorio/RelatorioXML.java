package br.ufpb.tutoria.infra.relatorio;

import br.ufpb.tutoria.UfpbTutoriaConfig;
import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Relatorio;
import br.ufpb.tutoria.util.Warning;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelatorioXML extends RelatorioAbstrato {

    public RelatorioXML() {
        this.dataGeracao = new Data(new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString());
    }

    public void SalvarRelatorio(Relatorio relatorio){
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("RELATORIO DE USUÁRIOS");
            document.appendChild(root);

            Attr attr = document.createAttribute("titulo");
            attr.setValue(super.ObterDados().get(0));
            root.setAttributeNode(attr);

            Element usuarios = document.createElement("USUARIOS_CADASTRADOS");
            root.appendChild(usuarios);

            attr = document.createAttribute("numeroUsuarios");
            attr.setValue(super.ObterDados().get(1));
            usuarios.setAttributeNode(attr);

            for(var linha = 3; linha < super.ObterDados().size(); linha++) {
                attr = document.createAttribute("idUsuario");
                attr.setValue(super.ObterDados().get(linha));
                attr = document.createAttribute("nomeUsuario");
                attr.setValue(super.ObterDados().get(linha));
                usuarios.setAttributeNode(attr);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(UfpbTutoriaConfig.pathRelatorios));
            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
