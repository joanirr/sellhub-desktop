package com.jotadev.gestao.vendas.modelo.servico;

import com.jotadev.gestao.vendas.modelo.dto.VendaDto;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaItemRepositorioImpl;
import com.jotadev.gestao.vendas.modelo.repositorio.impl.VendaRepositorioImpl;
import java.util.List;

public class VendaServico {
    
    private VendaRepositorioImpl vendaRepositorioImpl;
    private VendaItemRepositorioImpl vendaItemRepositorioImpl;
    
    public VendaServico() {
        vendaRepositorioImpl = new VendaRepositorioImpl();
        vendaItemRepositorioImpl = new VendaItemRepositorioImpl();
    }
    
    public List<VendaDto> buscarTodos() {
        return vendaRepositorioImpl.encontrarTodosPersonalizado();
    }
}
