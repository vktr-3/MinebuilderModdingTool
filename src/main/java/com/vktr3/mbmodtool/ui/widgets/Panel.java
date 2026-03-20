package com.vktr3.mbmodtool.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;

public class Panel extends UIContainer {
  private Color backgroundColor = new Color(Color.WHITE);

  @Override
  protected void drawBackground(SpriteBatch batch, UIRenderer renderer) {
    renderer.drawRect(batch, getAbsoluteX(), getAbsoluteY(), width, height, backgroundColor);
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public void setBackgroundColor(Color color) {
    this.backgroundColor = color;
  }
}
