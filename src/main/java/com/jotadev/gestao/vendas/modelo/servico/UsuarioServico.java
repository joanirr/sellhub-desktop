package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.UsuarioRepositorioImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioServico {
    
    private UsuarioRepositorioImpl usuarioRepositorio;
    private com.jotadev.gestao.vendas.modelo.servico.PermissaoServico permissaoServico = new com.jotadev.gestao.vendas.modelo.servico.PermissaoServico();

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
    
    public String gerarFichaDetalhadaUsuario(Long usuarioId) {
        Usuario usuario = buscarPeloId(usuarioId).orElse(null);
        if (usuario == null) return "Usuário não encontrado.";

        StringBuilder ficha = new StringBuilder();
        ficha.append("      FICHA INDIVIDUAL DE USUÁRIO      \n");
        ficha.append("---------------------------------------\n");
        ficha.append("ID:    ").append(usuario.getId()).append("\n");
        ficha.append("NOME:  ").append(usuario.getNome().toUpperCase()).append("\n");
        ficha.append("EMAIL: ").append(usuario.getEmail()).append("\n");
        ficha.append("PERFIL:").append(usuario.getPerfil().toUpperCase()).append("\n");

        String status = (usuario.getEstado() != null && usuario.getEstado()) ? "ATIVO" : "BLOQUEADO";
        ficha.append("STATUS:").append(status).append("\n");
        ficha.append("---------------------------------------\n");
        ficha.append("PERMISSÕES CONCEDIDAS:\n");

        var permissoesRelacionamento = permissaoServico.buscarTodasPermissoesUsuario(usuarioId);

        if (permissoesRelacionamento.isEmpty()) {
            ficha.append("- Nenhuma permissão específica.\n");
        } else {
            for (var relacao : permissoesRelacionamento) {
                permissaoServico.buscarPermissaoPeloId(relacao.getPermissaoId()).ifPresent(p -> {
                    ficha.append("- ").append(p.getNome()).append("\n");
                });
            }
        }

        ficha.append("---------------------------------------\n");
        ficha.append("Emissão: ").append(java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))).append("\n");

        return ficha.toString();
    }
}
