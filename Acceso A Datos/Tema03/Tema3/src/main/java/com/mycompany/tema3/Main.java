package com.mycompany.tema3;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Main {

    public static void main(String[] args) {

        try {

            TiendaXML tienda = new TiendaXML();

            Document document = tienda.cargaXML();

            NodeList lista = tienda.obtenerNombres(document);

            // Imprimir solo el primer nombre
            System.out.println(lista.item(0).getTextContent());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}