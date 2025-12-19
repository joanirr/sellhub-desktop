package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.servico.ProdutoServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioProduto;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormularioProdutoController implements ActionListener {
    
    private FormularioProduto formularioProduto;
    private ProdutoServico produtoServico;
    private TabelaModeloProduto tabelaModeloProduto;

    public FormularioProdutoController(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        
        produtoServico = new ProdutoServico();
        atualizarTabelaProduto();
    }
    
    private void atualizarTabelaProduto() {
        tabelaModeloProduto = new TabelaModeloProduto(produtoServico.buscarTodos());
        formularioProduto.getTabelaProduto().setModel(tabelaModeloProduto);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String acao = e.getActionCommand().toLowerCase();
       
       switch(acao) {
           case "adicionar" -> { mostrarTelaDeCadastroProdutoCategoria(); }
       }
    }
    
    public void mostrarTelaDeCadastroProdutoCategoria() {
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
    }
 
}
