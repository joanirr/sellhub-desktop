package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.servico.EstoqueServico;
import com.jotadev.gestao.vendas.modelo.servico.ProdutoServico;
import com.jotadev.gestao.vendas.modelo.servico.VendaServico;
import com.jotadev.gestao.vendas.visual.formulario.FormularioPrincipal;

public class FormularioPrincipalController {
    private FormularioPrincipal view;
    private ProdutoServico produtoServico;
    private VendaServico vendaServico;
    private EstoqueServico estoqueServico;

    public FormularioPrincipalController(FormularioPrincipal view) {
        this.view = view;
        this.produtoServico = new ProdutoServico();
        this.vendaServico = new VendaServico();
        this.estoqueServico = new EstoqueServico();
        atualizarDash();
    }

    public void atualizarDash() {
        long p = produtoServico.contarTotalProdutos();
        long v = vendaServico.buscarTodos().size(); 
        long e = estoqueServico.somarQuantidadeTotalEstoque(); 

        view.atualizarDadosCartoes(p, v, e);
    }
}