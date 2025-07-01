import dao.StockerDAO;
import dto.StockerDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class RepositoresGUI extends JFrame {
    private StockerDAO stockerDAO = new StockerDAO();

    public RepositoresGUI(JFrame parent) {
        setTitle("Gerenciar Repositores");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnInserir = new JButton("Inserir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");
        JButton btnVoltar = new JButton("Voltar");

        add(btnInserir);
        add(btnBuscar);
        add(btnListar);
        add(btnAtualizar);
        add(btnDeletar);
        add(btnVoltar);

        btnInserir.addActionListener(e -> inserirRepositor());
        btnBuscar.addActionListener(e -> buscarRepositor());
        btnListar.addActionListener(e -> listarRepositores());
        btnAtualizar.addActionListener(e -> atualizarRepositor());
        btnDeletar.addActionListener(e -> deletarRepositor());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void inserirRepositor() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField setorField = new JTextField();
        Object[] fields = {"CPF:", cpfField, "Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Inserir Repositor", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double salario = Double.parseDouble(salarioField.getText());
                StockerDTO s = new StockerDTO(cpfField.getText(), nomeField.getText(), salario, setorField.getText());
                stockerDAO.inserir(s);
                JOptionPane.showMessageDialog(this, "Repositor inserido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        }
    }

    private void buscarRepositor() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do repositor:");
        if (cpf != null) {
            try {
                StockerDTO s = stockerDAO.buscarPorCpf(cpf);
                if (s != null) {
                    JOptionPane.showMessageDialog(this, "CPF: " + s.getCpf() + "\nNome: " + s.getNome() + "\nSalário: " + s.getSalario() + "\nSetor: " + s.getSetor());
                } else {
                    JOptionPane.showMessageDialog(this, "Repositor não encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void listarRepositores() {
        try {
            List<StockerDTO> lista = stockerDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (StockerDTO s : lista) {
                sb.append("CPF: ").append(s.getCpf()).append(", Nome: ").append(s.getNome()).append(", Salário: ").append(s.getSalario()).append(", Setor: ").append(s.getSetor()).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Repositores", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizarRepositor() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do repositor a atualizar:");
        if (cpf != null) {
            try {
                StockerDTO s = stockerDAO.buscarPorCpf(cpf);
                if (s == null) {
                    JOptionPane.showMessageDialog(this, "Repositor não encontrado.");
                    return;
                }
                JTextField nomeField = new JTextField(s.getNome());
                JTextField salarioField = new JTextField(String.valueOf(s.getSalario()));
                JTextField setorField = new JTextField(s.getSetor());
                Object[] fields = {"Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
                int option = JOptionPane.showConfirmDialog(this, fields, "Atualizar Repositor", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    s.setNome(nomeField.getText());
                    s.setSalario(Double.parseDouble(salarioField.getText()));
                    s.setSetor(setorField.getText());
                    stockerDAO.atualizar(s);
                    JOptionPane.showMessageDialog(this, "Repositor atualizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void deletarRepositor() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do repositor a deletar:");
        if (cpf != null) {
            try {
                stockerDAO.deletar(cpf);
                JOptionPane.showMessageDialog(this, "Repositor deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }
} 