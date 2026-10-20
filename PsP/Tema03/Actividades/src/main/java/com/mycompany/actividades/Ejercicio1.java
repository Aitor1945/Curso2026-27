/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Usuario
 */
public class Ejercicio1 {

    static String info = "";

    public static void main(String[] args) {

        // HILO 1: lanza el contenedor y guarda la salida de cat /etc/os-release
        Thread hilo1 = new Thread(() -> {
            ProcessBuilder pb = new ProcessBuilder(
                    "docker", "run", "--rm", "ubuntu:24.04", "cat", "/etc/os-release");
            try {
                Process proceso = pb.start();

                BufferedReader lector = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    sb.append(linea).append("\n");
                }

                int codigo = proceso.waitFor();
                System.out.println("Hilo 1: contenedor terminado con código " + codigo);
                info = sb.toString();

            } catch (IOException | InterruptedException e) {
                System.out.println("Error en hilo 1: " + e.getMessage());
            }
        });

        // HILO 2: espera al hilo 1 y crea info.txt con la información
        Thread hilo2 = new Thread(() -> {
            try {
                hilo1.join();
                Files.writeString(Path.of("info.txt"), info);
                System.out.println("Hilo 2: info.txt creado");
            } catch (IOException | InterruptedException e) {
                System.out.println("Error en hilo 2: " + e.getMessage());
            }
        });

        hilo1.start();
        hilo2.start();

        // MAIN: espera al hilo 2, lee info.txt y lo muestra
        try {
            hilo2.join();
            String contenido = Files.readString(Path.of("info.txt"));
            System.out.println(contenido);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error en main: " + e.getMessage());
        }
    }
}