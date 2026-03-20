package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;

public abstract class UIWidget {
  protected UIContainer parent;

  protected float x, y, width, height;

  protected boolean visible = true;

  public final void draw(SpriteBatch batch, UIRenderer renderer) {
    if (!visible) return;

    drawBackground(batch, renderer);
    drawContent(batch, renderer);
  }

  protected abstract void drawBackground(SpriteBatch batch, UIRenderer renderer);

  protected abstract void drawContent(SpriteBatch batch, UIRenderer renderer);

  public UIContainer getParent() {
    return parent;
  }

  protected void setParent(UIContainer parent) {
    this.parent = parent;
  }

  public void setBounds(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }
}
