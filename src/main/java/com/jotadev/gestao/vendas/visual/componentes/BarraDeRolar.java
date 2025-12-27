package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class BarraDeRolar extends JScrollBar {
    
    public BarraDeRolar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setBackground(new Color(45, 45, 45));
        setUnitIncrement(20);
    }
}
