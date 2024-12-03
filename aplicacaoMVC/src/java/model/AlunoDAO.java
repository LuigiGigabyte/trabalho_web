package model;

import entidade.Aluno;
import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO implements Dao<Aluno> {

    @Override
    public Aluno get(int id) {
        Aluno aluno = null;
        String query = "SELECT * FROM Alunos WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                aluno = new Aluno(
                        resultado.getInt("ID"),
                        resultado.getString("NOME")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno;
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
        String query = "UPDATE Alunos SET nome = ?, WHERE ID = ?";
        Conexao conexao = new Conexao();
        try (
            PreparedStatement sql = conexao.getConexao().prepareStatement(query)) {
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
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
