package dao;


import dto.ManagerDTO;
import conn.ConnectionDB;
import java.sql.*;
import java.util.*;

/**
 * GerenteDAO
 *
 * CRUD + consultas “overall” para a tabela gerente.
 */
public class ManagerDAO {

    // CREATE
    public void inserir(ManagerDTO g) {
        String sql = "INSERT INTO maanger(cpf,\"name\",wage,setor) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, g.getCpf());
            ps.setString(2, g.getNome());
            ps.setDouble(3, g.getSalario());
            ps.setString(4, g.getSetor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Gerente", e);
        }
    }

    // READ by CPF
    public ManagerDTO buscarPorCpf(String cpf) {
        String sql = "SELECT cpf,\"name\",wage,sector FROM manager WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ManagerDTO(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getDouble("wage"),
                        rs.getString("sector")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Gerente", e);
        }
    }

    // READ all
    public List<ManagerDTO> listarTodos() {
        String sql = "SELECT cpf,\"name\",wage,sector FROM gerente";
        List<ManagerDTO> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ManagerDTO(
                    rs.getString("cpf"),
                    rs.getString("name"),
                    rs.getDouble("wage"),
                    rs.getString("sector")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Gerentes", e);
        }
    }

    // UPDATE
    public void atualizar(ManagerDTO g) {
        String sql = "UPDATE manager SET name = ?, wage = ?, sector = ? WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, g.getNome());
            ps.setDouble(2, g.getSalario());
            ps.setString(3, g.getSetor());
            ps.setString(4, g.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Gerente", e);
        }
    }

    // DELETE
    public void deletar(String cpf) {
        String sql = "DELETE FROM manager WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Gerente", e);
        }
    }

    // overall: total de salários pagos a gerentes
    public double totalSalario() {
        String sql = "SELECT SUM(wage) AS total FROM manager";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next() ? rs.getDouble("total") : 0.0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular total de salários (gerente)", e);
        }
    }

    // overall: média de salário por setor
    public Map<String, Double> mediaSalarioPorSetor() {
        String sql = "SELECT sector, AVG(wage) AS media FROM manager GROUP BY sector";
        Map<String, Double> map = new HashMap<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("sector"), rs.getDouble("media"));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular média de salários (gerente)", e);
        }
    }
}

