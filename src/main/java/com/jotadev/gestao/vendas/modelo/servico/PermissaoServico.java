package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.entidade.Permissao;
import com.jotadev.gestao.vendas.modelo.entidade.PermissaoUsuario;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.PermissaoRepositorioImpl;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.PermissaoUsuarioRepositorioImpl;
import java.util.List;
import java.util.Optional;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;


public class PermissaoServico {
    
    private PermissaoRepositorioImpl permissaoRepositorioImpl;
    private PermissaoUsuarioRepositorioImpl permissaoUsuarioRepositorioImpl;
    
    
    public  PermissaoServico() {
        permissaoRepositorioImpl = new PermissaoRepositorioImpl();
        permissaoUsuarioRepositorioImpl = new PermissaoUsuarioRepositorioImpl();
    }
    
    public void validarPermissao(Long permissaoId, Long usuarioId) {
        System.out.println(String.format("Permissão ID %s Usuario Id %s", permissaoId, usuarioId));
        PermissaoUsuario permissaoUsuario = PermissaoUsuario.builder()
                .permissaoId(permissaoId)
                .usuarioId(usuarioId)
                .build();
        Optional<PermissaoUsuario> lista = buscarPermissaoUsuario(permissaoUsuario);
        System.out.println("LIST: " + lista);
        if(lista.isEmpty()) {
            String mensagem = "Sem Permissao";
            JOptionPane.showMessageDialog(null, mensagem, mensagem, JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
    }
    
    public Optional<PermissaoUsuario> buscarPermissaoUsuario(PermissaoUsuario permissaoUsuario) {
       return permissaoUsuarioRepositorioImpl.buscarPelaPermissaoIdEUsuarioId(permissaoUsuario);
    }
    
    
    public List<Permissao> buscarTodasPermissoes(Long atualizarUsuario) {
        return permissaoRepositorioImpl.buscarTodos();
    }
    
    public List<PermissaoUsuario> buscarTodasPermissoesUsuario(Long usuarioId){
        return permissaoUsuarioRepositorioImpl.buscarPeloUsuarioId(usuarioId);
    }
    
    public Optional<Permissao> buscarPermissaoPeloId(Long permissaoId) {
        return permissaoRepositorioImpl.buscarPeloId(permissaoId);
    }
    
    public boolean salvar(PermissaoUsuario permissaoUsuario) {
        System.out.println("PermissaoUsuario: " + permissaoUsuario);
        return permissaoUsuarioRepositorioImpl.salvar(permissaoUsuario);
    }
    
    public Optional<Permissao> buscarPermissaoPeloNome(String nome) {
        return permissaoRepositorioImpl.buscarPeloNome(nome);
    }
    
    public boolean remover(PermissaoUsuario permissaoUsuario) {
        return permissaoUsuarioRepositorioImpl.removerPeloId(permissaoUsuario);
    }
}