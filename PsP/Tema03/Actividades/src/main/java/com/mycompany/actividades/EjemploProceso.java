/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.actividades;

import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class EjemploProceso {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("notepad.exe");

        try {
            Process proceso = pb.start();
            ProcessHandle handle = proceso.toHandle();

            System.out.println("Proceso lanzado");
            System.out.println("PID: " + handle.pid());
            System.out.println("Esta vivo: " + handle.isAlive());

            Thread.sleep(3000);

            proceso.destroy();
            System.out.println("Esta vivo: " + handle.isAlive());

        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}