package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.conexao.ConexaoMySQL;
import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioRepositorioImpl extends CrudRepositorioImpl {
    
    public UsuarioRepositorioImpl() {
        super(Usuario.class);
    }

    
    public Optional<Object> buscarPeloEmail(String email){
        try {
            String SQL = "SELECT * FROM usuario WHERE email = ?";
            
            PreparedStatement ps = ConexaoMySQL.obterConexao().prepareStatement(SQL);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                
                return Optional.ofNullable(getT(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return Optional.empty();
    }
    
    public static void main(String[] args) {
        Usuario usuario = Usuario.builder()
                .nome("Luizinho Gustavinho")
                .email("luizmito@gmail.com")
                .estado(true)
                .senha(new BCryptPasswordEncoder().encode("1234"))
                .perfil("PADRAO")
                .dataCriacao(LocalDateTime.now())
                .build();
        
        UsuarioRepositorioImpl rep = new UsuarioRepositorioImpl();
        rep.salvar(usuario);
    }
}
