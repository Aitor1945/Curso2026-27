

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;

/**
 *
 * @author Jesús Guillén Encinas
 */
public class Actividad2 {

   public static void main(String[] args) throws IOException {

        /*
         * ============================================================
         * TEMA 1 - INTRODUCCIÓN AL MANEJO DE FICHEROS
         * ACCESO A DATOS - EJERCICIOS
         * ============================================================
         *
         * INSTRUCCIONES:
         * - Completa cada apartado donde aparece TODO.
         * - No borres los enunciados.
         * - Puedes crear variables y código auxiliar dentro de cada apartado.
         *
         * CONTENIDOS:
         * 1. Clase File
         * 2. Información de un fichero
         * 3. Crear directorios y mover ficheros
         * 4. FileWriter
         * 5. FileReader
         * 6. Acceso secuencial o aleatorio
         * 7. FileInputStream / FileOutputStream
         * 8. RandomAccessFile
         * 9. Ejercicio Final
         */

        // ============================================================
        // EJERCICIO 1 - CREAR UN FICHERO
        // ============================================================

        /*
         * Crea un programa que:
         *
         * 1. Cree un objeto File asociado al fichero "datos.txt".
         * 2. Compruebe si el fichero existe.
         * 3. Si no existe, créalo.
         * 4. Muestra por pantalla el nombre del fichero.
         */

        // TODO: EJERCICIO 1
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 1 =====");
        File datos = new File("datos.txt");

        if (!datos.exists()) {
            datos.createNewFile();
            System.out.println("Se ha creado el fichero.");
        } else {
            System.out.println("El fichero ya existe.");
        }

        System.out.println("Nombre del fichero: " + datos.getName());

        // ============================================================
        // EJERCICIO 2 - INFORMACIÓN DEL FICHERO
        // ============================================================

        /*
         * Utilizando un objeto File asociado a "datos.txt",
         * muestra por pantalla:
         *
         * - Nombre del fichero.
         * - Ruta.
         * - Ruta absoluta.
         * - Directorio padre.
         * - Si existe.
         * - Si se puede leer.
         * - Si se puede escribir.
         */

        // TODO: EJERCICIO 2
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 2 =====");

        File datos2 = new File("datos.txt");

        System.out.println("Nombre: " + datos2.getName());
        System.out.println("Ruta: " + datos2.getPath());
        System.out.println("Ruta absoluta: " + datos2.getAbsolutePath());
        System.out.println("Directorio padre: " + datos2.getParent());
        System.out.println("Existe: " + datos2.exists());
        System.out.println("Se puede leer: " + datos2.canRead());
        System.out.println("Se puede escribir: " + datos2.canWrite());

        // ============================================================
        // EJERCICIO 3 - CREAR DIRECTORIO Y MOVER UN FICHERO
        // ============================================================

        /*
         * Tenemos un fichero llamado "datos.txt".
         *
         * El programa debe:
         *
         * 1. Crear un directorio llamado "backup".
         * 2. Comprobar que el directorio existe.
         * 3. Mover "datos.txt" dentro de "backup".
         *
         * Resultado final:
         *
         * backup/
         *     datos.txt
         */

        // TODO: EJERCICIO 3
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 3 =====");

        File backup = new File("backup");

        if (!backup.exists()) {
            backup.mkdir();
        }

        System.out.println("El directorio backup existe: " + backup.exists());

        File origen = new File("datos.txt");
        File destino = new File(backup, "datos.txt");

        if (origen.exists()) {
            if (origen.renameTo(destino)) {
                System.out.println("datos.txt se ha movido a backup.");
            } else {
                System.out.println("No se ha podido mover el fichero.");
            }
        } else {
            System.out.println("No existe datos.txt para mover.");
        }

        // ============================================================
        // EJERCICIO 4 - ESCRIBIR TEXTO CON FileWriter
        // ============================================================

        /*
         * Crea un fichero llamado "alumnos.txt" y escribe dentro:
         *
         * Juan
         * María
         * Pedro
         * Ana
         *
         * Debes utilizar FileWriter.
         *
         * Recuerda:
         * - Instanciar FileWriter.
         * - Utilizar write().
         * - Cerrar el flujo.
         */

        // TODO: EJERCICIO 4
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 4 =====");

        FileWriter escritor = new FileWriter("alumnos.txt");

        escritor.write("Juan\n");
        escritor.write("María\n");
        escritor.write("Pedro\n");
        escritor.write("Ana\n");

        escritor.close();

        System.out.println("Se ha escrito alumnos.txt correctamente.");


        // ============================================================
        // EJERCICIO 5 - LEER UN CARÁCTER CON FileReader
        // ============================================================

        /*
         * Crea previamente un fichero llamado "mensaje.txt" que contenga:
         *
         * Hola
         *
         * Utiliza FileReader para:
         *
         * 1. Abrir el fichero.
         * 2. Leer el primer carácter mediante read().
         * 3. Mostrarlo por pantalla.
         * 4. Cerrar el lector.
         *
         * Resultado esperado:
         * H
         */

        // TODO: EJERCICIO 5
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 5 =====");

        // Creamos previamente el fichero
        FileWriter mensajeEscritor = new FileWriter("mensaje.txt");
        mensajeEscritor.write("Hola");
        mensajeEscritor.close();

        FileReader lector = new FileReader("mensaje.txt");

        int caracter = lector.read();

        System.out.println("Primer carácter: " + (char) caracter);

        lector.close();


        // ============================================================
        // EJERCICIO 6 - ¿ACCESO SECUENCIAL O ALEATORIO?
        // ============================================================

        /*
         * Para cada situación indica si utilizarías:
         *
         *     ACCESO SECUENCIAL
         *     o
         *     ACCESO ALEATORIO / DIRECTO
         *
         * y explica brevemente por qué.
         *
         * A) Tenemos un fichero con 500 nombres y queremos mostrar
         *    todos los nombres desde el primero hasta el último.
         *
         * B) Tenemos un fichero enorme y queremos acceder directamente
         *    a una posición concreta.
         *
         * C) Queremos recorrer un fichero de texto completo y copiar
         *    su contenido a otro fichero.
         *
         * D) Queremos modificar información situada en una posición
         *    concreta del fichero.
         *
         * E) Queremos leer un fichero de texto completo.
         *
         * Recuerda:
         *
         * - Secuencial: recorremos la información en orden.
         * - Aleatorio/directo: podemos posicionarnos directamente
         *   en una posición concreta.
         */

        // TODO: EJERCICIO 6
        // Escribe tus respuestas aquí mediante comentarios.
        
        System.out.println("\n===== EJERCICIO 6 =====");

        /*
         * A) ACCESO SECUENCIAL
         * Porque queremos recorrer todos los nombres desde el primero
         * hasta el último.
         *
         * B) ACCESO ALEATORIO / DIRECTO
         * Porque queremos acceder directamente a una posición concreta.
         *
         * C) ACCESO SECUENCIAL
         * Porque recorremos todo el fichero en orden para copiarlo.
         *
         * D) ACCESO ALEATORIO / DIRECTO
         * Porque queremos modificar una posición concreta.
         *
         * E) ACCESO SECUENCIAL
         * Porque queremos leer todo el fichero de principio a fin.
         */

        System.out.println("A) Acceso secuencial.");
        System.out.println("B) Acceso aleatorio/directo.");
        System.out.println("C) Acceso secuencial.");
        System.out.println("D) Acceso aleatorio/directo.");
        System.out.println("E) Acceso secuencial.");


        // ============================================================
        // EJERCICIO 7 - LECTURA DE BYTES
        // ============================================================

        /*
         * Crea un fichero llamado "bytes.txt" que contenga algún texto.
         *
         * Utiliza FileInputStream para:
         *
         * 1. Abrir el fichero.
         * 2. Leer un byte utilizando read().
         * 3. Mostrar por pantalla el valor obtenido.
         * 4. Cerrar el flujo.
         *
         */

        // TODO: EJERCICIO 7
        // Escribe aquí tu solución.
        
        System.out.println("\n===== EJERCICIO 7 =====");

        // Creamos el fichero
        FileOutputStream crearBytes = new FileOutputStream("bytes.txt");
        crearBytes.write("Hola mundo".getBytes());
        crearBytes.close();

        FileInputStream entrada = new FileInputStream("bytes.txt");

        int byteLeido = entrada.read();

        System.out.println("Valor del primer byte: " + byteLeido);

        entrada.close();


        // ============================================================
        // EJERCICIO 8 - ESCRITURA DE BYTES
        // ============================================================

        /*
         * Utiliza FileOutputStream para crear/escribir el fichero:
         *
         * "salidaBytes.txt"
         *
         * Escribe mediante write() el valor 68.
         *
         * Recuerda que 68 es la letra 'D' en ASCII.
         *
         * Después cierra el flujo.
         */

        // TODO: EJERCICIO 8
        // Escribe aquí tu solución.
        
        System.out.println("\n===== EJERCICIO 8 =====");

        FileOutputStream salida = new FileOutputStream("salidaBytes.txt");

        salida.write(68);

        salida.close();

        System.out.println("Se ha escrito el valor 68 en salidaBytes.txt.");


        // ============================================================
        // EJERCICIO 9 - RandomAccessFile
        // ============================================================

        /*
         * Crea un fichero llamado "letras.txt" que contenga:
         *
         * ABCDEFGHIJ
         *
         * Utiliza RandomAccessFile para:
         *
         * 1. Abrir el fichero en modo "rw".
         * 2. Situarte en la posición 5 mediante seek().
         * 3. Leer el carácter que hay en esa posición.
         * 4. Mostrarlo por pantalla.
         * 5. Mostrar también la posición actual del puntero.
         *
         * Resultado esperado:
         *
         * F 6
         *
         */

        // TODO: EJERCICIO 9
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 9 =====");

        // Creamos el fichero letras.txt
        FileWriter letrasEscritor = new FileWriter("letras.txt");
        letrasEscritor.write("ABCDEFGHIJ");
        letrasEscritor.close();

        RandomAccessFile letras = new RandomAccessFile("letras.txt", "rw");

        letras.seek(5);

        int letra = letras.read();

        System.out.println("Carácter: " + (char) letra);
        System.out.println("Posición actual: " + letras.getFilePointer());

        letras.close();
        
        


        // ============================================================
        // EJERCICIO 10 - ESCRIBIR CON RandomAccessFile
        // ============================================================

        /*
         * Utiliza el fichero "letras.txt" del ejercicio anterior.
         *
         * 1. Abre el fichero con RandomAccessFile en modo "rw".
         * 2. Coloca el puntero en la posición 5.
         * 3. Escribe el byte correspondiente a la letra 'D'
         *    utilizando write().
         * 4. Cierra el fichero.
         *
         * Comprueba después cómo ha quedado el contenido del fichero.
         *
         */

        // TODO: EJERCICIO 10
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 10 =====");

        RandomAccessFile letras2 = new RandomAccessFile("letras.txt", "rw");

        letras2.seek(5);

        letras2.write('D');

        letras2.close();

        System.out.println("Se ha escrito la letra D en la posición 5.");
        
        
        

        // ============================================================
        // EJERCICIO 11 - LEER VARIOS BYTES
        // ============================================================

        /*
         * Utiliza RandomAccessFile con un fichero que contenga texto.
         *
         * Crea un array de bytes de tamaño 10.
         *
         * Utiliza read(byte[], inicio, cantidad) para leer varios
         * bytes del fichero y almacenarlos en el array.
         *
         * Finalmente muestra cuántos bytes se han leído.
         *
         */

        // TODO: EJERCICIO 11
        // Escribe aquí tu solución.
        System.out.println("\n===== EJERCICIO 11 =====");

        RandomAccessFile variosBytes = new RandomAccessFile("bytes.txt", "r");

        byte[] array = new byte[10];

        int cantidadLeida = variosBytes.read(array, 0, 10);

        System.out.println("Bytes leídos: " + cantidadLeida);

        variosBytes.close();

        // ============================================================
        // EJERCICIO 12 - Ejercicio FINAL: GESTOR DE FICHEROS
        // ============================================================

        /*
         * Crea un pequeño gestor de ficheros.
         *
         * El programa debe gestionar un fichero llamado:
         *
         * alumnos.txt
         *
         * Debe realizar las siguientes operaciones:
         *
         * 1. Crear el fichero si no existe.
         *
         * 2. Mostrar:
         *    - Nombre.
         *    - Ruta.
         *    - Ruta absoluta.
         *    - Si existe.
         *    - Si se puede leer.
         *    - Si se puede escribir.
         *
         * 3. Escribir en el fichero:
         *
         *    Juan
         *    Ana
         *    Pedro
         *    Laura
         *
         *    Utiliza FileWriter.
         *
         * 4. Utilizar FileReader para leer el primer carácter
         *    y mostrarlo por pantalla.
         *
         * 5. Crear un directorio llamado "backup".
         *
         * 6. Mover el fichero a:
         *
         *    backup/alumnos.txt
         *
         * 7. Mostrar la fecha de última modificación utilizando
         *    lastModified().
         *
         * IMPORTANTE:
         * Recuerda cerrar los flujos de lectura y escritura
         * utilizando close().
         *
         */

        // TODO: Ejercicio FINAL
        // Escribe aquí tu solución completa.
        System.out.println("\n===== EJERCICIO FINAL =====");

        File alumnos = new File("alumnos.txt");

        // 1. Crear el fichero si no existe
        if (!alumnos.exists()) {
            alumnos.createNewFile();
        }

        // 2. Mostrar información
        System.out.println("Nombre: " + alumnos.getName());
        System.out.println("Ruta: " + alumnos.getPath());
        System.out.println("Ruta absoluta: " + alumnos.getAbsolutePath());
        System.out.println("Existe: " + alumnos.exists());
        System.out.println("Se puede leer: " + alumnos.canRead());
        System.out.println("Se puede escribir: " + alumnos.canWrite());

        // 3. Escribir alumnos
        FileWriter alumnosWriter = new FileWriter(alumnos);

        alumnosWriter.write("Juan\n");
        alumnosWriter.write("Ana\n");
        alumnosWriter.write("Pedro\n");
        alumnosWriter.write("Laura\n");

        alumnosWriter.close();

        System.out.println("Se ha escrito el contenido de alumnos.txt.");

        // 4. Leer el primer carácter
        FileReader alumnosReader = new FileReader(alumnos);

        int primerCaracter = alumnosReader.read();

        System.out.println("Primer carácter: " + (char) primerCaracter);

        alumnosReader.close();
        
        // 5. Crear directorio backup
        File backupFinal = new File("backup");

        if (!backupFinal.exists()) {
            backupFinal.mkdir();
        }

        // 6. Mover alumnos.txt a backup
        File alumnosDestino = new File(backupFinal, "alumnos.txt");

        if (alumnos.renameTo(alumnosDestino)) {
            System.out.println("alumnos.txt se ha movido a backup.");
        } else {
            System.out.println("No se ha podido mover alumnos.txt.");
        }

        // 7. Mostrar fecha de última modificación
        System.out.println(
                "Última modificación: " + alumnosDestino.lastModified()
        );

        System.out.println("\n===== PROGRAMA TERMINADO =====");


    }
}
