
package dao;

import conn.ConnectionDB;
import dto.ClientDTO;

import java.sql.*;
import java.util.*;

public class ClientDAO {

    // CREATE
    public void inserir(ClientDTO c) {
        String sql = "INSERT INTO cliente(cpf,nome,email,setor) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCPF());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getSetor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Cliente", e);
        }
    }

    // READ by CPF
    public ClientDTO buscarPorCpf(String cpf) {
        String sql = "SELECT cpf,nome,email,setor FROM cliente WHERE cpf = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClientDTO(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("setor")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Cliente", e);
        }
    }

    // READ all
    public List<ClientDTO> listarTodos() {
        String sql = "SELECT cpf,nome,email,setor FROM cliente";
        List<ClientDTO> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ClientDTO(
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("setor")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Clientes", e);
        }
    }

    // UPDATE
    public void atualizar(ClientDTO c) {
        String sql = "UPDATE cliente SET nome=?, email=?, setor=? WHERE cpf=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getSetor());
            ps.setString(4, c.getCPF());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Cliente", e);
        }
    }

    // DELETE
    public void deletar(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf=?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Cliente", e);
        }
    }

    // overall: quantos clientes existem
    public int contarClientes() {
        String sql = "SELECT COUNT(*) AS total FROM cliente";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar Clientes", e);
        }
    }
}

