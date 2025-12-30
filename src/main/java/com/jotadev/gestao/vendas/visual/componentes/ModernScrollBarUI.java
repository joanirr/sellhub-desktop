package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {

    private final Color thumbColor = new Color(100, 100, 100, 100);

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        if (!c.isEnabled() || r.width > r.height) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(thumbColor);
        g2.fillRoundRect(r.x, r.y, r.width, r.height, 10, 10);
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
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(5, 30);
    }
}