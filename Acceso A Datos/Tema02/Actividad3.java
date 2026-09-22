import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Actividad3 {

    public static void main(String[] args) throws IOException, InterruptedException {

        File ficheroOrigen = new File("origen.txt");
        File ficheroDestino = new File("destino.txt");
        
        if (!ficheroOrigen.exists()) {
            ficheroOrigen.createNewFile();
        }
        
        if (!ficheroDestino.exists()) {
           ficheroDestino.createNewFile();
        }

        PipedOutputStream salida = new PipedOutputStream();
        PipedInputStream entrada = new PipedInputStream(salida);
        
        Thread hiloLectura = new Thread(() -> {

            try (FileInputStream lectura = new FileInputStream(ficheroOrigen)) {

                int dato;

                while ((dato = lectura.read()) != -1) {

                    salida.write(dato);
                }

                salida.close();

            } catch (IOException e) {
                System.out.println("Error en el hilo de lectura: "
                        + e.getMessage());
            }
        });

        Thread hiloCopia = new Thread(() -> {

            int caracteres = 0;
            int lineas = 0;

            try (
                FileOutputStream escritor = new FileOutputStream(ficheroDestino)
            ) {

                int dato;
                
                while ((dato = entrada.read()) != -1) {
                    
                    escritor.write(dato);
                    caracteres++;
                    
                    if (dato == '\n') {
                        lineas++;
                    }
                }

                if (caracteres > 0 && dato != '\n') {
                    lineas++;
                }

                System.out.println();
                System.out.println("Lineas procesadas: " + lineas);
                System.out.println("Caracteres procesados: " + caracteres);

                entrada.close();

            } catch (IOException e) {
                System.out.println("Error en el hilo de copia: " + e.getMessage());
            }
        });
                
                hiloLectura.start();
                hiloCopia.start();
                
                hiloLectura.join();
                hiloCopia.join();
                
                long tamañoOrigen = ficheroOrigen.length();
                long tamañoDestino = ficheroDestino.length();

                System.out.println("Fichero origen: " + ficheroOrigen.getName());
                System.out.println("Fichero destino: " + ficheroDestino.getName());
                System.out.println("Tamano origen: " + tamañoOrigen + " bytes");
                System.out.println("Tamano destino: " + tamañoDestino + " bytes");
                
        
    }
}





