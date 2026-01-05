package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import com.jotadev.gestao.vendas.modelo.entidade.Usuario;
import com.jotadev.gestao.vendas.modelo.servico.CategoriaServico;
import com.jotadev.gestao.vendas.modelo.servico.ProdutoServico;
import com.jotadev.gestao.vendas.modelo.servico.UsuarioServico;
import com.jotadev.gestao.vendas.modelo.servico.VendaServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioVenda;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloVenda;
import com.jotadev.gestao.vendas.modelo.util.MensagemUtil;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FormularioVendaController implements ActionListener {
    
    private FormularioVenda formularioVenda;
    private VendaServico vendaServico;
    private UsuarioServico usuarioServico;
    private CategoriaServico categoriaServico;
    private TabelaModeloVenda tabelaModeloVenda;
    private ProdutoServico produtoServico;

    public FormularioVendaController(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
        this.vendaServico = new VendaServico();
        usuarioServico = new UsuarioServico();
        categoriaServico = new CategoriaServico();
        produtoServico = new ProdutoServico();
        atualizarTabelaVenda();
    }
    
    private void atualizarTabelaVenda() {
        // se o usuário possui permissão de adm
        Optional<Usuario> usuario = usuarioServico.buscarPeloId(formularioVenda.getUsuarioId());
        List<VendaDto> lista = new ArrayList<>();
        
        if(usuario.get().getPerfil().equalsIgnoreCase("ADMIN")) {
            lista =  vendaServico.buscarTodos();
        } else {
            
        }
        
        tabelaModeloVenda = new TabelaModeloVenda(lista);
        formularioVenda.getTabelaVendas().setModel(tabelaModeloVenda);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeVenda(); break; }
        }
    }
    
    private void mostrarTelaDeVenda() {
        for(ActionListener al : formularioVenda.getTextoBuscarProdutoPeloID().getActionListeners()) {
        formularioVenda.getTextoBuscarProdutoPeloID().removeActionListener(al);
        }

        formularioVenda.getTextoBuscarProdutoPeloID().addActionListener(e -> {
            System.out.println("Evento disparado! Buscando ID: " + formularioVenda.getTextoBuscarProdutoPeloID().getText()); // Teste de console
            buscarProdutoPeloId();
        });

        formularioVenda.getDialogVenda().pack();
        formularioVenda.getDialogVenda().setLocationRelativeTo(null);
        formularioVenda.getDialogVenda().setVisible(true);
        preencherComboBoxCategoria();
    }
    
    private void preencherComboBoxCategoria() {
        formularioVenda.getComboBoxCategoria().removeAllItems();
        formularioVenda.getComboBoxProduto().removeAllItems();
        
        formularioVenda.getComboBoxCategoria().addItem("Selecione a categoria");
        formularioVenda.getComboBoxProduto().addItem("Selecione o produto");
        
        categoriaServico.buscarTodos()
                .stream()
                .forEach(c -> formularioVenda.getComboBoxCategoria().addItem(c.getNome()));
    }
    
    private void buscarProdutoPeloId() {
        String idTexto = formularioVenda.getTextoBuscarProdutoPeloID().getText().trim();
        
        if (idTexto.isEmpty()) return;
        
        try {
            Long id = Long.parseLong(idTexto);
            Optional<Produto> produtoOpt = produtoServico.buscarPeloId(id);
            
            if (produtoOpt.isPresent()) {
                Produto p = produtoOpt.get(); // extrai o produto de dentro do optional
                
                formularioVenda.getLabelNomeDoProduto().setText(p.getNome());
                formularioVenda.getLabelPrecoProduto().setText(p.getPreco().toString());
                formularioVenda.getLabelEstoqueQuantidade().setText(String.valueOf(p.getQuantidade()));
                formularioVenda.getTextoQuantidade().requestFocus();
            } else {
                formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Produto não encontrado!");
            }
        } catch (NumberFormatException e) {
            formularioVenda.getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Digite um ID numérico válido!");
        }
    }
}
