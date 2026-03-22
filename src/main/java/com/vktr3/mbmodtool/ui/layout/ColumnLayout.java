package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

public class ColumnLayout implements UILayout {
  private final float spacing;
  private final UIAlignment horizontalAlignment;

  public ColumnLayout() {
    this(0);
  }

  public ColumnLayout(float spacing) {
    this(spacing, UIAlignment.START);
  }

  public ColumnLayout(float spacing, UIAlignment horizontalAlignment) {
    this.spacing = spacing;
    this.horizontalAlignment = horizontalAlignment;
  }

  @Override
  public void updateLayout(UIContainer container) {
    float contentX = container.getContentX();
    float contentWidth = container.getContentWidth();

    float curY = container.getContentY();
    for (UIWidget child : container.getChildren()) {
      float childWidth = getChildWidth(child, contentWidth);
      float childHeight = getChildHeight(child);

      float childX = getAlignedX(contentX, contentWidth, childWidth);

      child.setBounds(childX, curY, childWidth, childHeight);

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

  private float getAlignedX(float contentX, float contentWidth, float childWidth) {
    return switch (horizontalAlignment) {
      case START -> contentX;
      case CENTER -> contentX + (contentWidth - childWidth) * 0.5F;
      case END -> contentX + (contentWidth - childWidth);
    };
  }
}
