package com.pixinator.mbtool.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Utils {
	// ############################################################
	// # COLORS
	// ############################################################

	public static final Color COL_E_BORDER_D = new Color(36, 33, 26);
	public static final Color COL_E_BORDER_M = new Color(58, 55, 39);
	public static final Color COL_E_BORDER_L = new Color(77, 74, 59);
	public static final Color COL_E_MAIN = new Color(64, 34, 19);

	public static final Color COL_D_BORDER_D = new Color(38, 37, 37);
	public static final Color COL_D_BORDER_M = new Color(78, 77, 77);
	public static final Color COL_D_BORDER_L = new Color(164, 163, 163);
	public static final Color COL_D_MAIN = new Color(48, 48, 47);

	// ############################################################
	// # IMAGES
	// ############################################################

	public static BufferedImage back01;
	public static BufferedImage back02;
	public static BufferedImage back03;
	public static BufferedImage iconNoImage;

	public static BufferedImage getBack() {
		int rnd = new Random().nextInt(3);

		switch (rnd) {
		case 0:
			return back01;
		case 1:
			return back02;
		case 2:
			return back03;
		default:
			return back01;
		}
	}

}
