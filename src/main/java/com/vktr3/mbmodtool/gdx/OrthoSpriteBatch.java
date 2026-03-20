package com.vktr3.mbmodtool.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Overrides some SpriteBatch draw methods.
 * <br>
 * Simple toggles flipY on texture rendering. This way coordinates from top/left to bottom/right.
 * More methods will be added when needed.
 */
public class OrthoSpriteBatch extends SpriteBatch {
  @Override
  public void draw(Texture texture, float x, float y) {
    draw(texture, x, y, texture.getWidth(), texture.getHeight(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
  }

  @Override
  public void draw(Texture texture, float x, float y, float width, float height) {
    draw(texture, x, y, width, height, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
  }

  @Override
  public void draw(Texture texture, float x, float y, int srcX, int srcY, int srcWidth, int srcHeight) {
    draw(texture, x, y, srcWidth, srcHeight, srcX, srcY, srcWidth, srcHeight, false, false);
  }

  @Override
  public void draw(Texture texture, float x, float y, float width, float height, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY) {
    super.draw(texture, x, y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, !flipY);
  }
}
