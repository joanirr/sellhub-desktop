package com.jotadev.gestao.vendas.controlador;

import com.jotadev.gestao.vendas.visual.formulario.FormularioVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioVendaController implements ActionListener {
    
    private FormularioVenda formularioVenda;

    public FormularioVendaController(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String acao = e.getActionCommand().toLowerCase();
        
        switch(acao) {
            case "adicionar" -> { mostrarTelaDeVenda(); break; }
        }
    }
    
    private void mostrarTelaDeVenda() {
        formularioVenda.getDialogVenda().pack();
        formularioVenda.getDialogVenda().setLocationRelativeTo(null);
        formularioVenda.getDialogVenda().setVisible(true);
    }
}
