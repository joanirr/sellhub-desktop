package com.jotadev.gestao.vendas.modelo.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

class VendaItemDto {
    private Long produtoId;
    private String produtonome;
    private BigDecimal preco;
    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal total;
}
