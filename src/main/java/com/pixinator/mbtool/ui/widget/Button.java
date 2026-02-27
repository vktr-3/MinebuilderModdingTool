package com.pixinator.mbtool.ui.widget;

import com.pixinator.mbtool.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Button extends JButton implements ActionListener {
  // ################################################################
  // # VARIABLES
  // ################################################################

  private String clickMethodName;
  private Object clickMethodSrc;

  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public Button() {
    super();
    super.addActionListener(this);

    super.setOpaque(true);
    super.setContentAreaFilled(false);
    super.setFocusPainted(false);
    super.setForeground(Color.WHITE);

    this.updateColors();
  }

  // ############################################################
  // # METHODS
  // ############################################################

  private void updateColors() {
    if (super.isEnabled()) {
      if (super.getModel().isPressed()) {
        super.setBackground(Utils.COL_E_MAIN);
        super.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Utils.COL_E_BORDER_L));
      } else if (super.hasFocus() || super.getModel().isRollover()) {
        super.setBackground(Utils.COL_E_MAIN);
        super.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Utils.COL_E_BORDER_D));
      } else {
        super.setBackground(Utils.COL_E_MAIN);
        super.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Utils.COL_E_BORDER_M));
      }

    } else {
      super.setBackground(Utils.COL_D_MAIN);
      super.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Utils.COL_D_BORDER_M));
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.updateColors();

    g.setColor(super.getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());

    super.paintComponent(g);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (super.isEnabled()) {
      if (this.clickMethodName != null && !this.clickMethodName.equals("")) {
        Method method;
        try {
          method = this.clickMethodSrc.getClass().getMethod(this.clickMethodName, Object.class);
          method.invoke(this.clickMethodSrc, this);
        } catch (NoSuchMethodException ex) {
          ex.printStackTrace();
        } catch (IllegalAccessException ex) {
          ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
          ex.printStackTrace();
        } catch (InvocationTargetException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  // ################################################################
  // # GETTERS, SETTERS
  // ################################################################

  public void setClick(String methodName, Object methodSrc) {
    this.clickMethodName = methodName;
    this.clickMethodSrc = methodSrc;
  }

}
