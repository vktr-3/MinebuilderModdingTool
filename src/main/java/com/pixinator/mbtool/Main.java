package com.pixinator.mbtool;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.pixinator.mbtool.io.ContentManager;
import com.pixinator.mbtool.io.ContentManager.DirectoryType;
import com.pixinator.mbtool.mod.Mod;
import com.pixinator.mbtool.ui.MainFrame;
import com.pixinator.mbtool.utils.Utils;

public class Main {

	public static void main(String[] args) {
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

}
