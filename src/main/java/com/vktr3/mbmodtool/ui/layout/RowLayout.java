package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

public class RowLayout implements UILayout {
  private final float spacing;
  private final UIAlignment verticalAlignment;

  public RowLayout() {
    this(0);
  }

  public RowLayout(float spacing) {
    this(spacing, UIAlignment.START);
  }

  public RowLayout(float spacing, UIAlignment verticalAlignment) {
    this.spacing = spacing;
    this.verticalAlignment = verticalAlignment;
  }

  @Override
  public void updateLayout(UIContainer container) {
    float contentY = container.getContentY();
    float contentHeight = container.getContentHeight();

    float curX = container.getContentX();
    for (UIWidget child : container.getChildren()) {
      float childWidth = getChildWidth(child);
      float childHeight = getChildHeight(child, contentHeight);

      float childY = getAlignedY(contentY, contentHeight, childHeight);

      child.setBounds(curX, childY, childWidth, childHeight);

      curX += childWidth + spacing;
    }
  }

  private float getChildWidth(UIWidget child) {
    return Math.max(0, child.getLayoutWidth());
  }

  private float getChildHeight(UIWidget child, float containerContentHeight) {
    if (child.getLayoutHeight() == FILL_PARENT) {
      return containerContentHeight;
    } else {
      return Math.max(0, child.getLayoutHeight());
    }
  }

  private float getAlignedY(float contentY, float contentHeight, float childHeight) {
    return switch (verticalAlignment) {
      case START -> contentY;
      case CENTER -> contentY + (contentHeight - childHeight) * 0.5F;
      case END -> contentY + (contentHeight - childHeight);
    };
  }
}
