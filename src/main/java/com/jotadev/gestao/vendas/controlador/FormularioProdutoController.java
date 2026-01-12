package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.modelo.dto.ProdutoDto;
import com.jotadev.gestao.vendas.modelo.entidade.Categoria;
import com.jotadev.gestao.vendas.modelo.entidade.Produto;
import com.jotadev.gestao.vendas.modelo.servico.CategoriaServico;
import com.jotadev.gestao.vendas.modelo.servico.PermissaoServico;
import com.jotadev.gestao.vendas.modelo.servico.ProdutoServico;
import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import com.jotadev.gestao.vendas.visual.formulario.FormularioProduto;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloCategoria;
import com.jotadev.gestao.vendas.visual.modelo.TabelaModeloProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.swing.JOptionPane;


public class FormularioProdutoController implements ActionListener, MouseListener {
    
    private FormularioProduto formularioProduto;
    private ProdutoServico produtoServico;
    private TabelaModeloProduto tabelaModeloProduto;
    
    private CategoriaServico categoriaServico;
    private TabelaModeloCategoria tabelaModeloCategoria;
    private Long categoriaId = null;
    private Long produtoId = null;
    
    private PermissaoServico permissaoServico;
    
    private final long PERMISSAO_ID_PARA_SALVAR_PRODUTO = 9;
    private final long PERMISSAO_ID_PARA_REMOVER_PRODUTO = 10;
    
//    private final long PERMISSAO_ID_PARA_BUSCAR_TODOS = 3;
//    private final long PERMISSAO_ID_PARA_REMOVER = 4;
//    private final long PERMISSAO_ID_PARA_SALVAR_PERMISSAO = 16;

    public FormularioProdutoController(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        permissaoServico = new PermissaoServico();
        
        atualizarTabelaProduto();
        atualizarTabelaCategoria();
    }
    
    private void atualizarTabelaProduto() {
        tabelaModeloProduto = new TabelaModeloProduto(produtoServico.buscarTodos());
        formularioProduto.getTabelaProduto().setModel(tabelaModeloProduto);
    }
    
    public void atualizarTabelaCategoria() {
        tabelaModeloCategoria = new TabelaModeloCategoria(categoriaServico.buscarTodos());
        formularioProduto.getTela().getProdutoCategoria().getTabelaCategoria().setModel(tabelaModeloCategoria);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String acao = e.getActionCommand().toLowerCase();
       
       switch(acao) {
           case "adicionar" -> { mostrarTelaDeCadastroProdutoCategoria(); break;}
           case "atualizar" -> { mostrarTelaDeCadastroParaAtualizar(); break;}
           case "remover" -> { removerProduto(); break;}
       }
       
       salvarCategoria();
       salvarProduto();
    }
    
    public void mostrarTelaDeCadastroProdutoCategoria() {
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
        limparCamposProduto();
        limparCamposCategoria();
    }
    
