package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

public class RowLayout implements UILayout {
  private final float spacing;

  public RowLayout(float spacing) {
    this.spacing = spacing;
  }

  @Override
  public void updateLayout(UIContainer container) {
    float contentY = container.getContentY();
    float contentHeight = container.getContentHeight();

    float curX = container.getContentX();
    for (UIWidget child : container.getChildren()) {
      float childWidth = getChildWidth(child);
      child.setBounds(curX, contentY, childWidth, contentHeight);

      curX += childWidth + spacing;
    }
  }

  private float getChildWidth(UIWidget child) {
    return Math.max(0, child.getLayoutWidth());
  }
}
