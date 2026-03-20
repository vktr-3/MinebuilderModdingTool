package com.vktr3.mbmodtool.ui.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class UIRenderer implements Disposable {
  private final Texture whiteTexture;

  public UIRenderer() {
    whiteTexture = generateWhiteTexture();
  }

  private Texture generateWhiteTexture() {
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.WHITE);
    pixmap.fill();

    Texture whiteTexture = new Texture(pixmap);
    pixmap.dispose();
    return whiteTexture;
  }

  public void drawRect(SpriteBatch batch, float x, float y, float width, float height, Color color) {
    batch.setColor(color);
    batch.draw(whiteTexture, x, y, width, height);
  }

  @Override
  public void dispose() {
    whiteTexture.dispose();
  }
}
