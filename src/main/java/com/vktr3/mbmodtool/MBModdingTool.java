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
import com.vktr3.mbmodtool.ui.layout.ColumnLayout;
import com.vktr3.mbmodtool.ui.layout.RowLayout;
import com.vktr3.mbmodtool.ui.layout.UIAlignment;
import com.vktr3.mbmodtool.ui.widgets.Panel;

import static com.vktr3.mbmodtool.ui.layout.UILayout.MATCH_PARENT;

public class MBModdingTool extends ApplicationAdapter {
  private AssetManager assets;
  private OrthoSpriteBatch batch;

  private OrthographicCamera camera;
  private Viewport viewport;

  private UIDocument ui;

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
    Panel pnlRoot = new Panel();
    pnlRoot.setBackgroundColor(Color.BLACK);
    pnlRoot.setPadding(8);
    pnlRoot.setLayout(new ColumnLayout(8, UIAlignment.CENTER));

    Panel pnlHeader = new Panel();
    pnlHeader.setBackgroundColor(new Color(0.25F, 0.75F, 0.25F, 1));
    pnlHeader.setLayoutSize(MATCH_PARENT, 96);
    pnlRoot.addChild(pnlHeader);

    Panel pnlContent = new Panel();
    pnlContent.setBackgroundColor(new Color(0.75F, 0.25F, 0.25F, 1));
    pnlContent.setLayoutSize(MATCH_PARENT, 256);
    pnlContent.setPadding(8, 16);
    pnlContent.setLayout(new RowLayout(8, UIAlignment.CENTER));
    pnlRoot.addChild(pnlContent);

    Panel pnlLeft = new Panel();
    pnlLeft.setBackgroundColor(new Color(0, 0.75F, 1, 1));
    pnlLeft.setLayoutSize(256, 64);
    pnlContent.addChild(pnlLeft);

    Panel pnlRight = new Panel();
    pnlRight.setBackgroundColor(new Color(1, 0.75F, 0, 1));
    pnlRight.setLayoutSize(512, MATCH_PARENT);
    pnlContent.addChild(pnlRight);

    Panel pnlFooter = new Panel();
    pnlFooter.setBackgroundColor(new Color(1, 1, 0.75F, 1));
    pnlFooter.setLayoutSize(512, 64);
    pnlRoot.addChild(pnlFooter);

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
    ui.getRoot().updateLayout();
  }

  @Override
  public void dispose() {
    assets.dispose();
    batch.dispose();
    ui.dispose();
  }
}
