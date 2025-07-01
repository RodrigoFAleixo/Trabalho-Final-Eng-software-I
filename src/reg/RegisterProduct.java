package reg;

import conn.ConnectionDB;
import dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterProduct {

    /**
     * Insere um novo produto. Após o insert, o ID gerado é atribuído ao DTO.
     */
    public void inserir(ProductDTO p) {
        String sql = "INSERT INTO product(\"name\", price, quantity) VALUES (?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir Produto", e);
        }
    }

    /**
     * Busca um produto pelo ID. Retorna null se não encontrar.
     */
    public ProductDTO buscarPorId(int id) {
        String sql = "SELECT id, \"name\", price, quantity FROM product WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ProductDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar Produto por ID", e);
        }
    }

    /**
     * Lista todos os produtos cadastrados.
     */
    public List<ProductDTO> listarTodos() {
        String sql = "SELECT id, \"name\", price, quantity FROM product";
        List<ProductDTO> lista = new ArrayList<>();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new ProductDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar Produtos", e);
        }
    }

    /**
     * Atualiza os dados de um produto existente.
     */
    public void atualizar(ProductDTO p) {
        String sql = "UPDATE product SET \"name\" = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Produto", e);
        }
    }

    /**
     * Remove um produto pelo ID.
     */
    public void deletar(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar Produto", e);
        }
    }

    /**
     * Conta quantos produtos estão cadastrados.
     */
    public int contarProdutos() {
        String sql = "SELECT COUNT(*) AS total FROM product";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar Produtos", e);
        }
    }
}

