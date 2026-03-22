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
    float contentX = container.getContentX();
    float contentWidth = container.getContentWidth();

    float curY = container.getContentY();
    for (UIWidget child : container.getChildren()) {
      float childWidth = getChildWidth(child, contentWidth);
      float childHeight = getChildHeight(child);
      child.setBounds(contentX, curY, childWidth, childHeight);

      curY += childHeight + spacing;
    }
  }

  private float getChildWidth(UIWidget child, float containerContentWidth) {
    if (child.getLayoutWidth() == MATCH_PARENT) {
      return containerContentWidth;
    } else {
      return Math.max(0, child.getLayoutWidth());
    }
  }

  private float getChildHeight(UIWidget child) {
    return Math.max(0, child.getLayoutHeight());
  }
}
