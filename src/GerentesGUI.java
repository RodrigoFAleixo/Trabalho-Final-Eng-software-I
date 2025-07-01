import dao.ManagerDAO;
import dto.ManagerDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class GerentesGUI extends JFrame {
    private ManagerDAO managerDAO = new ManagerDAO();

    public GerentesGUI(JFrame parent) {
        setTitle("Gerenciar Gerentes");
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

        btnInserir.addActionListener(e -> inserirGerente());
        btnBuscar.addActionListener(e -> buscarGerente());
        btnListar.addActionListener(e -> listarGerentes());
        btnAtualizar.addActionListener(e -> atualizarGerente());
        btnDeletar.addActionListener(e -> deletarGerente());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void inserirGerente() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField setorField = new JTextField();
        Object[] fields = {"CPF:", cpfField, "Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Inserir Gerente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double salario = Double.parseDouble(salarioField.getText());
                ManagerDTO m = new ManagerDTO(cpfField.getText(), nomeField.getText(), salario, setorField.getText());
                managerDAO.inserir(m);
                JOptionPane.showMessageDialog(this, "Gerente inserido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        }
    }

    private void buscarGerente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do gerente:");
        if (cpf != null) {
            try {
                ManagerDTO m = managerDAO.buscarPorCpf(cpf);
                if (m != null) {
                    JOptionPane.showMessageDialog(this, "CPF: " + m.getCpf() + "\nNome: " + m.getNome() + "\nSalário: " + m.getSalario() + "\nSetor: " + m.getSetor());
                } else {
                    JOptionPane.showMessageDialog(this, "Gerente não encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void listarGerentes() {
        try {
            List<ManagerDTO> lista = managerDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (ManagerDTO m : lista) {
                sb.append("CPF: ").append(m.getCpf()).append(", Nome: ").append(m.getNome()).append(", Salário: ").append(m.getSalario()).append(", Setor: ").append(m.getSetor()).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Gerentes", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizarGerente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do gerente a atualizar:");
        if (cpf != null) {
            try {
                ManagerDTO m = managerDAO.buscarPorCpf(cpf);
                if (m == null) {
                    JOptionPane.showMessageDialog(this, "Gerente não encontrado.");
                    return;
                }
                JTextField nomeField = new JTextField(m.getNome());
                JTextField salarioField = new JTextField(String.valueOf(m.getSalario()));
                JTextField setorField = new JTextField(m.getSetor());
                Object[] fields = {"Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
                int option = JOptionPane.showConfirmDialog(this, fields, "Atualizar Gerente", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    m.setNome(nomeField.getText());
                    m.setSalario(Double.parseDouble(salarioField.getText()));
                    m.setSetor(setorField.getText());
                    managerDAO.atualizar(m);
                    JOptionPane.showMessageDialog(this, "Gerente atualizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void deletarGerente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do gerente a deletar:");
        if (cpf != null) {
            try {
                managerDAO.deletar(cpf);
                JOptionPane.showMessageDialog(this, "Gerente deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }
} 