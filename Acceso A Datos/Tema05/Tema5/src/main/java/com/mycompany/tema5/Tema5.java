package com.mycompany.tema5;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Tema5 {

    // ====================== MAIN ======================

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                mostrarMenu();
                opcion = leerEntero(sc, "Selecciona una opción: ");

                switch (opcion) {
                    case 1 -> registrarReserva(sc);
                    case 2 -> mostrarTodas();
                    case 3 -> buscarPorAula(sc);
                    case 4 -> eliminarReserva(sc);
                    case 0 -> System.out.println("Hasta pronto. Los datos quedan guardados en PostgreSQL.");
                    default -> System.out.println("Opción no válida. Elige un número del menú.");
                }

                System.out.println();
            } while (opcion != 0);
        }
    }

    private static void mostrarMenu() {
        System.out.println("=================================");
        System.out.println("     GESTOR DE RESERVAS");
        System.out.println("=================================");
        System.out.println();
        System.out.println("1. Registrar una reserva");
        System.out.println("2. Mostrar todas las reservas");
        System.out.println("3. Buscar reservas por aula");
        System.out.println("4. Eliminar una reserva");
        System.out.println("0. Salir");
        System.out.println();
    }

    

    private static void registrarReserva(Scanner sc) {
        String alumno = leerTexto(sc, "Nombre del alumno: ", 100);
        String aula = leerTexto(sc, "Aula: ", 20);
        LocalDate fecha = leerFecha(sc, "Fecha de la reserva (AAAA-MM-DD): ");

        String sql = "INSERT INTO reserva (alumno, aula, fecha) VALUES (?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, alumno);
            ps.setString(2, aula);
            ps.setDate(3, Date.valueOf(fecha));

            if (ps.executeUpdate() > 0) {
                System.out.println("Reserva registrada correctamente.");
            } else {
                System.out.println("No se pudo registrar la reserva.");
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar la reserva: " + e.getMessage());
        }
    }

    private static void mostrarTodas() {
        String sql = "SELECT id, alumno, aula, fecha FROM reserva ORDER BY fecha, id";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

 

        } catch (SQLException e) {
            System.out.println("Error al consultar las reservas: " + e.getMessage());
        }
    }

}