package com.jotadev.gestao.vendas.visual.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Botao extends JButton {
    private Animator animacao;
    private int tamanhoDoAlvo;
    private float tamanhoDaAnimacao;
    private Point ponto;
    private float alfa;
    private Color corDeEfeito = new Color(255, 255, 255, 100);
    
    public Botao() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(new Color(26, 188, 254));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tamanhoDoAlvo = Math.max(getWidth(), getHeight() * 2);
                
                tamanhoDaAnimacao = 0;
                ponto = e.getPoint();
                
                alfa = 0.5f;
                
                if (animacao.isRunning()) {
                    animacao.stop();
                }
                
                animacao.start();
            }
            
        });
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alfa = 1 - fraction;
                }
                
                tamanhoDaAnimacao = fraction * tamanhoDoAlvo;
                repaint();
            }
        };
        
        animacao = new Animator(700, target);
        animacao.setAcceleration(0.5f);
        animacao.setDeceleration(0.5f);
        animacao.setResolution(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int comprimento = getWidth();
        int altura = getHeight();
        
        BufferedImage img = new BufferedImage(comprimento, altura, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D graphics2D = img.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color corTopo = new Color(46, 89, 112);   // azul escuro azulado
        Color corBase = new Color(30, 60, 80);    // azul bem escuro
        java.awt.GradientPaint gradiente = new java.awt.GradientPaint(0, 0, corTopo, 0, altura, corBase);
        graphics2D.setPaint(gradiente);
        graphics2D.fillRoundRect(0, 0, comprimento, altura, altura, altura);
        
        if (ponto != null) {
            graphics2D.setColor(corDeEfeito);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alfa));
            graphics2D.fillOval((int) (ponto.x - tamanhoDaAnimacao / 2), (int) (ponto.y - tamanhoDaAnimacao / 2),
                    (int) tamanhoDaAnimacao, (int) tamanhoDaAnimacao);
        }
        
        graphics2D.dispose();
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
}
