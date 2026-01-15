package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.entidade.Permissao;
import com.jotadev.gestao.vendas.modelo.entidade.PermissaoUsuario;
import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.PermissaoServico;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioUsuario;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class FormularioUsuarioController implements ActionListener, MouseListener {
    
    private FormularioUsuario formularioUsuario;
    private UsuarioServico usuarioServico;
    private PermissaoServico permissaoServico;
    private TabelaModeloUsuario tabelaModeloUsuario;
    private List<Usuario> usuarios;
    private String urlFoto;
    private Long atualizarUsuario;
    private Long usuarioLogado;
    private Usuario usuario;
    
    private final long PERMISSAO_ID_PARA_SALVAR_USUARIO = 1;
    private final long PERMISSAO_ID_PARA_SOMENTE_SEU = 2;
    private final long PERMISSAO_ID_PARA_BUSCAR_TODOS = 3;
    private final long PERMISSAO_ID_PARA_REMOVER = 4;
    private final long PERMISSAO_ID_PARA_SALVAR_PERMISSAO = 16;
    private String acao;
    
    public FormularioUsuarioController(FormularioUsuario formularioUsuario) {
        this.formularioUsuario = formularioUsuario;
        usuarioServico = new UsuarioServico();
        permissaoServico = new PermissaoServico();
        this.usuarioLogado = formularioUsuario.getUsuarioId();
        this.formularioUsuario.getBotaoSelecionarArquivo().addActionListener(this);
        javax.swing.Timer timer = new javax.swing.Timer(30000, (e) -> {
            atualizarTabela();
        });
        timer.start();
    }
    
    private void atualizarTabela() {
        usuarios = usuarioServico.buscarTodos();
        
        java.awt.EventQueue.invokeLater(() -> {
            tabelaModeloUsuario = new TabelaModeloUsuario(usuarios);
            formularioUsuario.getTabelaUsuarios().setModel(tabelaModeloUsuario);

            formularioUsuario.getTabelaUsuarios().revalidate();
            formularioUsuario.getTabelaUsuarios().repaint();
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTela("ADICIONAR USUÁRIO"); }
            case "atualizar" -> { atualizarTela("ATUALIZAR USUÁRIO"); }
            case "salvar" -> { salvarUsuario(); }
            case "permissoes" -> { permissao(); }
            case "salvarpermissao" -> { salvarPermissao(); }
            case "selecionar" -> { escolherArquivo(); }
            case "remover" -> { remover(); }
            case "imprimir" -> { imprimirUsuario(); }
        }
    }
    
    private void mostrarTela(String titulo){
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_USUARIO, usuarioLogado);
        limparCamposUsuario();
        limparMensagem();
        formularioUsuario.getDialogUsuario().pack();
        formularioUsuario.getDialogUsuario().setLocationRelativeTo(null);
        formularioUsuario.getDialogUsuario().setVisible(true);
        formularioUsuario.getLabelTitulo().setText(titulo);
    }
    
    private void atualizarTela(String titulo){
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_USUARIO, usuarioLogado);
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
                atualizarFotoNoMenu(formularioUsuario.getTxtFoto().getText());
                mensagem(true, "Usuário salvo com sucesso!");
                limparCamposUsuario();
                atualizarTabela();
            }
            else
                mensagem(false, "Erro ao cadastrar usuário.");
        } catch (Exception e) {
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
        int retorno = fileChooser.showOpenDialog(formularioUsuario);

        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
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
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_REMOVER, usuarioLogado);
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
                usuario = null;
            } else {
            JOptionPane.showMessageDialog(null, "Erro ao remover usuário!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void permissao() {
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_PERMISSAO, usuarioLogado);
        if (atualizarUsuario == null) {
            String mensagem = "Selecione um usuário na tabela.";
            JOptionPane.showMessageDialog(null, mensagem);
            throw new RuntimeException(mensagem);
        }

        limparPermissoes();
        preencherPermissao();
        formularioUsuario.getDialogPermissao().pack();
        formularioUsuario.getDialogPermissao().setLocationRelativeTo(null);
        formularioUsuario.getDialogPermissao().setVisible(true);
    }
    
    private void preencherPermissao() {
        List<JCheckBox> lista = formularioUsuario.listaChecks();
        
        Map<String, JCheckBox> map = new HashMap<>();
        
        for(JCheckBox checkBox : lista) {
            map.put(checkBox.getName(), checkBox);
        }
        
        permissaoServico.buscarTodasPermissoesUsuario(atualizarUsuario)
                .stream()
                .forEach(p -> {
                    Optional<Permissao> permissaoOp = permissaoServico.buscarPermissaoPeloId(p.getPermissaoId());
                    
                    if (permissaoOp.isPresent()) {
                        Permissao permissao = permissaoOp.get();
                        JCheckBox checkBox = map.get(permissao.getNome());
                    
                        if (checkBox != null) {
                            checkBox.setToolTipText(permissao.getDescricao());
                            checkBox.setSelected(true);
                        }
                    }
                });
    }
    
    private void limparPermissoes() {
        List<JCheckBox> lista = formularioUsuario.listaChecks();
        
        for(JCheckBox checkBox : lista) {
            checkBox.setSelected(false);
        }
    }
    
    private void salvarPermissao() {
        Long usuarioId = atualizarUsuario;
        List<JCheckBox> checkBoxs = formularioUsuario.listaChecks();
        
        List<PermissaoUsuario> adicionarPermissao = new ArrayList<>();
        List<PermissaoUsuario> removerPermissao = new ArrayList<>();
        
        for (JCheckBox checkBox : checkBoxs) {
            String nome = checkBox.getName();
            
            Optional<Permissao> permissoes = permissaoServico.buscarPermissaoPeloNome(nome);
            if (permissoes.isPresent()) {
                Permissao permissao = permissoes.get();
                PermissaoUsuario permissaoUsuario = PermissaoUsuario.builder()
                        .permissaoId(permissao.getId())
                        .usuarioId(usuarioId)
                        .build();
                
                if (checkBox.isSelected()) {
                    adicionarPermissao.add(permissaoUsuario);
                } else {
                    removerPermissao.add(permissaoUsuario);
                }
            }
        }
            
            adicionarPermissao.stream()
                    .filter(pu -> permissaoServico.buscarPermissaoUsuario(pu).isEmpty())
                    .forEach(pu -> permissaoServico.salvar(pu));
            
            removerPermissao.stream()
                    .filter(pu -> permissaoServico.buscarPermissaoUsuario(pu).isPresent())
                    .forEach(pu -> permissaoServico.remover(pu));
            
            JOptionPane.showMessageDialog(null, "Permissão alterada com sucesso!");
    }
    
    private void imprimirUsuario() {
        int linhaSelecionada = formularioUsuario.getTabelaUsuarios().getSelectedRow();

        try {
            if (linhaSelecionada == -1) {
                MessageFormat header = new MessageFormat("Relatório Geral de Usuários do Sistema");
                MessageFormat footer = new MessageFormat("Página {0,number,integer}");

                boolean imprimir = formularioUsuario.getTabelaUsuarios().print(
                    JTable.PrintMode.FIT_WIDTH, 
                    header, 
                    footer
                );

                if (imprimir) {
                    JOptionPane.showMessageDialog(null, "Relatório geral impresso com sucesso!");
                }

            } else {
                Long id = (Long) formularioUsuario.getTabelaUsuarios().getValueAt(linhaSelecionada, 0);

                String fichaCompleta = usuarioServico.gerarFichaDetalhadaUsuario(id);

                JTextArea areaImpressao = new JTextArea(fichaCompleta);
                areaImpressao.setFont(new Font("Monospaced", Font.PLAIN, 9));

                boolean ok = areaImpressao.print(
                    new MessageFormat("Perfil do Usuário #" + id),
                    new MessageFormat("Sistema de Gestão - Sigiloso"),
                    true, null, null, true
                );

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Ficha do usuário impressa com sucesso!");
                }
            }
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(null, "Falha na impressão: " + e.getMessage());
        }
    }
    
    private void atualizarFotoNoMenu(String caminho) {
    if (caminho == null || caminho.isEmpty()) return;

    java.awt.Window janela = javax.swing.SwingUtilities.getWindowAncestor(formularioUsuario);

    if (janela instanceof com.jotadev.gestao.vendas.visual.formulario.Dashboard principal) {
        
        try {
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(caminho);
            
            principal.getMenu1().getImageAvatar1().setImage(icon);

            principal.getMenu1().getImageAvatar1().repaint();
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }
}
    
}
