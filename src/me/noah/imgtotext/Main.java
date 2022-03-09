package me.noah.imgtotext;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Please specify a filename!");
			return;
		}
		
		int width = 0;
		int height = 0;
		if (args.length >= 3) {
			try {
				width = Integer.parseInt(args[1]);
				height = Integer.parseInt(args[2]);
			} catch (Exception e) {
				System.out.println("Not a valid with or height!");
				return;
			}
		}
		
		BufferedImage image;
		try {
			image = ImageIO.read(new File(args[0]));
		} catch (Exception e) {
			System.out.println("Something went wrong while reading image!");
			return;
		}
		
		String pixels = " .,_;|\\=+0bnmBNM#";
		if (height == 0) {
			height = image.getHeight();
		}
		if (width == 0) {
			width = image.getWidth();
		}
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				int rgb = image.getRGB(x * image.getWidth() / width, y * image.getHeight() / height);
				int pValue = (rgb & 0xFF) + (rgb >> 8 & 0xFF) + (rgb >> 16 & 0xFF);
				pValue /= 3;
				pValue = (pValue * pixels.length() / 255) - 1;
				if (pValue > pixels.length() - 1) {
					pValue = pixels.length() - 1;
				}
				if (pValue < 0) {
					pValue = 0;
				}
				System.out.print(pixels.charAt(pValue));
			}
			System.out.print("\n");
		}
	}

}
