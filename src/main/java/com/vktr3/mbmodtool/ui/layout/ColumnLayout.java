package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

import java.util.List;

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
    List<UIWidget> children = container.getChildren();
    if (children.isEmpty()) return;

    float contentX = container.getContentX();
    float contentY = container.getContentY();
    float contentWidth = container.getContentWidth();
    float contentHeight = container.getContentHeight();

    int parentFillCount = 0;
    float fixedHeightSum = 0;
    for (UIWidget child : children) {
      if (child.getLayoutHeight() == FILL_PARENT) {
        parentFillCount++;
      } else {
        fixedHeightSum += child.getLayoutHeight();
      }
    }

    float totalSpacing = spacing * (children.size() - 1);
    float remainingHeight = contentHeight - fixedHeightSum - totalSpacing;
    float parentFillHeight = parentFillCount > 0 ? Math.max(0, remainingHeight) / parentFillCount : 0;

    float curY = contentY;
    for (UIWidget child : children) {
      float childWidth = resolveChildWidth(child, contentWidth);
      float childHeight = resolveChildHeight(child, parentFillHeight);

      float childX = getAlignedX(contentX, contentWidth, childWidth);

      child.setBounds(childX, curY, childWidth, childHeight);

      curY += childHeight + spacing;
    }
  }

  private float resolveChildWidth(UIWidget child, float parentContentWidth) {
    if (child.getLayoutWidth() == FILL_PARENT) {
      return parentContentWidth;
    } else {
      return Math.max(0, child.getLayoutWidth());
    }
  }

  private float resolveChildHeight(UIWidget child, float parentFillHeight) {
    if (child.getLayoutHeight() == FILL_PARENT) {
      return parentFillHeight;
    } else {
      return Math.max(0, child.getLayoutHeight());
    }
  }

  private float getAlignedX(float contentX, float contentWidth, float childWidth) {
    return switch (horizontalAlignment) {
      case START -> contentX;
      case CENTER -> contentX + (contentWidth - childWidth) * 0.5F;
      case END -> contentX + (contentWidth - childWidth);
    };
  }
}
