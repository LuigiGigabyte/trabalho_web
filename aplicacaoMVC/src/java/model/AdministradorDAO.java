package model;

import entidade.Administrador;
import java.sql.*;
import java.util.ArrayList;

public class AdministradorDAO implements Dao<Administrador> {

    @Override
    public Administrador get(int id) {
        Administrador admin = null;
        String query = "SELECT * FROM administrador WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado.next()) {
                admin = new Administrador(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA"),
                        resultado.getString("ENDERECO"),
                        resultado.getString("APROVADO")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar administrador: " + e.getMessage());
        }
        return admin;
    }

    @Override
    public void insert(Administrador admin) {
        String query = "INSERT INTO administrador (nome, cpf, senha, endereco, aprovado) VALUES (?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, admin.getNome());
            sql.setString(2, admin.getCpf());
            sql.setString(3, admin.getSenha());
            sql.setString(4, admin.getEndereco());
            sql.setString(5, admin.getAprovado());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir administrador: " + e.getMessage());
        }
    }

    @Override
    public void update(Administrador admin) {
        String query = "UPDATE administrador SET nome = ?, cpf = ?, senha = ?, endereco = ?, aprovado = ? WHERE ID = ?";

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setString(1, admin.getNome());
            sql.setString(2, admin.getCpf());
            sql.setString(3, admin.getSenha());
            sql.setString(4, admin.getEndereco());
            sql.setString(5, admin.getAprovado());
            sql.setInt(6, admin.getId());
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar administrador: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM administrador WHERE ID = ?";
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            sql.setInt(1, id);
            sql.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir administrador: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Administrador> getAll() {
        ArrayList<Administrador> administradores = new ArrayList<>();
        String query = "SELECT * FROM administrador";
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(query);
            ResultSet resultado = sql.executeQuery();

            while (resultado.next()) {
                Administrador admin = new Administrador(
                        resultado.getInt("ID"),
                        resultado.getString("NOME"),
                        resultado.getString("CPF"),
                        resultado.getString("SENHA"),
                        resultado.getString("ENDERECO"),
                        resultado.getString("APROVADO")
                );
                administradores.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar administradores: " + e.getMessage());
        }
        return administradores;
    }
    public Administrador Logar(Administrador Administrador) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Administrador WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, Administrador.getCpf());
            sql.setString(2, Administrador.getSenha());
            ResultSet resultado = sql.executeQuery();
            Administrador AdministradorObtido = new Administrador();
            if (resultado != null) {
                while (resultado.next()) {
                    AdministradorObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    AdministradorObtido.setNome(resultado.getString("NOME"));
                    AdministradorObtido.setCpf(resultado.getString("CPF"));
                    AdministradorObtido.setEndereco(resultado.getString("ENDERECO"));
                    AdministradorObtido.setSenha(resultado.getString("SENHA"));
                }
            }
            return AdministradorObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
