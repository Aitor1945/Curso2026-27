import java.io.IOException;

/**
 * @author Jesús Guillén Encinas
 */
public class Runetime {

    public static void main(String[] args) {
        /**
         * La clase Runtime en Java (java.lang.Runtime) permite que una aplicación interactúe
         * directamente con el entorno de ejecución y la máquina virtual (JVM).
         * Sirve para:
         *     - Ejecutar comandos del ssoo 'exec()'
         *     - Consultar memoria de la JVM
         *     - Registrar tareas de apagado
         *     - etc
         */
        try {
            Runtime runtime = Runtime.getRuntime();// Crea instancia para ejecutar comandos 
            Process proceso = runtime.exec("ping www.google.es");

            // Aquí se puede interactuar con el proceso

            int estadoSalida = proceso.waitFor();
            System.out.println("El proceso ha salido con: " + estadoSalida);
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }
}
