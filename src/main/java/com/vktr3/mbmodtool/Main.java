package com.vktr3.mbmodtool;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.pixinator.mbtool.config.ApplicationConfig;
import com.pixinator.mbtool.io.ContentManager;
import com.pixinator.mbtool.io.ContentManager.DirectoryType;
import com.pixinator.mbtool.mod.Mod;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.utils.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    ApplicationConfig appConfig = ApplicationConfig.getInstance(); // triggers initial load of config

    switch (appConfig.getUIFramework()) {
      case AWT:
        startAWT();
        break;
      case LIBGDX:
        startLibGDX();
        break;
    }
  }

  private static void startAWT() {
    // LOAD IMAGES
    ContentManager content = new ContentManager(DirectoryType.PACKED);
    try {
      Utils.back01 = content.loadImage("Graphics/back_01.png");
      Utils.back02 = content.loadImage("Graphics/back_02.png");
      Utils.back03 = content.loadImage("Graphics/back_03.png");
      Utils.iconNoImage = content.loadImage("Graphics/icon_no_image.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Mod.load();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    new MainFrame().setVisible(true);
  }

  private static void startLibGDX() {
    Lwjgl3ApplicationConfiguration gdxConfig = new Lwjgl3ApplicationConfiguration();
    gdxConfig.setTitle("Minebuilder Modding Tool");
    gdxConfig.useVsync(true);
    gdxConfig.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
    gdxConfig.setWindowedMode(640, 480);
    gdxConfig.setWindowIcon("app-icons/128.png", "app-icons/64.png", "app-icons/32.png", "app-icons/16.png");

    new Lwjgl3Application(new MBModdingTool(), gdxConfig);
  }
}
