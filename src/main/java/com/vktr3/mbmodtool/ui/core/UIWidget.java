package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;

public abstract class UIWidget {
  protected UIContainer parent;

  protected float x, y, width, height;
  protected float layoutWidth, layoutHeight;

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

  public float getAbsoluteX() {
    if (parent == null) return x;
    return parent.getAbsoluteX() + x;
  }

  public float getAbsoluteY() {
    if (parent == null) return y;
    return parent.getAbsoluteY() + y;
  }

  public void setBounds(float x, float y, float width, float height) {
    setPosition(x, y);
    setSize(width, height);
  }

  public void setPosition(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void setSize(float width, float height) {
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

  public void setLayoutSize(float layoutWidth, float layoutHeight) {
    setLayoutWidth(layoutWidth);
    setLayoutHeight(layoutHeight);
  }

  public float getLayoutWidth() {
    return layoutWidth;
  }

  public void setLayoutWidth(float preferredWidth) {
    this.layoutWidth = preferredWidth;
  }

  public float getLayoutHeight() {
    return layoutHeight;
  }

  public void setLayoutHeight(float layoutHeight) {
    this.layoutHeight = layoutHeight;
  }

  public boolean isVisible() {
    return visible;
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
  }
}
