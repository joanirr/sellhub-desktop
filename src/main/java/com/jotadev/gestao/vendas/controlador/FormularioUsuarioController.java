package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioUsuario;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormularioUsuarioController implements ActionListener {
    
    private FormularioUsuario formularioUsuario;
    private UsuarioServico usuarioServico;
    private TabelaModeloUsuario tabelaModeloUsuario;
    private List<Usuario> usuarios;
    
    private final long PERMISSAO_ID_PARA_SALVAR_USUARIO = 0;
    
    public FormularioUsuarioController(FormularioUsuario formularioUsuario) {
        this.formularioUsuario = formularioUsuario;
        usuarioServico = new UsuarioServico();
        usuarios = usuarioServico.buscarTodos();
        atualizarTabela();
    }
    
    private void atualizarTabela() {
        
        tabelaModeloUsuario = new TabelaModeloUsuario(usuarios);
        formularioUsuario.getTabelaUsuarios().setModel(tabelaModeloUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
    
    
}
