package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;

public class UIDocument implements Disposable {
  // ################################################################
  // # VARIABLES
  // ################################################################

  private final UIContainer root;

  private final UIRenderer renderer;

  private float width, height;

  // ################################################################
  // # CONSTRUCTOR
  // ################################################################

  public UIDocument(UIContainer root) {
    this.root = root;
    this.root.setDocument(this);

    this.renderer = new UIRenderer();
  }

  // ################################################################
  // # METHODS
  // ################################################################

  public void draw(SpriteBatch batch) {
    root.draw(batch, renderer);
  }

  @Override
  public void dispose() {
    this.renderer.dispose();
  }

  // ################################################################
  // # GETTERS, SETTERS
  // ################################################################

  public UIContainer getRoot() {
    return root;
  }

  public void setSize(float width, float height) {
    this.width = width;
    this.height = height;

    root.setBounds(0, 0, width, height);
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }
}
