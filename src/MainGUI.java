import java.awt.*;
import javax.swing.*;

public class MainGUI extends JFrame {
    public MainGUI() {
        setTitle("Supermercado - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JLabel label = new JLabel("=== MENU PRINCIPAL ===", SwingConstants.CENTER);
        add(label);

        JButton btnClientes = new JButton("Clientes");
        JButton btnCaixas = new JButton("Caixas");
        JButton btnGerentes = new JButton("Gerentes");
        JButton btnRepositores = new JButton("Repositores");
        JButton btnSair = new JButton("Sair");

        add(btnClientes);
        add(btnCaixas);
        add(btnGerentes);
        add(btnRepositores);
        add(btnSair);

        btnClientes.addActionListener(e -> abrirClientes());
        btnCaixas.addActionListener(e -> abrirCaixas());
        btnGerentes.addActionListener(e -> abrirGerentes());
        btnRepositores.addActionListener(e -> abrirRepositores());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void abrirClientes() {
        new ClientesGUI(this).setVisible(true);
    }
    private void abrirCaixas() {
        new CaixasGUI(this).setVisible(true);
    }
    private void abrirGerentes() {
        new GerentesGUI(this).setVisible(true);
    }
    private void abrirRepositores() {
        new RepositoresGUI(this).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
} 