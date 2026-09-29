import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.tema3.Calculadora;
import com.mycompany.tema3.TiendaXML;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Tema3Test {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    void sumaDosNumeros() {
        assertEquals(5, calc.sumar(2, 3));
    }

    @Test
    void restaDosNumeros() {
        assertEquals(1, calc.restar(3, 2));
    }

    @Test
    void divideDosNumeros() {
        assertEquals(2, calc.dividir(4, 2));
    }

    @Test
    void dividirEntreCeroLanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> calc.dividir(4, 0));
    }

    @Test
    void primerNombreDelXml() throws Exception {
        TiendaXML tienda = new TiendaXML();

        Document document = tienda.cargaXML();
        NodeList lista = tienda.obtenerNombres(document);

        assertEquals("Lucía Fernández", lista.item(0).getTextContent());
    }
}