package model;

import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;
import java.sql.*;
import java.util.ArrayList;

public class DisciplinaDAO implements Dao<Disciplina> {

    @Override
    public Disciplina get(int id) {
        Disciplina disciplina = null;
        String query = "SELECT * FROM disciplina WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                disciplina = new Disciplina(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("REQUISITO"),
                        resultado.getString("EMENTA"),
                        resultado.getInt("CARGA_HORARIA")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar disciplina: " + e.getMessage());
        }
        return disciplina;
    }

    @Override
    public void insert(Disciplina disciplina) {
        String query = "INSERT INTO disciplina (nome, requisito, ementa, carga_horaria) VALUES (?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir disciplina: " + e.getMessage());
        }
    }

    @Override
    public void update(Disciplina disciplina) {
        String query = "UPDATE disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, disciplina.getNome());
            sql.setString(2, disciplina.getRequisito());
            sql.setString(3, disciplina.getEmenta());
            sql.setInt(4, disciplina.getCargaHoraria());
            sql.setInt(5, disciplina.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM disciplina WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir disciplina: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        String query = "SELECT * FROM disciplina";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Disciplina disciplina = new Disciplina(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("REQUISITO"),
                        resultado.getString("EMENTA"),
                        resultado.getInt("CARGA_HORARIA")
                );
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }
    public ArrayList<Turma> getTurmas(Disciplina disciplina) {
        ArrayList<Turma> turmas = new ArrayList<>();
        String query = "SELECT * FROM Turmas where disciplina_id = ? ";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, disciplina.getId());
            ResultSet resultado = sql.executeQuery();


            while (resultado.next()) {

                Turma turma = new Turma();
                turma.setId(resultado.getInt("id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setNota(resultado.getDouble("nota"));
               
                turmas.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turmas com alunos e notas: " + e.getMessage());
        }finally {
            conexao.closeConexao();
        }

        return turmas;
    }
}
