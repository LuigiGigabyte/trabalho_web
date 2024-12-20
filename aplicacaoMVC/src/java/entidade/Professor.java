package entidade;

public class Aluno {

    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String cidade;
    private String bairro;
    private String cep;
    private String email;
    private String celular;
    private String senha;

    public Aluno(int id, String nome, String cpf, String endereco, String cidade, String bairro, String cep, 
                 String email, String celular, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
    }

    public Aluno(String nome, String cpf, String endereco, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Aluno(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Aluno() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.endereco = "";
        this.cidade = "";
        this.bairro = "";
        this.cep = "";
        this.email = "";
        this.celular = "";
        this.senha = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
