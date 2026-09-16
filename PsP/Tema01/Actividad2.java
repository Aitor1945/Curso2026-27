import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actividad2 {

    public static void main(String[] args) {
        /*
Crear un objeto ProcessBuilder.
Ejecutar el comando correspondiente según tu sistema operativo:
Windows: cmd /c dir
Linux/macOS: ls
Iniciar el proceso mediante start().
Utilizar BufferedReader para leer la salida que genera el proceso.
Mostrar por consola, línea por línea, el contenido que devuelve el comando.
Esperar a que el proceso termine utilizando waitFor().
Mostrar al final el código de salida del proceso.
        */
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "dir");
            Process proceso = builder.start();

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );
            
            String linea;

            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            
            int estadoSalida = proceso.waitFor();
            System.out.println("El proceso ha salido con: " + estadoSalida);

        } catch (IOException | InterruptedException e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
            
        }
        }
    }
