import java.io.IOException;

/**
 * @author Jesús Guillén Encinas
 */
public class Procesbuilder {

    public static void main(String[] args) {
        /**
         * ProcessBuilder es una clase de Java que permite crear y ejecutar procesos
         * o comandos externos de forma controlada.
         */
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "www.google.es");
            Process proceso = builder.start();

            // Aquí se puede interactuar con el proceso

            int estadoSalida = proceso.waitFor();
            System.out.println("El proceso ha salido con: " + estadoSalida);
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}