public class Cliente {
    private String CPF;
    private String nome;
    private String email;
    private String setor;

    public Cliente(String CPF, String nome, String email) {
        this.CPF = CPF;
        this.nome = nome;
        this.email = email;
    }

    // getters & setters
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + CPF + ", nome='" + nome + "', email='" + email + "'}";
    }
}
