public class Gerente {
    private String CPF;
    private String nome;
    private double salario;
    private String setor;

    public Gerente(String CPF, String nome, double salario, String setor) {
        this.CPF = CPF;
        this.nome = nome;
        this.salario = salario;
        this.setor = setor;
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
    public void setSalario (Double salario){
        this.salario = salario; 
    }

    @Override
    public String toString() {
        return "Cliente{id=" + CPF + ", nome='" + nome + "', salario='" + salario + "'}";
    }

    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
}
