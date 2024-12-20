package model;

import entidade.Aluno;
import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO implements Dao<Aluno> {

    @Override
    public Aluno get(int id) {
        Aluno Aluno = new Aluno();
        String query = "SELECT * FROM Alunos WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado !=  null) {
                while (resultado.next()) {
                    Aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    Aluno.setNome(resultado.getString("NOME"));
                    Aluno.setCpf(resultado.getString("CPF"));
                    Aluno.setEndereco(resultado.getString("ENDERECO"));
                    Aluno.setSenha(resultado.getString("SENHA"));
                    Aluno.setEmail(resultado.getString("EMAIL"));
                    Aluno.setCelular(resultado.getString("CELULAR"));
                    Aluno.setCidade(resultado.getString("CIDADE"));
                    Aluno.setBairro(resultado.getString("BAIRRO"));
                    Aluno.setCep(resultado.getString("CEP"));
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return Aluno;
    }

    @Override
    public void insert(Aluno aluno) {
        String query = "INSERT INTO Alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getEmail());
            sql.setString(3, aluno.getCelular());
            sql.setString(4, aluno.getCpf());
            sql.setString(5, aluno.getSenha());
            sql.setString(6, aluno.getEndereco());
            sql.setString(7, aluno.getCidade());
            sql.setString(8, aluno.getBairro());
            sql.setString(9, aluno.getCep());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    @Override
    public void update(Aluno aluno) {
        Conexao conexao = new Conexao();
        try {
            String updateSQL = "UPDATE alunos SET nome = ?, cpf = ?, endereco = ?, senha = ?, cidade = ?, bairro = ?, celular = ?, email = ?, cep = ?  WHERE id = ? ";
            PreparedStatement sql = conexao.getConexao().prepareStatement(updateSQL);
            sql.setString(1, aluno.getNome());
            sql.setString(2, aluno.getCpf());
            sql.setString(3, aluno.getEndereco());
            sql.setString(4, aluno.getSenha());
            sql.setString(5, aluno.getCidade());
            sql.setString(6, aluno.getBairro());
            sql.setString(7, aluno.getCelular());
            sql.setString(8, aluno.getEmail());
            sql.setString(9, aluno.getCep());
            sql.setInt(10, aluno.getId());
            System.out.println(sql.toString());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Alunos WHERE ID = ?";
        Conexao conexao = new Conexao();
        try (
                 PreparedStatement sql = conexao.getConexao().prepareStatement(query)) {

            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String query = "SELECT * FROM Alunos";
        Conexao conexao = new Conexao();
        try (
                 PreparedStatement sql = conexao.getConexao().prepareStatement(query);  ResultSet resultado = sql.executeQuery()) {

            while (resultado.next()) {
                Aluno aluno = new Aluno(
                        resultado.getInt("ID"),
                        resultado.getString("NOME")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }
}
