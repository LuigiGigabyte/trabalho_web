package model;

import entidade.Professor;
import java.sql.*;
import java.util.ArrayList;

public class ProfessorDAO implements Dao<Professor> {

    @Override
    public Professor get(int id) {
        Professor professor = null;
        String query = "SELECT * FROM Professores WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                professor = new Professor(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("EMAIL"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar professor: " + e.getMessage());
        }
        return professor;
    }

    @Override
    public void insert(Professor professor) {
        String query = "INSERT INTO Professores (nome, email, cpf, senha) VALUES (?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    @Override
    public void update(Professor professor) {
        String query = "UPDATE Professores SET nome = ?, email = ?, cpf = ?, senha = ? WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, professor.getNome());
            sql.setString(2, professor.getEmail());
            sql.setString(3, professor.getCpf());
            sql.setString(4, professor.getSenha());
            sql.setInt(5, professor.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Professores WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir professor: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Professor> getAll() {
        ArrayList<Professor> professores = new ArrayList<>();
        String query = "SELECT * FROM Professores";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Professor professor = new Professor(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("EMAIL"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA")
                );
                professores.add(professor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }
        return professores;
    }
}
