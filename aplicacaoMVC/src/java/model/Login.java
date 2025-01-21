package model;

import java.sql.*;

public class Login {
    public Boolean ExisteAdm(String cpf, String senha){
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, cpf);
            sql.setString(2, senha);
            ResultSet resultado = sql.executeQuery();
            System.out.println(resultado.toString());
            return resultado.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    public Boolean ExisteAluno(String cpf, String senha){
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, cpf);
            sql.setString(2, senha);
            ResultSet resultado = sql.executeQuery();
            return resultado.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    public Boolean ExisteProfessor(String cpf, String senha){
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, cpf);
            sql.setString(2, senha);
            ResultSet resultado = sql.executeQuery();
            return resultado.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
