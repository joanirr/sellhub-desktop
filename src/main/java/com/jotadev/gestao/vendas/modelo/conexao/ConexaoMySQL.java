package com.jotadev.gestao.vendas.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_venda?useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASSWORD = "@Joanir123";

    public static Connection obterConexao() throws SQLException {
        // SEMPRE retorna uma conexão nova para evitar o erro de "Connection Closed"
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // O fecharConexao agora deve receber a conexão que deve ser fechada
    public static void fecharConexao(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar: " + e.getMessage());
        }
    }
}
