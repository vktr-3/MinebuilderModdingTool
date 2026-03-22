package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

public class ColumnLayout implements UILayout {
  private final float spacing;

  public ColumnLayout(float spacing) {
    this.spacing = spacing;
  }

  @Override
  public void updateLayout(UIContainer container) {
    float curY = 0;

    for (UIWidget child : container.getChildren()) {
      float childHeight = getChildHeight(child);
      child.setBounds(0, curY, container.getWidth(), childHeight);

      curY += childHeight + spacing;
    }
  }

  private float getChildHeight(UIWidget child) {
    return Math.max(0, child.getLayoutHeight());
  }
}
