package com.pixinator.mbtool.mod;

import com.pixinator.mbtool.io.ContentManager;
import com.pixinator.mbtool.io.ContentManager.DirectoryType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ItemTexture {
  // ############################################################
  // # VARIABLES
  // ############################################################

  private int id;
  private long lastModified;
  private BufferedImage texture;

  // ############################################################
  // # CONSTRUCTORS
  // ############################################################

  public static ItemTexture createItemTexture(int id) throws IOException {
    ContentManager content = new ContentManager(DirectoryType.FOLDER);
    File file = new File("files/Items/item_" + id + ".png");

    ItemTexture itemTexture = new ItemTexture();
    itemTexture.id = id;
    itemTexture.lastModified = file.lastModified();
    itemTexture.texture = content.loadImage("files/Items/item_" + id + ".png");
    return itemTexture;
  }

  // ############################################################
  // # METHODS
  // ############################################################

  public void dispose() {
    this.texture.getGraphics().dispose();
    this.texture.flush();
  }

  // ############################################################
  // # GETTERS, SETTERS
  // ############################################################

  public int getID() {
    return this.id;
  }

  public long getLastModified() {
    return this.lastModified;
  }

  public BufferedImage getTexture() {
    return this.texture;
  }

}
