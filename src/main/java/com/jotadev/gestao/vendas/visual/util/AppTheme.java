package com.jotadev.gestao.vendas.visual.util;

import com.jotadev.gestao.vendas.visual.componentes.ModernScrollBarUI;
import javax.swing.*;

public class AppTheme {
    public static void setup() {
        UIManager.put("ScrollBarUI", ModernScrollBarUI.class.getName());
        
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
        UIManager.put("ScrollBar.width", 8);
    }
}