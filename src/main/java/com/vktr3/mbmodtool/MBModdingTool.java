package com.vktr3.mbmodtool;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vktr3.mbmodtool.gdx.OrthoSpriteBatch;
import com.vktr3.mbmodtool.ui.core.UIDocument;
import com.vktr3.mbmodtool.ui.widgets.Panel;

public class MBModdingTool extends ApplicationAdapter {
  private AssetManager assets;
  private OrthoSpriteBatch batch;

  private OrthographicCamera camera;
  private Viewport viewport;

  private UIDocument ui;
  private Panel pnlRoot;
  private Panel pnlInner;

  @Override
  public void create() {
    assets = new AssetManager();
    batch = new OrthoSpriteBatch();

    camera = new OrthographicCamera();
    camera.setToOrtho(true);
    viewport = new ScreenViewport(camera);

    loadContent();
    initUI();
  }

  private void loadContent() {
    assets.finishLoading();
  }

  private void initUI() {
    pnlRoot = new Panel();
    pnlRoot.setBackgroundColor(Color.BLACK);

    pnlInner = new Panel();
    pnlInner.setBackgroundColor(new Color(1, 0.75F, 1, 0.5F));
    pnlRoot.addChild(pnlInner);

    ui = new UIDocument(pnlRoot);
  }

  @Override
  public void render() {
    ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

    batch.setProjectionMatrix(camera.combined);
    batch.begin();

    ui.draw(batch);

    batch.end();
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);

    resizeUI(width, height);
  }

  private void resizeUI(int width, int height) {
    ui.setSize(width, height);
    pnlInner.setBounds(8, 8, pnlRoot.getWidth() - 16, pnlRoot.getHeight() - 16);
  }

  @Override
  public void dispose() {
    assets.dispose();
    batch.dispose();
    ui.dispose();
  }
}
