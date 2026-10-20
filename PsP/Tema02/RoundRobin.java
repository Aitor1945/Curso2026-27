import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {

    static class Proceso {
        String nombre;
        int tiempoCreacion;
        int tiempoRestante;
        int tiempoCPU;
        int tiempoFin;

        public Proceso(String nombre, int tiempoCreacion, int tiempoCPU) {
            this.nombre = nombre;
            this.tiempoCreacion = tiempoCreacion;
            this.tiempoCPU = tiempoCPU;
            this.tiempoRestante = tiempoCPU;
        }
    }

    public static void main(String[] args) {

        int quantum = 2;
        int tiempoActual = 0;

        Proceso A = new Proceso("A", 2, 2);
        Proceso B = new Proceso("B", 0, 8);
        Proceso C = new Proceso("C", 3, 6);
        Proceso D = new Proceso("D", 4, 4);

        Proceso[] procesos = {B, A, C, D};

        Queue<Proceso> cola = new LinkedList<>();

        int terminados = 0;

        System.out.println("EMPIEZA ROUND ROBIN");
        System.out.println("-------------------");

        while (terminados < procesos.length) {

            // Añadir a la cola los procesos que ya han sido creados
            for (Proceso p : procesos) {
                if (p.tiempoCreacion <= tiempoActual
                        && p.tiempoRestante > 0
                        && !cola.contains(p)) {
                    cola.add(p);
                }
            }

            // Si no hay procesos disponibles, avanzamos el tiempo
            if (cola.isEmpty()) {
                tiempoActual++;
                continue;
            }

            // Sacamos el primer proceso de la cola
            Proceso proceso = cola.poll();

            int inicio = tiempoActual;
            int tiempoEjecutado = Math.min(quantum, proceso.tiempoRestante);

            // Ejecución
            proceso.tiempoRestante -= tiempoEjecutado;
            tiempoActual += tiempoEjecutado;

            System.out.println(inicio + " - " + tiempoActual + " -> " + proceso.nombre);

            // 1. Llegadas DURANTE la ejecución (estrictamente antes del final): entran antes
            for (Proceso p : procesos) {
                if (p != proceso
                        && p.tiempoCreacion < tiempoActual
                        && p.tiempoRestante > 0
                        && !cola.contains(p)) {
                    cola.add(p);
                }
            }

            // 2. El proceso que acaba de ejecutar: termina o vuelve a la cola
            if (proceso.tiempoRestante == 0) {
                proceso.tiempoFin = tiempoActual;
                terminados++;
                System.out.println(proceso.nombre + " termina en t = " + tiempoActual);
            } else {
                cola.add(proceso);
            }

            // 3. Llegadas JUSTO en el instante en que acaba el quantum: entran después
            for (Proceso p : procesos) {
                if (p.tiempoCreacion == tiempoActual
                        && p.tiempoRestante > 0
                        && !cola.contains(p)) {
                    cola.add(p);
                }
            }
        }

        System.out.println("-------------------");
        System.out.println("TODOS LOS PROCESOS HAN TERMINADO");
        System.out.println();

        // Tiempos de retorno y espera
        for (Proceso p : procesos) {
            int retorno = p.tiempoFin - p.tiempoCreacion;
            int espera = retorno - p.tiempoCPU;
            System.out.println(p.nombre + ": fin=" + p.tiempoFin
                    + ", retorno=" + retorno + ", espera=" + espera);
        }
    }
}