package dto;


public class ClientDTO {
    private String cpf;
    private String nome;
    private String email;
    private String setor;

    public ClientDTO() {}

    public ClientDTO(String cpf, String nome, String email, String setor) {
        this.cpf   = cpf;
        this.nome  = nome;
        this.email = email;
        this.setor = setor;
    }

    // getters & setters
    public String getCPF()         { return cpf; }
    public void   setCPF(String cpf) { this.cpf = cpf; }

    public String getNome()          { return nome; }
    public void   setNome(String nome) { this.nome = nome; }

    public String getEmail()           { return email; }
    public void   setEmail(String email) { this.email = email; }

    public String getSetor()           { return setor; }
    public void   setSetor(String setor) { this.setor = setor; }

    @Override
    public String toString() {
        return "ClienteDTO{cpf='" + cpf + "', nome='" + nome +
               "', email='" + email + "', setor='" + setor + "'}";
    }
}

