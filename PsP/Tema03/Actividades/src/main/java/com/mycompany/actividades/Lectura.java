package com.mycompany.actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lectura {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("docker", "run", "--rm","--memory=128m","--cpus=0.5", "alpine", "echo", "Hola MundoSOS");

        try {
            Process proceso = pb.start();

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));

            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            int codigo = proceso.waitFor();
            System.out.println("Código de salida: " + codigo);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}