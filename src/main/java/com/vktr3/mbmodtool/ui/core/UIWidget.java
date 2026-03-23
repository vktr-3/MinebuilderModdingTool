package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;
import com.vktr3.mbmodtool.ui.layout.UIVisibility;

public abstract class UIWidget {
  // ################################################################
  // # VARIABLES
  // ################################################################

  protected UIContainer parent;

  protected float x, y, width, height;
  protected float paddingTop, paddingRight, paddingBottom, paddingLeft;
  protected float layoutWidth, layoutHeight;

  protected UIVisibility visibility = UIVisibility.VISIBLE;

  // ################################################################
  // # LAYOUT METHODS
  // ################################################################

  public abstract float measureContentWidth();

  public abstract float measureContentHeight();

  // ################################################################
  // # DRAW METHODS
  // ################################################################

  public final void draw(SpriteBatch batch, UIRenderer renderer) {
    if (this.visibility != UIVisibility.VISIBLE) return;

    drawBackground(batch, renderer);
    drawContent(batch, renderer);
  }

  protected abstract void drawBackground(SpriteBatch batch, UIRenderer renderer);

  protected abstract void drawContent(SpriteBatch batch, UIRenderer renderer);

  // ################################################################
  // # GETTERS, SETTERS
  // ################################################################

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

  public float getContentX() {
    return paddingLeft;
  }

  public float getContentY() {
    return paddingTop;
  }

  public float getContentWidth() {
    return Math.max(0, getWidth() - paddingLeft - paddingRight);
  }

  public float getContentHeight() {
    return Math.max(0, getHeight() - paddingTop - paddingBottom);
  }

  public void setPadding(float padding) {
    this.setPadding(padding, padding, padding, padding);
  }

  public void setPadding(float paddingVertical, float paddingHorizontal) {
    this.setPadding(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal);
  }

  public void setPadding(float paddingTop, float paddingRight, float paddingBottom, float paddingLeft) {
    this.paddingTop = paddingTop;
    this.paddingRight = paddingRight;
    this.paddingBottom = paddingBottom;
    this.paddingLeft = paddingLeft;
  }

  public float getPaddingVertical() {
    return paddingTop + paddingBottom;
  }

  public float getPaddingHorizontal() {
    return paddingLeft + paddingRight;
  }

  public float getPaddingTop() {
    return paddingTop;
  }

  public float getPaddingRight() {
    return paddingRight;
  }

  public float getPaddingBottom() {
    return paddingBottom;
  }

  public float getPaddingLeft() {
    return paddingLeft;
  }

  public void setLayoutSize(float layoutWidth, float layoutHeight) {
    setLayoutWidth(layoutWidth);
    setLayoutHeight(layoutHeight);
  }

  public float getLayoutWidth() {
    return layoutWidth;
  }

  public void setLayoutWidth(float layoutWidth) {
    this.layoutWidth = layoutWidth;
  }

  public float getLayoutHeight() {
    return layoutHeight;
  }

  public void setLayoutHeight(float layoutHeight) {
    this.layoutHeight = layoutHeight;
  }

  public boolean affectsLayout() {
    return this.visibility != UIVisibility.GONE;
  }

  public UIVisibility getVisibility() {
    return visibility;
  }

  public void setVisibility(UIVisibility visibility) {
    this.visibility = visibility;
  }
}
