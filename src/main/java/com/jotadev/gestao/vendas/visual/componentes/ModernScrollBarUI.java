package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {

    private final Color thumbColor = new Color(150, 150, 150, 80);
    private final Color thumbHoverColor = new Color(120, 120, 120, 150);
    private final int SCROLL_BAR_WIDTH = 8;

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        if (!c.isEnabled()) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        Color color = isDragging ? thumbHoverColor : thumbColor;

        g2.setColor(color);
        g2.fillRoundRect(r.x + 2, r.y, r.width - 4, r.height, 10, 10);
        g2.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        return button;
    }
    
    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension dim = super.getPreferredSize(c);
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            dim.width = SCROLL_BAR_WIDTH;
        } else {
            dim.height = SCROLL_BAR_WIDTH;
        }
        return dim;
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(SCROLL_BAR_WIDTH, 40);
    }
}