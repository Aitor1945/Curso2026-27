import java.io.FileReader;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Actividad2 {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Creamos el pipe
        PipedOutputStream salida = new PipedOutputStream();
        PipedInputStream entrada = new PipedInputStream(salida);

        // HILO 1: lee el fichero y escribe en el pipe
        Thread hiloLectura = new Thread(() -> {

            try (FileReader lector = new FileReader("origen.txt")) {

                int caracter;

                // Leemos el fichero carácter a carácter
                while ((caracter = lector.read()) != -1) {

                    // Enviamos el carácter al pipe
                    salida.write(caracter);
                }

                // Cerramos el pipe cuando hemos terminado
                salida.close();

            } catch (IOException e) {
                System.out.println("Error al leer el fichero: " + e.getMessage());
            }
        });

        // HILO 2: recibe los datos del pipe y los muestra
        Thread hiloMostrar = new Thread(() -> {

            try {

                int caracter;

                // Leemos los datos que llegan por el pipe
                while ((caracter = entrada.read()) != -1) {

                    System.out.print((char) caracter);
                }

                // Cerramos la entrada
                entrada.close();

            } catch (IOException e) {
                System.out.println("Error al leer del pipe: " + e.getMessage());
            }
        });

        // Iniciamos los dos hilos
        hiloLectura.start();
        hiloMostrar.start();

        // Esperamos a que terminen
        hiloLectura.join();
        hiloMostrar.join();

        System.out.println();
        System.out.println("Proceso terminado.");
    }
}