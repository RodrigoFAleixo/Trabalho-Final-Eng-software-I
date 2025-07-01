import dao.*;
import dto.*;
import java.util.*;

public class MainCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static ClientDAO clientDAO = new ClientDAO();
    private static CashierDAO cashierDAO = new CashierDAO();
    private static ManagerDAO managerDAO = new ManagerDAO();
    private static StockerDAO stockerDAO = new StockerDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1) Clientes");
            System.out.println("2) Caixas");
            System.out.println("3) Gerentes");
            System.out.println("4) Repositores");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": menuClientes(); break;
                case "2": menuCaixas(); break;
                case "3": menuGerentes(); break;
                case "4": menuRepositores(); break;
                case "0": System.exit(0);
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // CLIENTES
    private static void menuClientes() {
        while (true) {
            System.out.println("-- Gerenciar Clientes --");
            System.out.println("1) Inserir  2) Buscar  3) Listar  4) Atualizar  5) Deletar  0) Voltar");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": inserirCliente(); break;
                case "2": buscarCliente(); break;
                case "3": listarClientes(); break;
                case "4": atualizarCliente(); break;
                case "5": deletarCliente(); break;
                case "0": return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    private static void inserirCliente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Setor: "); String setor = scanner.nextLine();
        try {
            clientDAO.inserir(new ClientDTO(cpf, nome, email, setor));
            System.out.println("Cliente inserido!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void buscarCliente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            ClientDTO c = clientDAO.buscarPorCpf(cpf);
            if (c != null) System.out.println(c);
            else System.out.println("Não encontrado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void listarClientes() {
        try {
            List<ClientDTO> lista = clientDAO.listarTodos();
            for (ClientDTO c : lista) System.out.println(c);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void atualizarCliente() {
        System.out.print("CPF do cliente a atualizar: "); String cpf = scanner.nextLine();
        try {
            ClientDTO c = clientDAO.buscarPorCpf(cpf);
            if (c == null) { System.out.println("Não encontrado."); return; }
            System.out.print("Novo nome (atual: " + c.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo email (atual: " + c.getEmail() + "): "); String email = scanner.nextLine();
            System.out.print("Novo setor (atual: " + c.getSetor() + "): "); String setor = scanner.nextLine();
            c.setNome(nome.isEmpty() ? c.getNome() : nome);
            c.setEmail(email.isEmpty() ? c.getEmail() : email);
            c.setSetor(setor.isEmpty() ? c.getSetor() : setor);
            clientDAO.atualizar(c);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void deletarCliente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            clientDAO.deletar(cpf);
            System.out.println("Deletado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // CAIXAS
    private static void menuCaixas() {
        while (true) {
            System.out.println("-- Gerenciar Caixas --");
            System.out.println("1) Inserir  2) Buscar  3) Listar  4) Atualizar  5) Deletar  0) Voltar");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": inserirCaixa(); break;
                case "2": buscarCaixa(); break;
                case "3": listarCaixas(); break;
                case "4": atualizarCaixa(); break;
                case "5": deletarCaixa(); break;
                case "0": return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    private static void inserirCaixa() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Salário: "); double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Setor: "); String setor = scanner.nextLine();
        try {
            cashierDAO.inserir(new CashierDTO(cpf, nome, salario, setor));
            System.out.println("Caixa inserido!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void buscarCaixa() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            CashierDTO c = cashierDAO.buscarPorCpf(cpf);
            if (c != null) System.out.println(c);
            else System.out.println("Não encontrado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void listarCaixas() {
        try {
            List<CashierDTO> lista = cashierDAO.listarTodos();
            for (CashierDTO c : lista) System.out.println(c);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void atualizarCaixa() {
        System.out.print("CPF do caixa a atualizar: "); String cpf = scanner.nextLine();
        try {
            CashierDTO c = cashierDAO.buscarPorCpf(cpf);
            if (c == null) { System.out.println("Não encontrado."); return; }
            System.out.print("Novo nome (atual: " + c.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo salário (atual: " + c.getSalario() + "): "); String salarioStr = scanner.nextLine();
            System.out.print("Novo setor (atual: " + c.getSetor() + "): "); String setor = scanner.nextLine();
            c.setNome(nome.isEmpty() ? c.getNome() : nome);
            c.setSalario(salarioStr.isEmpty() ? c.getSalario() : Double.parseDouble(salarioStr));
            c.setSetor(setor.isEmpty() ? c.getSetor() : setor);
            cashierDAO.atualizar(c);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void deletarCaixa() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            cashierDAO.deletar(cpf);
            System.out.println("Deletado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // GERENTES
    private static void menuGerentes() {
        while (true) {
            System.out.println("-- Gerenciar Gerentes --");
            System.out.println("1) Inserir  2) Buscar  3) Listar  4) Atualizar  5) Deletar  0) Voltar");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": inserirGerente(); break;
                case "2": buscarGerente(); break;
                case "3": listarGerentes(); break;
                case "4": atualizarGerente(); break;
                case "5": deletarGerente(); break;
                case "0": return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    private static void inserirGerente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Salário: "); double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Setor: "); String setor = scanner.nextLine();
        try {
            managerDAO.inserir(new ManagerDTO(cpf, nome, salario, setor));
            System.out.println("Gerente inserido!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void buscarGerente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            ManagerDTO m = managerDAO.buscarPorCpf(cpf);
            if (m != null) System.out.println(m);
            else System.out.println("Não encontrado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void listarGerentes() {
        try {
            List<ManagerDTO> lista = managerDAO.listarTodos();
            for (ManagerDTO m : lista) System.out.println(m);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void atualizarGerente() {
        System.out.print("CPF do gerente a atualizar: "); String cpf = scanner.nextLine();
        try {
            ManagerDTO m = managerDAO.buscarPorCpf(cpf);
            if (m == null) { System.out.println("Não encontrado."); return; }
            System.out.print("Novo nome (atual: " + m.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo salário (atual: " + m.getSalario() + "): "); String salarioStr = scanner.nextLine();
            System.out.print("Novo setor (atual: " + m.getSetor() + "): "); String setor = scanner.nextLine();
            m.setNome(nome.isEmpty() ? m.getNome() : nome);
            m.setSalario(salarioStr.isEmpty() ? m.getSalario() : Double.parseDouble(salarioStr));
            m.setSetor(setor.isEmpty() ? m.getSetor() : setor);
            managerDAO.atualizar(m);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void deletarGerente() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            managerDAO.deletar(cpf);
            System.out.println("Deletado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // REPOSITORES
    private static void menuRepositores() {
        while (true) {
            System.out.println("-- Gerenciar Repositores --");
            System.out.println("1) Inserir  2) Buscar  3) Listar  4) Atualizar  5) Deletar  0) Voltar");
            System.out.print("Escolha: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1": inserirRepositor(); break;
                case "2": buscarRepositor(); break;
                case "3": listarRepositores(); break;
                case "4": atualizarRepositor(); break;
                case "5": deletarRepositor(); break;
                case "0": return;
                default: System.out.println("Opção inválida!");
            }
        }
    }
    private static void inserirRepositor() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Salário: "); double salario = Double.parseDouble(scanner.nextLine());
        System.out.print("Setor: "); String setor = scanner.nextLine();
        try {
            stockerDAO.inserir(new StockerDTO(cpf, nome, salario, setor));
            System.out.println("Repositor inserido!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void buscarRepositor() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            StockerDTO s = stockerDAO.buscarPorCpf(cpf);
            if (s != null) System.out.println(s);
            else System.out.println("Não encontrado.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void listarRepositores() {
        try {
            List<StockerDTO> lista = stockerDAO.listarTodos();
            for (StockerDTO s : lista) System.out.println(s);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void atualizarRepositor() {
        System.out.print("CPF do repositor a atualizar: "); String cpf = scanner.nextLine();
        try {
            StockerDTO s = stockerDAO.buscarPorCpf(cpf);
            if (s == null) { System.out.println("Não encontrado."); return; }
            System.out.print("Novo nome (atual: " + s.getNome() + "): "); String nome = scanner.nextLine();
            System.out.print("Novo salário (atual: " + s.getSalario() + "): "); String salarioStr = scanner.nextLine();
            System.out.print("Novo setor (atual: " + s.getSetor() + "): "); String setor = scanner.nextLine();
            s.setNome(nome.isEmpty() ? s.getNome() : nome);
            s.setSalario(salarioStr.isEmpty() ? s.getSalario() : Double.parseDouble(salarioStr));
            s.setSetor(setor.isEmpty() ? s.getSetor() : setor);
            stockerDAO.atualizar(s);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
    private static void deletarRepositor() {
        System.out.print("CPF: "); String cpf = scanner.nextLine();
        try {
            stockerDAO.deletar(cpf);
            System.out.println("Deletado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
} 