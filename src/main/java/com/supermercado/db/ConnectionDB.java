package com.supermercado.db;

import java.sql.*;

public class ConnectionDB {
    private static final String URL      = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER     = "root";
    private static final String PASSWORD = "12345678";

    static{
        try {
            // Para Java 6+ não é estritamente necessário, mas garante compatibilidade
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL carregado com sucesso.");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "Não foi possível carregar o driver PostgreSQL: " + e.getMessage()
            );
        }
    }

    public static ConnectionDB getConnection() throws SQLException {
        return (ConnectionDB) DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
