package com.mycompany.tema3;

import org.w3c.dom.NodeList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TiendaXML {

    public Document cargaXML() throws Exception {

        File archivo = new File("alunos.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse(archivo);

        return documento;
    }
    
   public NodeList obtenerNombres(Document document) throws Exception {

    XPath xpath = XPathFactory.newInstance().newXPath();

    String expresion = "//alumno/nombre";

    NodeList lista = (NodeList) xpath.evaluate(
            expresion,
            document,
            XPathConstants.NODESET
    );

    return lista;
}
}