package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL      = "jdbc:postgresql://localhost:5432/supermercado";
    private static final String USER     = "postgres";        // troque para o usuário do seu PostgreSQL
    private static final String PASSWORD = "1234";    // e para a senha correta

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL carregado com sucesso.");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(
                "Não foi possível carregar o driver PostgreSQL: " + e.getMessage()
            );
        }
    }

    /**
     * Retorna um java.sql.Connection conectando ao seu BD.
     * Lembre-se de chamar conn.close() após o uso.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = ConnectionDB.getConnection()) {
            System.out.println("Conectado? " + (conn != null && !conn.isClosed()));
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            // Se for erro de autenticação, verifique:
            // 1) Usuário/senha estão criados no Postgres?
            // 2) Você não está usando 'root' por engano (o default é 'postgres').
        }
    }
}

