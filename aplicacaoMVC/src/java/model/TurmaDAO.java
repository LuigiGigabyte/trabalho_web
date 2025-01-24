package model;

import entidade.Aluno;
import model.AlunoDAO;
import entidade.Disciplina;
import model.DisciplinaDAO;
import entidade.Turma;
import java.sql.*;
import java.util.ArrayList;

public class TurmaDAO implements Dao<Turma> {

    @Override
    public Turma get(int id) {
        Turma turma = null;
        String query = "SELECT * FROM turmas WHERE id = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                turma = new Turma(
                        resultado.getInt("id"),
                        resultado.getInt("professor_id"),
                        resultado.getInt("disciplina_id"),
                        resultado.getInt("aluno_id"),
                        resultado.getString("codigo_turma"),
                        resultado.getDouble("nota")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma: " + e.getMessage());
        }
        return turma;
    }
    
    public Turma getByCod(int professor_id, int disciplina_id, String codigo_turma, int aluno_id) {
        Turma turma = null;
        String query = "SELECT * FROM turmas WHERE professor_id = ? "
                + "AND disciplina_id = ? AND codigo_turma = ? AND aluno_id = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, professor_id);
            sql.setInt(2, disciplina_id);
            sql.setString(3, codigo_turma);
            sql.setInt(4, aluno_id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                turma = new Turma(
                        resultado.getInt("id"),
                        resultado.getInt("professor_id"),
                        resultado.getInt("disciplina_id"),
                        resultado.getInt("aluno_id"),
                        resultado.getString("codigo_turma"),
                        resultado.getDouble("nota")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma: " + e.getMessage());
        }
        return turma;
    }
    
    public boolean getTurmaCheia(int professor_id, int disciplina_id, String codigo_turma) {
        String query = "SELECT professor_id, disciplina_id, codigo_turma, "
                + "COUNT(*) FROM turmas t WHERE professor_id = ? "
                + "AND disciplina_id = ? AND codigo_turma = ?" +
                "GROUP BY professor_id, disciplina_id, codigo_turma HAVING COUNT(*) > 1";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, professor_id);
            sql.setInt(2, disciplina_id);
            sql.setString(3, codigo_turma);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turma: " + e.getMessage());
        }
        return false;
    }
    @Override
    public void insert(Turma turma) {
        String query = "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setDouble(5, turma.getNota());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir turma: " + e.getMessage());
        }
    }
    public void insertAluno(Turma turma, int alunoId) {
        String query = "INSERT INTO turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) VALUES (?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, alunoId);
            sql.setString(4, turma.getCodigoTurma());
            sql.setDouble(5, 99);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir turma: " + e.getMessage());
        }
    }
    @Override
    public void update(Turma turma) {
        String query = "UPDATE turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ? WHERE id = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, turma.getProfessorId());
            sql.setInt(2, turma.getDisciplinaId());
            sql.setInt(3, turma.getAlunoId());
            sql.setString(4, turma.getCodigoTurma());
            sql.setDouble(5, turma.getNota());
            sql.setInt(6, turma.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        
        
        String query = "DELETE FROM turmas WHERE id = ?";

        Conexao conexao = new Conexao();
        try {
           
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            sql.executeUpdate();
            
            //alunodao.insert(aluno);
            //disciplinadao.insert(disciplina);
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir turma: " + e.getMessage());
        }
    }
    
    public void deleteByCod(int professor_id, int disciplina_id, String codigo_turma) {
        
        String query = "DELETE FROM turmas WHERE professor_id = ? " +
                        "AND disciplina_id = ? AND codigo_turma = ?";

        Conexao conexao = new Conexao();
        try {
           
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, professor_id);
            sql.setInt(2, disciplina_id);
            sql.setString(3, codigo_turma);
            sql.executeUpdate();

            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir turma: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Turma> getAll() {
        ArrayList<Turma> turmas = new ArrayList<>();
        String query = "SELECT * FROM turmas";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Turma turma = new Turma();
                
                turma.setId(resultado.getInt("id"));
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setAlunoId(resultado.getInt("aluno_id"));
                turma.setNota(resultado.getDouble("nota"));

                turmas.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar turmas: " + e.getMessage());
        }
        return turmas;
    }
    public ArrayList<Turma> getTurmasNaoInscritas(int alunoId) {
        ArrayList<Turma> turmasNaoInscritas = new ArrayList<>();
        String query = "SELECT DISTINCT t.professor_id, t.disciplina_id, t.codigo_turma " +
               "FROM turmas AS t " +
               "LEFT JOIN ( " +
               "    SELECT DISTINCT professor_id, disciplina_id, codigo_turma " +
               "    FROM turmas " +
               "    WHERE aluno_id = ? " +
               ") AS ct " +
               "ON t.professor_id = ct.professor_id " +
               "AND t.disciplina_id = ct.disciplina_id " +
               "AND t.codigo_turma = ct.codigo_turma " +
               "WHERE ct.professor_id IS NULL";


        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, alunoId);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Turma turma = new Turma();
                
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                        

                turmasNaoInscritas.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turmas não inscritas: " + e.getMessage());
        }

        return turmasNaoInscritas;
    }
    public ArrayList<Turma> getTurmasInscritas(int alunoId) {
        ArrayList<Turma> turmasInscritas = new ArrayList<>();
                String query = "SELECT DISTINCT professor_id, disciplina_id, codigo_turma FROM turmas WHERE aluno_id = ?";


        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, alunoId);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Turma turma = new Turma();
                
                turma.setProfessorId(resultado.getInt("professor_id"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turmasInscritas.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turmas não inscritas: " + e.getMessage());
        }

        return turmasInscritas;
    }
    public ArrayList<Turma> getAllTurmasComAlunosNotas() {
        ArrayList<Turma> turmas = new ArrayList<>();
        String query = "SELECT t.id AS turma_id, t.codigo_turma, a.id AS aluno_id, a.nome AS aluno_nome, t.nota, d.id AS disciplina_id " +
                       "FROM turmas t " +
                       "JOIN alunos a ON a.id = t.aluno_id " +
                       "JOIN disciplina d ON d.id = t.disciplina_id";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet resultado = sql.executeQuery();


            while (resultado.next()) {

                Aluno aluno = new Aluno();
                aluno.setId(resultado.getInt("aluno_id"));
                aluno.setNome(resultado.getString("aluno_nome"));
                
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                Disciplina disciplina = new Disciplina();


                Turma turma = new Turma();
                turma.setId(resultado.getInt("turma_id"));
                turma.setCodigoTurma(resultado.getString("codigo_turma"));
                turma.setDisciplinaId(resultado.getInt("disciplina_id"));
                turma.setNota(resultado.getDouble("nota"));


                turma.addAluno(aluno);

                turmas.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar turmas com alunos e notas: " + e.getMessage());
        }

        return turmas;
    }
}
