package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class UIContainer extends UIWidget {
  protected final List<UIWidget> children = new ArrayList<>();

  @Override
  protected void drawContent(SpriteBatch batch, UIRenderer renderer) {
    for (UIWidget child : children) {
      child.draw(batch, renderer);
    }
  }

  public void addChild(UIWidget child) {
    if (child == null || child == this || child.getParent() != null) return;

    child.setParent(this);
    children.add(child);
  }

  public void removeChild(UIWidget child) {
    if (child == null) return;

    if (children.remove(child)) {
      child.setParent(null);
    }
  }

  public List<UIWidget> getChildren() {
    return Collections.unmodifiableList(children);
  }
}
