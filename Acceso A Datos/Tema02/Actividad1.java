import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Actividad1 {

    public static void main(String[] args) {

        try {
            PipedOutputStream salida = new PipedOutputStream();
            PipedInputStream entrada = new PipedInputStream(salida);

            Thread productor = new Thread(() -> {
                try {
                    salida.write("Mensaje1\n".getBytes());
                    Thread.sleep(2000);

                    salida.write("Mensaje2\n".getBytes());
                    Thread.sleep(2000);

                    salida.write("Mensaje3\n".getBytes());
                    Thread.sleep(2000);

                    salida.close();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });

            Thread consumidor = new Thread(() -> {
                try {
                    int dato;
                    int caracteres = 0;
                    int mensajes = 0;

                    while ((dato = entrada.read()) != -1) {
                        System.out.print((char) dato);
                        caracteres++;

                        if (dato == '\n') {
                            mensajes++;
                        }
                    }

                    System.out.println("Caracteres: " + caracteres);
                    System.out.println("Mensajes: " + mensajes);

                    entrada.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            productor.start();
            consumidor.start();
            
            productor.join();
            consumidor.join();

            System.out.println("Los dos hilos han terminado.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
