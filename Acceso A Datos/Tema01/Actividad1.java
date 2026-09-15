/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Actividad1 {

    public static void main(String[] args) throws IOException {

        File f = new File("C:/Users/Usuario/Documents/Programacion/Acceso A Datos/Tema01/archivo.txt");

        if (!f.exists()) {
            f.createNewFile();
        }

        System.out.println("El archivo se llama: " + f.getName());
        System.out.println("El path es: " + f.getPath());
        System.out.println("¿Existe?: " + f.exists());
        System.out.println("¿Se puede editar?: " + f.canWrite());

        // FileInputStream - leer bytes
        FileInputStream e = new FileInputStream(f);

        System.out.println("Byte: " + e.read());

        e.close();

        // FileReader - leer caracteres
        FileReader lector = new FileReader(f);

        int dato = lector.read();

        System.out.println("Caracter: " + (char) dato);

        lector.close();

        // FileWriter - escribir caracteres
        FileWriter escritor = new FileWriter(f);

        escritor.write("Adios Mundo");

        escritor.close();

        // RandomAccessFile
        // Modos: "r" = solo lectura / "rw" = lectura y escritura
        RandomAccessFile buffer = new RandomAccessFile(f, "rw");

        // Puntero
        buffer.seek(6);

        System.out.println("Posición del puntero: " + buffer.getFilePointer());

        // Lectura
        System.out.println("Byte leído: " + buffer.read());

        // Escritura
        buffer.seek(6);
        buffer.write(65); // 65 = A

        // Leer varios bytes
        buffer.seek(0);

        byte[] datos = new byte[5];

        int cantidad = buffer.read(datos);

        for (int i = 0; i < cantidad; i++) {
            System.out.println((char) datos[i]);
        }

        buffer.close();
    }
}