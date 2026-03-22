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

import static com.vktr3.mbmodtool.ui.layout.UILayout.FILL_PARENT;
import static com.vktr3.mbmodtool.ui.layout.UILayout.WRAP_CONTENT;

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
    pnlHeader.setLayoutSize(FILL_PARENT, 96);
    pnlRoot.addChild(pnlHeader);

    Panel pnlContent = new Panel();
    pnlContent.setBackgroundColor(new Color(0.75F, 0.25F, 0.25F, 1));
    pnlContent.setLayoutSize(FILL_PARENT, FILL_PARENT);
    pnlContent.setPadding(8);
    pnlContent.setLayout(new RowLayout(8, UIAlignment.CENTER));
    pnlRoot.addChild(pnlContent);

    Panel pnlTools = new Panel();
    pnlTools.setBackgroundColor(new Color(0.75F, 1, 0, 1));
    pnlTools.setLayoutSize(64, FILL_PARENT);
    pnlContent.addChild(pnlTools);

    Panel pnlContext = new Panel();
    pnlContext.setBackgroundColor(new Color(0, 0.75F, 1, 1));
    pnlContext.setLayoutSize(256, FILL_PARENT);
    pnlContent.addChild(pnlContext);

    Panel pnlEdit = new Panel();
    pnlEdit.setBackgroundColor(new Color(1, 0.75F, 0, 1));
    pnlEdit.setLayoutSize(FILL_PARENT, FILL_PARENT);
    pnlContent.addChild(pnlEdit);

    Panel pnlFooter = new Panel();
    pnlFooter.setBackgroundColor(new Color(0.25F, 0.35F, 0.25F, 1));
    pnlFooter.setLayoutSize(WRAP_CONTENT, 64);
    pnlFooter.setPadding(8);
    pnlFooter.setLayout(new RowLayout(0, UIAlignment.CENTER));
    pnlRoot.addChild(pnlFooter);

    Panel pnlCopyright = new Panel();
    pnlCopyright.setBackgroundColor(Color.WHITE);
    pnlCopyright.setLayoutSize(256, 32);
    pnlFooter.addChild(pnlCopyright);

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
