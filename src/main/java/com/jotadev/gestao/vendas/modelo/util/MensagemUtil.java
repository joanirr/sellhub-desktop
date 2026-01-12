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
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                final Mensagem ms = new Mensagem();
                ms.mostrarMensagem(tipo, mensagem);

                for (java.awt.Component c : background.getComponents()) {
                    if (c instanceof Mensagem) {
                        background.remove(c);
                    }
                }

                background.add(ms);
                background.setLayer(ms, JLayeredPane.POPUP_LAYER);
                ms.setVisible(true);

                TimingTarget target = new TimingTargetAdapter() {
                    @Override
                    public void timingEvent(float fraction) {
                        float f = !ms.isMostrar() ? -30 + (70 * fraction) : 40 - (70 * fraction);

                        int x = (background.getWidth() - ms.getPreferredSize().width) / 2;
                        ms.setBounds(x, (int) f, ms.getPreferredSize().width, ms.getPreferredSize().height);

                        background.repaint();
                    }

                    @Override
                    public void end() {
                        if (ms.isMostrar()) {
                            background.remove(ms);
                            background.repaint();
                        } else {
                            ms.setMostrar(true);
                            new Thread(() -> {
                                try {
                                    Thread.sleep(2000);
                                    ms.setMostrar(true);
                                    animarSaida();
                                } catch (InterruptedException e) {}
                            }).start();
                        }
                    }

                    private void animarSaida() {
                        Animator out = new Animator(300, this);
                        out.start();
                    }
                };

                Animator animator = new Animator(300, target);
                animator.setAcceleration(0.5f);
                animator.setDeceleration(0.5f);
                animator.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Object getMensagemPanel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    