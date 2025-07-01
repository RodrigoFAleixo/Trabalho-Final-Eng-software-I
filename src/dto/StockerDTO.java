package dto;


public class StockerDTO {
    private String cpf;
    private String nome;
    private Double salario;
    private String setor;

    public StockerDTO() {}

    public StockerDTO(String cpf, String nome, Double salario, String setor) {
        this.cpf     = cpf;
        this.nome    = nome;
        this.salario = salario;
        this.setor   = setor;
    }

    // getters & setters
    public String getCpf()            { return cpf; }
    public void   setCpf(String cpf)    { this.cpf = cpf; }

    public String getNome()           { return nome; }
    public void   setNome(String nome)  { this.nome = nome; }

    public Double getSalario()              { return salario; }
    public void   setSalario(Double salario) { this.salario = salario; }

    public String getSetor()             { return setor; }
    public void   setSetor(String setor)   { this.setor = setor; }

    @Override
    public String toString() {
        return "RepositorDTO{cpf='" + cpf + "', nome='" + nome +
               "', salario=" + salario + ", setor='" + setor + "'}";
    }
}

