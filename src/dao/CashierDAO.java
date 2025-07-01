package dao;

import dto.CashierDTO;
import conn.ConnectionDB;
import java.sql.*;
import java.util.*;

public class CashierDAO {

    public void inserir(CashierDTO c) {
        String sql = "INSERT INTO cashier(cpf,\"name\",wage,sector) VALUES(?,?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCpf());
            ps.setString(2, c.getNome());
            ps.setDouble(3, c.getSalario());
            ps.setString(4, c.getSetor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Caixa", e);
        }
    }

    public CashierDTO buscarPorCpf(String cpf) {
        String sql = "SELECT cpf,\"name\",wage,sector FROM cashier WHERE cpf=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CashierDTO(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getDouble("wage"),
                        rs.getString("sector")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Caixa", e);
        }
    }

    public List<CashierDTO> listarTodos() {
        String sql = "SELECT cpf,\"name\",wage,sector FROM cashier";
        List<CashierDTO> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new CashierDTO(
                    rs.getString("cpf"),
                    rs.getString("name"),
                    rs.getDouble("wage"),
                    rs.getString("sector")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Caixas", e);
        }
    }

    public void atualizar(CashierDTO c) {
        String sql = "UPDATE cashier SET \"name\"=?, wage=?, sector=? WHERE cpf=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getSalario());
            ps.setString(3, c.getSetor());
            ps.setString(4, c.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Caixa", e);
        }
    }

    public void deletar(String cpf) {
        String sql = "DELETE FROM cashier WHERE cpf=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Caixa", e);
        }
    }

    // overall: total de salários pagos a caixas
    public double totalSalario() {
        String sql = "SELECT SUM(wage) AS total FROM cashier";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble("total") : 0.0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular total de salários", e);
        }
    }

    // Média de salário por setor
    public Map<String, Double> mediaSalarioPorSetor() {
        String sql = "SELECT setor, AVG(wage) AS media FROM cashier GROUP BY sector";
        Map<String, Double> map = new HashMap<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                map.put(rs.getString("setor"), rs.getDouble("media"));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular média de salários", e);
        }
    }
}

