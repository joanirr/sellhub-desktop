package com.jotadev.gestao.vendas.modelo.util;

import com.jotadev.gestao.vendas.visual.componentes.Mensagem;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MensagemUtil {
    
    private final JLayeredPane background;
    private final MigLayout layout;

    public MensagemUtil(JLayeredPane background, MigLayout layout) {
        this.background = background;
        this.layout = layout;
    }
    
    public void mostrarMensagem(Mensagem.TipoMensagem tipo, String mensagem) {
        Mensagem ms = new Mensagem();
        ms.mostrarMensagem(tipo, mensagem);
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isMostrar()) {
                    background.add(ms, "pos 0.5al -30", JLayeredPane.DRAG_LAYER);
                    background.setLayer(ms, JLayeredPane.DRAG_LAYER);
                    ms.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                
                if (!ms.isMostrar()) {
                    f = -30 + (70 * fraction);
                } else {
                    f = 40 - (70 * fraction);
                }
                
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) f);
                background.revalidate();
            }

            @Override
            public void end() {
                if (ms.isMostrar()) {
                    background.remove(ms);
                    background.repaint();
                    background.revalidate();
                } else {
                    ms.setMostrar(true);
                }
            }
            
        };
        
        Animator animacaoEntrada = new Animator(300, target);
        Animator animacaoSaida = new Animator(300, target);
        animacaoEntrada.setAcceleration(0.5f);
        animacaoEntrada.setDeceleration(0.5f);
        animacaoSaida.setAcceleration(0.5f);
        animacaoSaida.setDeceleration(0.5f);
        
        new Thread(() -> {
            try {
                animacaoEntrada.start();
                Thread.sleep(2000);
                animacaoSaida.start();
            } catch (InterruptedException e) {
                System.out.println(e);
            } catch (IllegalStateException e) {
                System.out.println("Animador já estava rodando: " + e.getMessage());
            }
        }).start();
        
    }

    public Object getMensagemPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    