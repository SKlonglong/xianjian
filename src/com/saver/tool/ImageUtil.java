package com.saver.tool;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static Image getImage(String name, String pngName) {
		try {
			return ImageIO.read(new File("素材"+File.separator + name + File.separator + pngName
					+ ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static Image[] getImages(String name, int number) {
		Image[] images = new Image[number];
		for (int i = 0; i < number; i++) {
			images[i] = getImage(name, String.valueOf(i));
		}
		return images;
	}
}
