package com.mycompany.tema4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tema4 {

    private static final String url = "jdbc:mysql://localhost:3307/accesodatos";
    private static final String user = "aitor";
    private static final String pass = "mipass123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static void listarEmpleados() {
        String sql = "SELECT * FROM empleados";

        try {
            Connection conexion = conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("ID - NOMBRE - DEPARTAMENTO - SALARIO - EDAD");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String departamento = rs.getString("departamento");
                double salario = rs.getDouble("salario");
                int edad = rs.getInt("edad");
                System.out.println(id + " - " + nombre + " - " + departamento
                        + " - " + salario + " - " + edad);
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarPorId(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";

        try {
            Connection conexion = conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String departamento = rs.getString("departamento");
                double salario = rs.getDouble("salario");
                int edad = rs.getInt("edad");
                System.out.println(id + " - " + nombre + " - " + departamento
                        + " - " + salario + " - " + edad);
            } else {
                System.out.println("No existe ningún empleado con id " + id);
            }

            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void actualizarSalario(int id, double salario) {
    String sql = "UPDATE empleados SET salario = ? WHERE id = ?";

    try {
        Connection conexion = conectar();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setDouble(1, salario);
        ps.setInt(2, id);

        ps.executeUpdate();
        System.out.println("Salario actualizado");

        ps.close();
        conexion.close();

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        System.out.println("Listar Todos los empleados");
        listarEmpleados();

        System.out.println("Buscar empleado con id 2");
        buscarPorId(2);
        
        System.out.println("Actualizar Salirio del id 3");
        actualizarSalario(3,50000);
        buscarPorId(3);

    }
}