package com.jotadev.gestao.vendas.visual.modelo;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MenuModelo {
    private String icon;
    private String nome;
    private TipoMenu tipoMenu;
    
    public  enum TipoMenu {
        MENU, TITULO, VAZIO;
    }
    
    public Icon toIcon() {
        return new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\jotadev\\gestao\\vendas\\visual\\icon\\" + icon + ".png");
    }
}
