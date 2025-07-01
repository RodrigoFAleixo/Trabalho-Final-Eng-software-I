package dao;


import conn.ConnectionDB;
import dto.StockerDTO;
import java.sql.*;
import java.util.*;

/**
 * StockerDAO
 *
 * CRUD + consultas overall para a tabela repositor.
 */
public class StockerDAO {

    // CREATE
    public void inserir(StockerDTO r) {
        String sql = "INSERT INTO repositor(cpf,\"name\",wage,sector) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getCpf());
            ps.setString(2, r.getNome());
            ps.setDouble(3, r.getSalario());
            ps.setString(4, r.getSetor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Repositor", e);
        }
    }

    // READ by CPF
    public StockerDTO buscarPorCpf(String cpf) {
        String sql = "SELECT cpf,\"name\",wage,sector FROM repositor WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new StockerDTO(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getDouble("wage"),
                        rs.getString("sector")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Repositor", e);
        }
    }

    // READ all
    public List<StockerDTO> listarTodos() {
        String sql = "SELECT cpf,\"name\",wage,sector FROM repositor";
        List<StockerDTO> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new StockerDTO(
                    rs.getString("cpf"),
                    rs.getString("name"),
                    rs.getDouble("wage"),
                    rs.getString("sector")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Repositores", e);
        }
    }

    // UPDATE
    public void atualizar(StockerDTO r) {
        String sql = "UPDATE repositor SET \"name\" = ?, salario = ?, setor = ? WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getNome());
            ps.setDouble(2, r.getSalario());
            ps.setString(3, r.getSetor());
            ps.setString(4, r.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Repositor", e);
        }
    }

    // DELETE
    public void deletar(String cpf) {
        String sql = "DELETE FROM stocker WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Repositor", e);
        }
    }

    // overall: total de salários pagos a repositores
    public double totalSalario() {
        String sql = "SELECT SUM(wage) AS total FROM stocker";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next() ? rs.getDouble("total") : 0.0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular total de salários (repositor)", e);
        }
    }

    // overall: média de salário por setor
    public Map<String, Double> mediaSalarioPorSetor() {
        String sql = "SELECT sector, AVG(wage) AS media FROM stocker GROUP BY sector";
        Map<String, Double> map = new HashMap<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("setor"), rs.getDouble("media"));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular média de salários (repositor)", e);
        }
    }
}

