package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;
import com.vktr3.mbmodtool.ui.core.UIWidget;

import java.util.List;

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
    List<UIWidget> children = container.getLayoutAffectedChildren();
    if (children.isEmpty()) return;

    float contentX = container.getContentX();
    float contentY = container.getContentY();
    float contentWidth = container.getContentWidth();
    float contentHeight = container.getContentHeight();

    int parentFillCount = 0;
    float fixedWidthSum = 0;
    for (UIWidget child : children) {
      if (child.getLayoutWidth() == FILL_PARENT) {
        parentFillCount++;
      } else {
        fixedWidthSum += child.getLayoutWidth();
      }
    }

    float totalSpacing = spacing * (children.size() - 1);
    float remainingWidth = contentWidth - fixedWidthSum - totalSpacing;
    float parentFillWidth = parentFillCount > 0 ? Math.max(0, remainingWidth) / parentFillCount : 0;

    float curX = contentX;
    for (UIWidget child : children) {
      float childWidth = resolveChildWidth(child, parentFillWidth);
      float childHeight = resolveChildHeight(child, contentHeight);

      float childY = getAlignedY(contentY, contentHeight, childHeight);

      child.setBounds(curX, childY, childWidth, childHeight);

      curX += childWidth + spacing;
    }
  }

  private float resolveChildWidth(UIWidget child, float fillParentWidth) {
    if (child.getLayoutWidth() == FILL_PARENT) {
      return fillParentWidth;
    } else {
      return Math.max(0, child.getLayoutWidth());
    }
  }

  private float resolveChildHeight(UIWidget child, float parentContentHeight) {
    if (child.getLayoutHeight() == FILL_PARENT) {
      return parentContentHeight;
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
