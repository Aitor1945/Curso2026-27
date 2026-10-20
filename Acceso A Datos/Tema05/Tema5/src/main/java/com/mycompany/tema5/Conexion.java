/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema5;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {

    public static Connection conectar() throws SQLException {
        Properties env = new Properties();

        try (FileInputStream in = new FileInputStream(".env")) {
            env.load(in);
        } catch (IOException e) {
            throw new SQLException("No se pudo leer el archivo .env: " + e.getMessage());
        }

        String url = env.getProperty("DB_URL", "jdbc:postgresql://localhost:5432/reservas");
        String user = env.getProperty("DB_USER", "alumno");
        String pass = env.getProperty("DB_PASSWORD");

        if (pass == null || pass.isBlank()) {
            throw new SQLException("Falta DB_PASSWORD en el archivo .env.");
        }

        return DriverManager.getConnection(url, user, pass);
    }
}