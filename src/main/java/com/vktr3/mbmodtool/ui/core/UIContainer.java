package com.vktr3.mbmodtool.ui.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vktr3.mbmodtool.ui.graphics.UIRenderer;
import com.vktr3.mbmodtool.ui.layout.UILayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class UIContainer extends UIWidget {
  protected final List<UIWidget> children = new ArrayList<>();
  protected UILayout layout;

  @Override
  public float measureContentWidth() {
    return layout != null ? layout.measureContentWidth(this) : 0;
  }

  @Override
  public float measureContentHeight() {
    return layout != null ? layout.measureContentHeight(this) : 0;
  }

  public void updateLayout() {
    if (layout != null) layout.updateLayout(this);

    for (UIWidget child : children) {
      if (child instanceof UIContainer container) container.updateLayout();
    }
  }

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

  public List<UIWidget> getLayoutAffectedChildren() {
    List<UIWidget> affectedChildren = new ArrayList<>();
    for (UIWidget child : children) {
      if (child.affectsLayout()) {
        affectedChildren.add(child);
      }
    }
    return affectedChildren;
  }

  public UILayout getLayout() {
    return layout;
  }

  public void setLayout(UILayout layout) {
    this.layout = layout;
  }
}
