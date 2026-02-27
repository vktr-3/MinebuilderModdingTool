package com.pixinator.mbtool.ui.widget;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public Frame(String title, Dimension contentSize) {
    super();

    super.setTitle(title);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    super.setLayout(null);
    super.getContentPane().setPreferredSize(contentSize);
    super.pack();
    super.setLocationRelativeTo(null);
  }

  // ############################################################
  // # GETTERS, SETTERS
  // ############################################################

  public int getContentWidth() {
    return super.getContentPane().getWidth();
  }

  public int getContentHeight() {
    return super.getContentPane().getHeight();
  }

}
