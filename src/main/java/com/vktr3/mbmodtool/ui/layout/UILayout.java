package com.vktr3.mbmodtool.ui.layout;

import com.vktr3.mbmodtool.ui.core.UIContainer;

public interface UILayout {
  float FILL_PARENT = -1;
  float WRAP_CONTENT = -2;

  float measureContentWidth(UIContainer container);

  float measureContentHeight(UIContainer container);

  void updateLayout(UIContainer container);
}
