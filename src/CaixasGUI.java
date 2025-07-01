import dao.CashierDAO;
import dto.CashierDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class CaixasGUI extends JFrame {
    private CashierDAO cashierDAO = new CashierDAO();

    public CaixasGUI(JFrame parent) {
        setTitle("Gerenciar Caixas");
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

        btnInserir.addActionListener(e -> inserirCaixa());
        btnBuscar.addActionListener(e -> buscarCaixa());
        btnListar.addActionListener(e -> listarCaixas());
        btnAtualizar.addActionListener(e -> atualizarCaixa());
        btnDeletar.addActionListener(e -> deletarCaixa());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void inserirCaixa() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField salarioField = new JTextField();
        JTextField setorField = new JTextField();
        Object[] fields = {"CPF:", cpfField, "Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Inserir Caixa", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double salario = Double.parseDouble(salarioField.getText());
                CashierDTO c = new CashierDTO(cpfField.getText(), nomeField.getText(), salario, setorField.getText());
                cashierDAO.inserir(c);
                JOptionPane.showMessageDialog(this, "Caixa inserido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        }
    }

    private void buscarCaixa() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do caixa:");
        if (cpf != null) {
            try {
                CashierDTO c = cashierDAO.buscarPorCpf(cpf);
                if (c != null) {
                    JOptionPane.showMessageDialog(this, "CPF: " + c.getCpf() + "\nNome: " + c.getNome() + "\nSalário: " + c.getSalario() + "\nSetor: " + c.getSetor());
                } else {
                    JOptionPane.showMessageDialog(this, "Caixa não encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void listarCaixas() {
        try {
            List<CashierDTO> lista = cashierDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (CashierDTO c : lista) {
                sb.append("CPF: ").append(c.getCpf()).append(", Nome: ").append(c.getNome()).append(", Salário: ").append(c.getSalario()).append(", Setor: ").append(c.getSetor()).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Caixas", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizarCaixa() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do caixa a atualizar:");
        if (cpf != null) {
            try {
                CashierDTO c = cashierDAO.buscarPorCpf(cpf);
                if (c == null) {
                    JOptionPane.showMessageDialog(this, "Caixa não encontrado.");
                    return;
                }
                JTextField nomeField = new JTextField(c.getNome());
                JTextField salarioField = new JTextField(String.valueOf(c.getSalario()));
                JTextField setorField = new JTextField(c.getSetor());
                Object[] fields = {"Nome:", nomeField, "Salário:", salarioField, "Setor:", setorField};
                int option = JOptionPane.showConfirmDialog(this, fields, "Atualizar Caixa", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    c.setNome(nomeField.getText());
                    c.setSalario(Double.parseDouble(salarioField.getText()));
                    c.setSetor(setorField.getText());
                    cashierDAO.atualizar(c);
                    JOptionPane.showMessageDialog(this, "Caixa atualizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void deletarCaixa() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do caixa a deletar:");
        if (cpf != null) {
            try {
                cashierDAO.deletar(cpf);
                JOptionPane.showMessageDialog(this, "Caixa deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }
} 