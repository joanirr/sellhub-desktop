package com.jotadev.gestao.vendas.modelo.repositorio.impl;

import com.jotadev.gestao.vendas.modelo.entidade.VendaItem;

public class VendaItemRepositorioImpl extends CrudRepositorioImpl<VendaItem> {
    
    public VendaItemRepositorioImpl() {
        super(VendaItem.class);
    }
    
}
