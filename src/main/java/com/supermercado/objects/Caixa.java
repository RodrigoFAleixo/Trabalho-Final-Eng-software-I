public class Caixa {
    private String CPF;
    private String nome;
    private Double salario;
    private String setor;

    public Caixa(String CPF, String nome, Double salario) {
        this.CPF = CPF;
        this.nome = nome;
        this.salario = salario;
    }

    // getters, setters e toString()
    public String getCPF() {
        return CPF;
    }
    public String getNome() {
        return nome;
    }

    public Double getSalario() {
        return salario;
    } 
    public void setSalario(Double salario){
        this.salario = salario;
    }

    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Caixa{CPF = " + CPF + ", nome = " + nome+"salario = "+ salario+ "}";
    }
}