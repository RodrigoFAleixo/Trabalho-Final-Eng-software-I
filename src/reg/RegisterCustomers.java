package reg;


import dao.CashierDAO;
import dao.ClientDAO;
import dao.ManagerDAO;
import dao.StockerDAO;
import dto.CashierDTO;
import dto.ClientDTO;
import dto.ManagerDTO;
import dto.StockerDTO;



import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * RegisterCustomers
 *
 * Classe única para acessar todas as funcionalidades dos DAOs:
 * clientes, caixas, gerentes e repositores.
 */
public class RegisterCustomers {
    private final ClientDAO clientDAO = new ClientDAO();
    private final CashierDAO   cashierDAO = new CashierDAO();
    private final ManagerDAO managerDAO = new ManagerDAO();
    private final StockerDAO stockerDAO = new StockerDAO();

    private final Scanner in = new Scanner(System.in);

    public RegisterCustomers(){
        menu();
    }

    private void menu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1) Clientes");
            System.out.println("2) Caixas");
            System.out.println("3) Gerentes");
            System.out.println("4) Repositores");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            switch (in.nextLine()) {
                case "1": submenuCliente();     break;
                case "2": submenuCaixa();       break;
                case "3": submenuGerente();     break;
                case "4": submenuRepositor();   break;
                case "0": System.exit(0);
                default : System.out.println("Opção inválida");
            }
        }
    }

    /*==================== CLIENTE ====================*/
    private void submenuCliente() {
        System.out.println("\n-- Gerenciar Clientes --");
        System.out.println("1) Inserir  2) Buscar  3) Listar  4) Atualizar  5) Deletar  6) Contar  0) Voltar");
        System.out.print("Escolha: ");
        switch (in.nextLine()) {
            case "1": inserirCliente();   break;
            case "2": buscarCliente();    break;
            case "3": listarClientes();   break;
            case "4": atualizarCliente(); break;
            case "5": deletarCliente();   break;
            case "6": System.out.println("Total de clientes: " + clientDAO.contarClientes()); break;
            case "0": return;
            default : System.out.println("Opção inválida");
        }
    }

    private void inserirCliente() {
        System.out.print("CPF: ");     String cpf   = in.nextLine();
        System.out.print("Nome: ");    String nome  = in.nextLine();
        System.out.print("Email: ");   String email = in.nextLine();
        System.out.print("Setor: ");   String setor = in.nextLine();
        clientDAO.inserir(new ClientDTO(cpf, nome, email, setor));
        System.out.println("Cliente inserido!");
    }

    private void buscarCliente() {
        System.out.print("CPF: ");
        ClientDTO c = clientDAO.buscarPorCpf(in.nextLine());
        System.out.println(c == null ? "Não encontrado" : c);
    }

    private void listarClientes() {
        List<ClientDTO> list = clientDAO.listarTodos();
        list.forEach(System.out::println);
    }

    private void atualizarCliente() {
        System.out.print("CPF do cliente: "); String cpf = in.nextLine();
        ClientDTO c = clientDAO.buscarPorCpf(cpf);
        if (c == null) {
            System.out.println("Cliente não existe.");
            return;
        }
        System.out.print("Novo nome [" + c.getNome() + "]: ");    
        String nome = in.nextLine(); if (!nome.isBlank()) c.setNome(nome);
        System.out.print("Novo email [" + c.getEmail() + "]: ");
        String email = in.nextLine(); if (!email.isBlank()) c.setEmail(email);
        System.out.print("Novo setor [" + c.getSetor() + "]: ");
        String setor = in.nextLine(); if (!setor.isBlank()) c.setSetor(setor);
        clientDAO.atualizar(c);
        System.out.println("Cliente atualizado!");
    }

    private void deletarCliente() {
        System.out.print("CPF: ");
        clientDAO.deletar(in.nextLine());
        System.out.println("Cliente deletado (se existia).");
    }


    /*==================== CAIXA ====================*/
    private void submenuCaixa() {
        System.out.println("\n-- Gerenciar Caixas --");
        System.out.println("1) Inserir 2) Buscar 3) Listar 4) Atualizar 5) Deletar 6) Total salários 7) Média por setor 0) Voltar");
        System.out.print("Escolha: ");
        switch (in.nextLine()) {
            case "1": inserirCaixa();     break;
            case "2": buscarCaixa();      break;
            case "3": listarCaixas();     break;
            case "4": atualizarCaixa();   break;
            case "5": deletarCaixa();     break;
            case "6": System.out.println("Total salários: " + cashierDAO.totalSalario()); break;
            case "7": imprimirMapa(cashierDAO.mediaSalarioPorSetor()); break;
            case "0": return;
            default : System.out.println("Opção inválida");
        }
    }

    private void inserirCaixa() {
        System.out.print("CPF: ");      String cpf    = in.nextLine();
        System.out.print("Nome: ");     String nome   = in.nextLine();
        System.out.print("Salário: ");  Double sal    = Double.valueOf(in.nextLine());
        System.out.print("Setor: ");    String setor  = in.nextLine();
        cashierDAO.inserir(new CashierDTO(cpf, nome, sal, setor));
        System.out.println("Caixa inserido!");
    }

    private void buscarCaixa() {
        System.out.print("CPF: ");
        CashierDTO c = cashierDAO.buscarPorCpf(in.nextLine());
        System.out.println(c == null ? "Não encontrado" : c);
    }

    private void listarCaixas() {
        List<CashierDTO> list = cashierDAO.listarTodos();
        list.forEach(System.out::println);
    }

    private void atualizarCaixa() {
        System.out.print("CPF do caixa: "); String cpf = in.nextLine();
        CashierDTO c = cashierDAO.buscarPorCpf(cpf);
        if (c == null) {
            System.out.println("Caixa não existe.");
            return;
        }
        System.out.print("Novo nome [" + c.getNome() + "]: ");
        String nome = in.nextLine(); if (!nome.isBlank()) c.setNome(nome);
        System.out.print("Novo salário [" + c.getSalario() + "]: ");
        String sal = in.nextLine(); if (!sal.isBlank()) c.setSalario(Double.valueOf(sal));
        System.out.print("Novo setor [" + c.getSetor() + "]: ");
        String setor = in.nextLine(); if (!setor.isBlank()) c.setSetor(setor);
        cashierDAO.atualizar(c);
        System.out.println("Caixa atualizado!");
    }

    private void deletarCaixa() {
        System.out.print("CPF: ");
        cashierDAO.deletar(in.nextLine());
        System.out.println("Caixa deletado (se existia).");
    }


    /*==================== GERENTE ====================*/
    private void submenuGerente() {
        System.out.println("\n-- Gerenciar Gerentes --");
        System.out.println("1) Inserir 2) Buscar 3) Listar 4) Atualizar 5) Deletar 6) Total salários 7) Média por setor 0) Voltar");
        System.out.print("Escolha: ");
        switch (in.nextLine()) {
            case "1": inserirGerente();    break;
            case "2": buscarGerente();     break;
            case "3": listarGerentes();    break;
            case "4": atualizarGerente();  break;
            case "5": deletarGerente();    break;
            case "6": System.out.println("Total salários: " + managerDAO.totalSalario()); break;
            case "7": imprimirMapa(managerDAO.mediaSalarioPorSetor()); break;
            case "0": return;
            default : System.out.println("Opção inválida");
        }
    }

    private void inserirGerente() {
        System.out.print("CPF: ");      String cpf    = in.nextLine();
        System.out.print("Nome: ");     String nome   = in.nextLine();
        System.out.print("Salário: ");  Double sal    = Double.valueOf(in.nextLine());
        System.out.print("Setor: ");    String setor  = in.nextLine();
        managerDAO.inserir(new ManagerDTO(cpf, nome, sal, setor));
        System.out.println("Gerente inserido!");
    }

    private void buscarGerente() {
        System.out.print("CPF: ");
        ManagerDTO g = managerDAO.buscarPorCpf(in.nextLine());
        System.out.println(g == null ? "Não encontrado" : g);
    }

    private void listarGerentes() {
        List<ManagerDTO> list = managerDAO.listarTodos();
        list.forEach(System.out::println);
    }

    private void atualizarGerente() {
        System.out.print("CPF do gerente: "); String cpf = in.nextLine();
        ManagerDTO g = managerDAO.buscarPorCpf(cpf);
        if (g == null) {
            System.out.println("Gerente não existe.");
            return;
        }
        System.out.print("Novo nome [" + g.getNome() + "]: ");
        String nome = in.nextLine(); if (!nome.isBlank()) g.setNome(nome);
        System.out.print("Novo salário [" + g.getSalario() + "]: ");
        String sal = in.nextLine(); if (!sal.isBlank()) g.setSalario(Double.valueOf(sal));
        System.out.print("Novo setor [" + g.getSetor() + "]: ");
        String setor = in.nextLine(); if (!setor.isBlank()) g.setSetor(setor);
        managerDAO.atualizar(g);
        System.out.println("Gerente atualizado!");
    }

    private void deletarGerente() {
        System.out.print("CPF: ");
        managerDAO.deletar(in.nextLine());
        System.out.println("Gerente deletado (se existia).");
    }


    /*==================== REPOSITOR ====================*/
    private void submenuRepositor() {
        System.out.println("\n-- Gerenciar Repositores --");
        System.out.println("1) Inserir 2) Buscar 3) Listar 4) Atualizar 5) Deletar 6) Total salários 7) Média por setor 0) Voltar");
        System.out.print("Escolha: ");
        switch (in.nextLine()) {
            case "1": inserirRepositor();    break;
            case "2": buscarRepositor();     break;
            case "3": listarRepositores();   break;
            case "4": atualizarRepositor();  break;
            case "5": deletarRepositor();    break;
            case "6": System.out.println("Total salários: " + stockerDAO.totalSalario()); break;
            case "7": imprimirMapa(stockerDAO.mediaSalarioPorSetor()); break;
            case "0": return;
            default : System.out.println("Opção inválida");
        }
    }

    private void inserirRepositor() {
        System.out.print("CPF: ");      String cpf    = in.nextLine();
        System.out.print("Nome: ");     String nome   = in.nextLine();
        System.out.print("Salário: ");  Double sal    = Double.valueOf(in.nextLine());
        System.out.print("Setor: ");    String setor  = in.nextLine();
        stockerDAO.inserir(new StockerDTO(cpf, nome, sal, setor));
        System.out.println("Repositor inserido!");
    }

    private void buscarRepositor() {
        System.out.print("CPF: ");
        StockerDTO r = stockerDAO.buscarPorCpf(in.nextLine());
        System.out.println(r == null ? "Não encontrado" : r);
    }

    private void listarRepositores() {
        List<StockerDTO> list = stockerDAO.listarTodos();
        list.forEach(System.out::println);
    }

    private void atualizarRepositor() {
        System.out.print("CPF do repositor: "); String cpf = in.nextLine();
        StockerDTO r = stockerDAO.buscarPorCpf(cpf);
        if (r == null) {
            System.out.println("Repositor não existe.");
            return;
        }
        System.out.print("Novo nome [" + r.getNome() + "]: ");
        String nome = in.nextLine(); if (!nome.isBlank()) r.setNome(nome);
        System.out.print("Novo salário [" + r.getSalario() + "]: ");
        String sal = in.nextLine(); if (!sal.isBlank()) r.setSalario(Double.valueOf(sal));
        System.out.print("Novo setor [" + r.getSetor() + "]: ");
        String setor = in.nextLine(); if (!setor.isBlank()) r.setSetor(setor);
        stockerDAO.atualizar(r);
        System.out.println("Repositor atualizado!");
    }

    private void deletarRepositor() {
        System.out.print("CPF: ");
        stockerDAO.deletar(in.nextLine());
        System.out.println("Repositor deletado (se existia).");
    }

    /*==================== UTIL ====================*/
    private <K, V> void imprimirMapa(Map<K, V> map) {
        map.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}

