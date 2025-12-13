package com.jotadev.gestao.vendas.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_venda?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASSWORD = "@Joanir123";
    private static Connection connection;
    
    private ConexaoMySQL() {}
    
    public static Connection obterConexao() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    
    public static void fecharConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(obterConexao());
    }
}
