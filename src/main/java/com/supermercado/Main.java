package com.supermercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PostgresConnection
 *
 * Responsável por fornecer conexões JDBC para um banco PostgreSQL.
 */
public class Main {

    // URL de conexão: jdbc:postgresql://<host>:<porta>/<database>
    private static final String URL      = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER     = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    // Bloco estático: carrega o driver apenas uma vez ao iniciar a classe
    static {
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

    /**
     * Retorna uma nova Connection aberta. 
     * Quem chama é responsável por fechar a conexão (conn.close()).
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Exemplo de main para testar a conexão
    public static void main(String[] args) {
        try (Connection conn = Main.getConnection()) {
            if (!conn.isClosed()) {
                System.out.println("Conectado com sucesso ao PostgreSQL.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
