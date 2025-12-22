package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.UsuarioRepositorioImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioServico {
    
    private UsuarioRepositorioImpl usuarioRepositorio;

    public UsuarioServico() {
        usuarioRepositorio = new UsuarioRepositorioImpl();
    }
    
    public String login(String email, String senha) {
        Optional usuarioOptional = buscarPeloEmail(email);
        
        if (usuarioOptional.isPresent()) {
            Usuario usuario = (Usuario) usuarioOptional.get();
            
            if (validaSenha(senha, usuario.getSenha())) {
                if (!usuario.getEstado())
                    return "Usuario bloqueado. Validar com o admin.";
                
                return "Iniciando login...";
                }
            return "Email ou Senha incorreta.";
        }
        return "Usuario inexistente.";
    }
    
    public boolean validaSenha(String senhaInserida, String senhaUsuario) {
        return new BCryptPasswordEncoder().matches(senhaInserida, senhaUsuario);
    }
    
    public List<Usuario> buscarTodos() {
        return usuarioRepositorio.buscarTodos();
    }
    
    public Optional<Usuario> buscarPeloId(Long id) {
        return usuarioRepositorio.buscarPeloId(id);
    }
    
    public Optional<Object> buscarPeloEmail(String email) {
        return usuarioRepositorio.buscarPeloEmail(email);
    }
    
    public boolean salvar(Usuario usuario) {
        
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        
        return usuarioRepositorio.salvar(usuario);
    }
    
    public boolean remover(Long id) {
        return usuarioRepositorio.removerPeloId(id);
    }
}
