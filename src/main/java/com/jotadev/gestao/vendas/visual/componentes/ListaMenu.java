package com.jotadev.gestao.vendas.visual.componentes;

import com.jotadev.gestao.vendas.visual.modelo.MenuModelo;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListaMenu<E extends Object> extends JList<E> {
    
    private DefaultListModel model;
    private int selecionadoIndex = -1;
    private int hoverIndex = -1;
    
    public ListaMenu() {
        setOpaque(false);
        this.model = new DefaultListModel();
        setModel(model);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    
                    Object o = model.getElementAt(index);
                    
                    if (o instanceof MenuModelo) {
                        MenuModelo menuModelo = (MenuModelo) o;
                        
                        if (menuModelo.getTipoMenu() == MenuModelo.TipoMenu.MENU) {
                            selecionadoIndex = index;
                            
                        }
                    }
                    repaint();
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                hoverIndex = -1;
                repaint();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                
                Object o = model.getElementAt(index);
                    
                    if (o instanceof MenuModelo) {
                        MenuModelo menuModelo = (MenuModelo) o;
                        
                        if (menuModelo.getTipoMenu() == MenuModelo.TipoMenu.MENU) {
                            hoverIndex = index;
                            
                        }
                    }
                    repaint();
            }
            
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                
                MenuModelo menuModelo;
                
                if (value instanceof MenuModelo) {
                    menuModelo = (MenuModelo) value;
                } else {
                    menuModelo = new MenuModelo("", value + "", MenuModelo.TipoMenu.VAZIO);
                }
                
                MenuItem menuItem = new MenuItem(menuModelo);
                menuItem.setSelecionado(selecionadoIndex == index);
                menuItem.setHover(hoverIndex == index);
                return menuItem;
            }
        };
    }
    
    public void adicionarItem(MenuModelo menuModelo) {
        model.addElement(menuModelo);
        
        if (menuModelo.getNome().equals("Dashboard")) {
            selecionadoIndex = 0;
        }
    }
}