    private void mostrarTelaDeCadastroParaAtualizar() {
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());
        selecionarProdutoNaTabela();
        formularioProduto.getDashboard().setForm(formularioProduto.getTela());
    }
    
    private void selecionarProdutoNaTabela() {
        if (produtoId == null) {
            String mensagem = "Selecione um produto na tabela.";
            JOptionPane.showMessageDialog(null, mensagem, "Produto não selecionado", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void removerProduto() {
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_REMOVER_PRODUTO, formularioProduto.getUsuarioId());
        selecionarProdutoNaTabela();
        produtoServico.remover(produtoId);
        atualizarTabelaProduto();
        limparCamposProduto();
    }
    
    private void salvarCategoria() {
       formularioProduto.getTela().getProdutoCategoria().getBotaoCategoria().addActionListener(e -> {
           String nome = formularioProduto.getTela().getProdutoCategoria().getTextoNomeCategoria().getText();
           String descricao = formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoCategoria().getText();
           
           // Validação
           validacaoDeCampoVazio(nome);
           
           Categoria categoria = Categoria.builder()
                   .id(categoriaId)
                   .nome(nome)
                   .descricao(descricao)
                   .build();
           
           String mensagem = categoriaServico.salvar(categoria);
           
           if (mensagem.startsWith("Categoria")) {
               formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);
               atualizarTabelaCategoria();
               limparCamposCategoria();
               formularioProduto.getTela().getProdutoCategoria().preencherComboBoxCategoria();
           }
           else formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
       });
    }
    
    public void limparCamposCategoria() {
        categoriaId = null;
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeCategoria().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoCategoria().setText("");
        atualizarTabelaCategoria();
    }
    
    private void validacaoDeCampoVazio(String texto) {
        if (texto.isBlank()) {
            String mensagem = "Campo obrigatório!";
            formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public TabelaModeloCategoria getTabelaModeloCategoria() {
        return tabelaModeloCategoria;
    }
    
    private void salvarProduto() {
        permissaoServico.validarPermissao(PERMISSAO_ID_PARA_SALVAR_PRODUTO, formularioProduto.getUsuarioId());

        for (java.awt.event.ActionListener al : formularioProduto.getTela().getProdutoCategoria().getBotaoProduto().getActionListeners()) {
            formularioProduto.getTela().getProdutoCategoria().getBotaoProduto().removeActionListener(al);
        }

        formularioProduto.getTela().getProdutoCategoria().getBotaoProduto().addActionListener(e -> {
            String nome = formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().getText();
            String descricao = formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().getText();
            String precoTemp = formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().getText();

            if (nome.isEmpty() || precoTemp.isEmpty()) {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Preencha os campos obrigatórios!");
                return;
            }
            validarComboCategoriaProduto();

            BigDecimal preco = BigDecimal.ZERO;
            try {
                preco = new BigDecimal(precoTemp.replace(",", "."));
            } catch (Exception erro) {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, "Preço inválido!");
                return; 
            }

            int quantidadeParaSalvar = 0;
            if (produtoId != null) {
                quantidadeParaSalvar = produtoServico.buscarPeloId(produtoId)
                        .map(Produto::getQuantidade)
                        .orElse(0);
            }

            Produto produto = Produto.builder()
                    .id(produtoId)
                    .nome(nome)
                    .descricao(descricao)
                    .preco(preco)
                    .quantidade(quantidadeParaSalvar)
                    .categoriaId(categoriaId)
                    .dataCriacao(LocalDateTime.now())
                    .usuarioId(formularioProduto.getUsuarioId())
                    .build();

            String mensagem = produtoServico.salvar(produto);

            if (mensagem.startsWith("Produto")) {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.SUCESSO, mensagem);

                this.produtoId = null; 

                atualizarTabelaProduto();
                limparCamposProduto();
            } else {
                formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
            }
        });
    }
    
    private void validarComboCategoriaProduto() {
        if (formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedIndex() == 0) {
            mensagemDeErroProduto("Selecione a categoria.");
        }
        String categoriaNome = formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedItem().toString();
        Optional<Categoria> categoriaTemp = categoriaServico.buscarPeloNome(categoriaNome);
            
        if (categoriaTemp.isPresent()) categoriaId = categoriaTemp.get().getId();
        else {
        }
    }
    
    private void mensagemDeErroProduto(String mensagem) {
        formularioProduto.getTela().getMensagemUtil().mostrarMensagem(Mensagem.TipoMensagem.ERRO, mensagem);
        throw new RuntimeException(mensagem);
    }
    
    public void limparCamposProduto() {
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().setText("");
        formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedIndex(0);
    }
    
    private void preencherCamposProduto(ProdutoDto produto) {
        formularioProduto.getTela().getProdutoCategoria().getTextoNomeProduto().setText(produto.getNome());
        formularioProduto.getTela().getProdutoCategoria().getTextoDescricaoProduto().setText(produto.getDescricao());
        formularioProduto.getTela().getProdutoCategoria().getTextoPrecoProduto().setText(produto.getPreco().toString());
        formularioProduto.getTela().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedItem(produto.getCategoria());
        produtoId = produto.getId();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int linha = formularioProduto.getTabelaProduto().getSelectedRow();
        ProdutoDto produtoTemp = tabelaModeloProduto.getProdutos().get(linha);
        preencherCamposProduto(produtoTemp);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

