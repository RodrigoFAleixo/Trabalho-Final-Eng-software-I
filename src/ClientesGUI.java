import dao.ClientDAO;
import dto.ClientDTO;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ClientesGUI extends JFrame {
    private ClientDAO clientDAO = new ClientDAO();

    public ClientesGUI(JFrame parent) {
        setTitle("Gerenciar Clientes");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(8, 1, 10, 10));

        JButton btnInserir = new JButton("Inserir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnDeletar = new JButton("Deletar");
        JButton btnContar = new JButton("Contar");
        JButton btnVoltar = new JButton("Voltar");

        add(btnInserir);
        add(btnBuscar);
        add(btnListar);
        add(btnAtualizar);
        add(btnDeletar);
        add(btnContar);
        add(btnVoltar);

        btnInserir.addActionListener(e -> inserirCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
        btnListar.addActionListener(e -> listarClientes());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnDeletar.addActionListener(e -> deletarCliente());
        btnContar.addActionListener(e -> contarClientes());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void inserirCliente() {
        JTextField cpfField = new JTextField();
        JTextField nomeField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField setorField = new JTextField();
        Object[] fields = {"CPF:", cpfField, "Nome:", nomeField, "Email:", emailField, "Setor:", setorField};
        int option = JOptionPane.showConfirmDialog(this, fields, "Inserir Cliente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            ClientDTO c = new ClientDTO(cpfField.getText(), nomeField.getText(), emailField.getText(), setorField.getText());
            try {
                clientDAO.inserir(c);
                JOptionPane.showMessageDialog(this, "Cliente inserido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        }
    }

    private void buscarCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente:");
        if (cpf != null) {
            try {
                ClientDTO c = clientDAO.buscarPorCpf(cpf);
                if (c != null) {
                    JOptionPane.showMessageDialog(this, "CPF: " + c.getCPF() + "\nNome: " + c.getNome() + "\nEmail: " + c.getEmail() + "\nSetor: " + c.getSetor());
                } else {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void listarClientes() {
        try {
            List<ClientDTO> lista = clientDAO.listarTodos();
            StringBuilder sb = new StringBuilder();
            for (ClientDTO c : lista) {
                sb.append("CPF: ").append(c.getCPF()).append(", Nome: ").append(c.getNome()).append(", Email: ").append(c.getEmail()).append(", Setor: ").append(c.getSetor()).append("\n");
            }
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizarCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente a atualizar:");
        if (cpf != null) {
            try {
                ClientDTO c = clientDAO.buscarPorCpf(cpf);
                if (c == null) {
                    JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                    return;
                }
                JTextField nomeField = new JTextField(c.getNome());
                JTextField emailField = new JTextField(c.getEmail());
                JTextField setorField = new JTextField(c.getSetor());
                Object[] fields = {"Nome:", nomeField, "Email:", emailField, "Setor:", setorField};
                int option = JOptionPane.showConfirmDialog(this, fields, "Atualizar Cliente", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    c.setNome(nomeField.getText());
                    c.setEmail(emailField.getText());
                    c.setSetor(setorField.getText());
                    clientDAO.atualizar(c);
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void deletarCliente() {
        String cpf = JOptionPane.showInputDialog(this, "CPF do cliente a deletar:");
        if (cpf != null) {
            try {
                clientDAO.deletar(cpf);
                JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        }
    }

    private void contarClientes() {
        try {
            int total = clientDAO.contarClientes();
            JOptionPane.showMessageDialog(this, "Total de clientes: " + total);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
} 