package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import com.jotadev.gestao.vendas.visual.formulario.Dashboard;
import com.jotadev.gestao.vendas.visual.formulario.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class LoginControlador implements ActionListener {
    
    private Login login;
    private UsuarioServico usuarioServico;

    public LoginControlador(Login login) {
        this.login = login;
        usuarioServico = new UsuarioServico();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch (acao) {
            case "login" -> login();
        }
    }    
        private void login() {
            validaCampo();
            String email = login.getCampoDeTextoEmail().getText();
            String senha = String.valueOf(login.getCampoDeSenha().getPassword());
            
            String mensagem = usuarioServico.login(email, senha);
            
            if (mensagem.startsWith("Iniciando")) {
                login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
                login.getPanelCarregar().setVisible(true);
                Optional<Object> usuario = usuarioServico.buscarPeloEmail(email);
                
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        limparCampo();
                        login.setVisible(false);
                        new Dashboard((Usuario) usuario.get()).setVisible(true);
                    } catch (Exception e) {
                    }
                }).start();
                
            } else {
                login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            }
        }
      
        
        private void validaCampo() {
            String email = login.getCampoDeTextoEmail().getText();
            String senha = String.valueOf(login.getCampoDeSenha().getPassword());
            
            if (email.isBlank() || senha.isBlank()) {
                String mensagem = "Email e Senha são obrigatórios!";
                login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
                throw new RuntimeException(mensagem);
            }
        }
        
        private void limparCampo() {
            login.getCampoDeTextoEmail().setText("");
            login.getCampoDeSenha().setText("");
        }
}