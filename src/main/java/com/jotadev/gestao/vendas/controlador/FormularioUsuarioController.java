package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioUsuario;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FormularioUsuarioController implements ActionListener, MouseListener {
    
    private FormularioUsuario formularioUsuario;
    private UsuarioServico usuarioServico;
    private TabelaModeloUsuario tabelaModeloUsuario;
    private List<Usuario> usuarios;
    private String urlFoto;
    private Long atualizarUsuario;
    private Usuario usuario;
    
    private final long PERMISSAO_ID_PARA_SALVAR_USUARIO = 0;
    
    public FormularioUsuarioController(FormularioUsuario formularioUsuario) {
        this.formularioUsuario = formularioUsuario;
        usuarioServico = new UsuarioServico();
        
        atualizarTabela();
    }
    
    private void atualizarTabela() {
        usuarios = usuarioServico.buscarTodos();
        tabelaModeloUsuario = new TabelaModeloUsuario(usuarios);
        formularioUsuario.getTabelaUsuarios().setModel(tabelaModeloUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTela("ADICIONAR USUÁRIO"); }
            case "atualizar" -> { atualizarTela("ATUALIZAR USUÁRIO"); }
            case "salvar" -> { salvarUsuario(); }
            case "selecione" -> { escolherArquivo(); }
            case "remover" -> { remover(); }
        }
    }
    
    private void mostrarTela(String titulo){
        limparCamposUsuario();
        limparMensagem();
        formularioUsuario.getDialogUsuario().pack();
        formularioUsuario.getDialogUsuario().setLocationRelativeTo(null);
        formularioUsuario.getDialogUsuario().setVisible(true);
        formularioUsuario.getLabelTitulo().setText(titulo);
    }
    
    private void atualizarTela(String titulo){
        if (usuario == null) {
            String mensagem = "Selecione o usuário na tabela.";
            JOptionPane.showMessageDialog(null, mensagem, "Selecione a tabela.", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
            
        limparMensagem();
        formularioUsuario.getDialogUsuario().pack();
        formularioUsuario.getDialogUsuario().setLocationRelativeTo(null);
        formularioUsuario.getDialogUsuario().setVisible(true);
        formularioUsuario.getLabelTitulo().setText(titulo);
        preencherFormularioCadastro(usuario);
    }
    
    private void salvarUsuario() {
        String nome = formularioUsuario.getTxtNome().getText();
        String email = formularioUsuario.getTxtEmail().getText();
        String senha = formularioUsuario.getTxtSenha().getText();
        String perfil = formularioUsuario.getComboPerfil().getSelectedItem().toString();
        
        validacaoTexto(nome);
        validacaoTexto(email);
        validacaoTexto(senha);
        validacaoPerfil();
        
        try {
            Usuario usuario = Usuario.builder()
                    .id(atualizarUsuario)
                    .nome(nome)
                    .email(email)
                    .senha(senha)
                    .perfil(perfil)
                    .foto(urlFoto)
                    .estado(formularioUsuario.getRadioAtivo().isSelected())
                    .dataCriacao(LocalDateTime.now())
                    .build();
            
            boolean resultado = usuarioServico.salvar(usuario);
            
            if (resultado) {
                mensagem(true, "Usuário salvo com sucesso!");
                limparCamposUsuario();
            }
            else
                mensagem(false, "Erro ao cadastrar usuário.");
        } catch (Exception e) {
            System.out.println(e);
            mensagem(false, e.getMessage());
        }
    }
    
    private void validacaoTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            String mensagem = "Todos os campos são obrigatórios!";
            mensagem(false, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void validacaoPerfil() {
        if (formularioUsuario.getComboPerfil().getSelectedIndex() < 1) {
            String mensagem = "A seleção do perfil é obrigatória!";
            mensagem(false, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void mensagem(boolean bool,String mensagem) {
        if (bool) {
            formularioUsuario.getLabelMensagem().setBackground(Color.decode("#45B649"));
        } else {
            formularioUsuario.getLabelMensagem().setBackground(Color.decode("#93291E"));
        }
        
        formularioUsuario.getLabelMensagem().setOpaque(true);
        formularioUsuario.getLabelMensagem().setText(mensagem);
    }
    
    private void limparMensagem() {
        formularioUsuario.getLabelMensagem().setBackground(Color.WHITE);
        formularioUsuario.getLabelMensagem().setOpaque(false);
    }
    
    private void escolherArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int retorno = fileChooser.showDialog(null, "Selecione");
        
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile().getAbsoluteFile();
            urlFoto = file.getAbsolutePath();
            formularioUsuario.getTxtFoto().setText(urlFoto);
        }
    }
    
    private void limparCamposUsuario() {
        formularioUsuario.getTxtNome().setText("");
        formularioUsuario.getTxtEmail().setText("");
        formularioUsuario.getTxtSenha().setText("");
        formularioUsuario.getTxtFoto().setText("");
        formularioUsuario.getComboPerfil().setSelectedIndex(0);
        formularioUsuario.getRadioAtivo().setSelected(true);
        urlFoto = "";
        atualizarUsuario = null;
        atualizarTabela();
    }
    
    private void preencherFormularioCadastro(Usuario usuario) {
        atualizarUsuario = usuario.getId();
        formularioUsuario.getTxtNome().setText(usuario.getNome());
        formularioUsuario.getTxtEmail().setText(usuario.getEmail());
        formularioUsuario.getTxtSenha().setText("");
        formularioUsuario.getTxtFoto().setText(usuario.getFoto());
        
        if (usuario.getPerfil().equalsIgnoreCase("admin"))
            formularioUsuario.getComboPerfil().setSelectedIndex(1);
        else
            formularioUsuario.getComboPerfil().setSelectedIndex(2);
        
        if (usuario.getEstado())
            formularioUsuario.getRadioAtivo().setSelected(true);
        else
            formularioUsuario.getRadioInativo().setSelected(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int linha = formularioUsuario.getTabelaUsuarios().getSelectedRow();
        usuario = usuarios.get(linha);
        atualizarUsuario = usuario.getId();
        //preencherFormularioCadastro(usuario);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void remover() {
        if (usuario == null) {
            String mensagem = "Selecione o usuário na tabela.";
            JOptionPane.showMessageDialog(null, mensagem, "Selecione a tabela.", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
        
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?\n\n" 
                + "Nome: " + usuario.getNome(), "Remover usuário.", JOptionPane.YES_NO_OPTION);
        
        if (confirmar == JOptionPane.YES_OPTION) {
            boolean resultado = usuarioServico.remover(usuario.getId());
            if (resultado) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                atualizarTabela();
            } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover usuário!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
