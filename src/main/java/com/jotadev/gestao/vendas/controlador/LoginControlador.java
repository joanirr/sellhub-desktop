package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import com.jotadev.gestao.vendas.visual.formulario.Dashboard;
import com.jotadev.gestao.vendas.visual.formulario.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            
            if (mensagem.startsWith("Email e Senha")) {
                login.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
                login.getPanelCarregar().setVisible(true);
                
                new Thread(() -> {
                    limparCampo();
                    login.setVisible(false);
                    new Dashboard().setVisible(true);
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
