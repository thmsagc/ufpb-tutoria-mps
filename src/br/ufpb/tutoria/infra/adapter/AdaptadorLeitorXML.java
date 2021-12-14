package br.ufpb.tutoria.infra.adapter;

import br.ufpb.tutoria.business.model.Data;
import br.ufpb.tutoria.business.model.Usuario;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;

public class AdaptadorLeitorXML implements AdaptadorGenerico<Usuario> {

    public static AdaptadorLeitorXML adaptadorLeitorXML;

    @Override
    public SortedSet<Usuario> adaptar(String pathXML) {
        File file = new File(pathXML);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();

        DocumentBuilder documentBuilder = null;
        Document document = null;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        SortedSet<Usuario> usuarios = null;

        for(var i = 0; i < document.getElementsByTagName("usuario").getLength(); i++) {
            String usr = document.getElementsByTagName("usuario").item(i).getTextContent();
            String pwd = document.getElementsByTagName("senha").item(i).getTextContent();
            String dataNascimento = document.getElementsByTagName("dataNascimento").item(i).getTextContent();
            usuarios.add(new Usuario(usr, pwd, new Data(dataNascimento)));
        }
        return usuarios;
    }

    public static AdaptadorLeitorXML getInstance(){
        if(adaptadorLeitorXML == null){
            adaptadorLeitorXML = new AdaptadorLeitorXML();
        }
        return adaptadorLeitorXML;
    }
}
