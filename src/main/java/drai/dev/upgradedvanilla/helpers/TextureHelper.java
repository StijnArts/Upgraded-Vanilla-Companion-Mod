package drai.dev.upgradedvanilla.helpers;

import com.ibm.icu.impl.*;

import drai.dev.upgradedvanilla.helpers.composites.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TextureHelper {

	public static File woodPresetPalette = RelativeFileHelper.getTemplateData("/wood/Palletes/base_palette.png");
	public static File stonePresetPalette = RelativeFileHelper.getTemplateData("/stone/palletes/stone_palette.png");
	public static File metalPresetPalette = RelativeFileHelper.getTemplateData("/metal/Pallete/cast_iron_pallete.png");
	public static ArrayList<Runnable> requiredTextures = new ArrayList<>();

	public static void addTexture(Runnable runnable){
		requiredTextures.add(runnable);
	}

	public static ArrayList<Runnable> getRequiredTextures() {
		return requiredTextures;
	}

	public static BufferedImage swapColors(String outputLocation, String outputDirLocation, String id, BufferedImage img, File paletteIn, File paletteOut) throws IOException {
		//int[] pixels = img.getRGB(0,0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
		HashMap<Pair<Integer, Integer>, Color> toReplace = new HashMap<>();
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				int pixel = img.getRGB(j, i);
				if(pixel!=0){
					int alpha = (pixel >> 24) & 0xff;
					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;
					toReplace.put(Pair.of(j, i), new Color(red, green, blue, alpha));
				}
			}
		}

		BufferedImage paletteInImg = ImageIO.read(paletteIn);
		BufferedImage paletteOutImg = ImageIO.read(paletteOut);
		HashMap<Color, Color> map = new HashMap<>();
		for (int i = 0; i < paletteInImg.getHeight(); i++) {
			for (int j = 0; j < paletteInImg.getWidth(); j++) {
				int pixelIn = paletteInImg.getRGB(j, i);
				int alphaIn = (pixelIn >> 24) & 0xff;
				int redIn = (pixelIn >> 16) & 0xff;
				int greenIn = pixelIn >> 8 & 0xff;
				int blueIn = (pixelIn) & 0xff;
				int pixelOut = paletteOutImg.getRGB(j, i);
				int alphaOut = (pixelOut >> 24) & 0xff;
				int redOut = (pixelOut >> 16) & 0xff;
				int greenOut = (pixelOut >> 8) & 0xff;
				int blueOut = (pixelOut) & 0xff;
				map.put(new Color(redIn, greenIn, blueIn, alphaIn), new Color(redOut, greenOut, blueOut, alphaOut));
			}
		}

		Graphics2D g = img.createGraphics();
		for (Pair<Integer, Integer> key : toReplace.keySet()) {
			for (Color color : map.keySet()) {
				if (toReplace.get(key).getRed() == color.getRed() && toReplace.get(key).getGreen() == color.getGreen() &&
						toReplace.get(key).getBlue() == color.getBlue()) {
					Color replacement = new Color(map.get(color).getRed(), map.get(color).getGreen(), map.get(color).getBlue(), toReplace.get(key).getAlpha());
					//System.out.println(replacement);
					g.setColor(replacement);
					g.fillRect(key.first, key.second, 1, 1);
				}
			}
		}
		saveImage(img, outputLocation, outputDirLocation, id);
		return img;
	}

	public static BufferedImage flipTexture(BufferedImage image, String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage rotated = rotateImage(image, 180);
		saveImage(rotated, outputLocation, outputDir, id);
		return rotated;
	}

	private static BufferedImage rotateImage(BufferedImage buffImage, double angle) {
		double radian = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(radian));
		double cos = Math.abs(Math.cos(radian));

		int width = buffImage.getWidth();
		int height = buffImage.getHeight();

		int nWidth = (int) Math.floor((double) width * cos + (double) height * sin);
		int nHeight = (int) Math.floor((double) height * cos + (double) width * sin);

		BufferedImage rotatedImage = new BufferedImage(
				nWidth, nHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = rotatedImage.createGraphics();

		graphics.setRenderingHint(
				RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		graphics.translate((nWidth - width) / 2, (nHeight - height) / 2);
		// rotation around the center point
		graphics.rotate(radian, (double) (width / 2), (double) (height / 2));
		graphics.drawImage(buffImage, width, 0,-width, height, null);
		graphics.dispose();

		return rotatedImage;
	}

	public static BufferedImage overlayTexture(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
											   String outputLocation, String outputDir, String id) throws IOException {
		Graphics g = bottom.getGraphics();
		g.drawImage(overlay, xLocation, yLocation, null);
		saveImage(bottom, outputLocation, outputDir, id);
		return bottom;
	}
	public static BufferedImage overlayTexture(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
											   String outputLocation, String outputDir, String id, float opacityPercent) throws IOException {
		Graphics2D g = bottom.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacityPercent));
		g.drawImage(overlay, xLocation, yLocation, null);
		saveImage(bottom, outputLocation, outputDir, id);
		return bottom;
	}

	public static BufferedImage overlayTextureDarken(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													 String outputLocation, String outputDir, String id) throws IOException {
		Graphics2D graphics = bottom.createGraphics();
		for (int i = 0; i < bottom.getHeight(); i++) {
			for (int j = 0; j < bottom.getWidth(); j++) {
				int pixelIn = bottom.getRGB(j, i);
				int redIn = (pixelIn >> 16) & 0xff;
				int greenIn = (pixelIn >> 8) & 0xff;
				int blueIn = (pixelIn) & 0xff;
				int alphaIn = (pixelIn >> 24) & 0xff;
				int pixelOut = overlay.getRGB(j+xLocation, i+yLocation);
				int redOut = (pixelOut >> 16) & 0xff;
				int greenOut = (pixelOut >> 8) & 0xff;
				int blueOut = (pixelOut) & 0xff;
				double alphaOut = (pixelOut >> 24) & 0xff;


				if(alphaOut>0){
					double blendAmount = alphaOut/255;
					int r = (int) (((Math.abs(redOut*(blendAmount)-redIn))));
					int g = (int) (((Math.abs(greenOut*blendAmount-greenIn))));
					int b = (int) (((Math.abs(blueOut*blendAmount-blueIn))));
					int a = (int) Math.min(255, alphaIn + alphaOut);
					graphics.setColor(new Color(r, g, b, a));
					graphics.fillRect(j,i,1,1);
				} else {
					graphics.setColor(new Color(redIn,greenIn,blueIn,alphaIn));
					graphics.fillRect(j,i,1,1);
				}
			}
		}
		saveImage(bottom,outputLocation,outputDir,id);
		return bottom;
	}

	public static BufferedImage overlayTextureMultiplyReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													 String outputLocation, String outputDir, String id, float opacity) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.MULTIPLY,opacity));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureMultiplyReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
															  String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.MULTIPLY));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureLightenReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
															  String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.SOFT_LIGHT));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureMultiply(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													   String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.MULTIPLY));

		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureMultiply(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													   String outputLocation, String outputDir, String id, float opacity) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.MULTIPLY, opacity));

		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureLighten(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													   String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.SOFT_LIGHT));

		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureBurn(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
													  String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.SOFT_BURN));

		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureColorBurn(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
												   String outputLocation, String outputDir, String id) throws IOException {
		return overlayTextureColorBurn(bottom,overlay,xLocation,yLocation,outputLocation,outputDir,id,1);
	}

	public static BufferedImage overlayTextureColorBurn(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
														String outputLocation, String outputDir, String id, float opacity) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.COLOR_BURN, opacity));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureColorBurnReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
															 String outputLocation, String outputDir, String id, float opacity) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.COLOR_BURN, opacity));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureBurnReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
															   String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.SOFT_BURN));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	public static BufferedImage overlayTextureColorBurnReverse(BufferedImage bottom, BufferedImage overlay, int xLocation, int yLocation,
															   String outputLocation, String outputDir, String id) throws IOException {
		BufferedImage bottomINT = convertImageToIntDataType(bottom);
		overlay = convertImageToIntDataType(overlay);
		Graphics2D g = bottomINT.createGraphics();
		System.out.println(bottomINT);
		System.out.println(overlay);
		g.setComposite(new BlendComposite(BlendComposite.BlendingMode.COLOR_BURN));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.drawImage(overlay, xLocation, yLocation, null);

		saveImage(bottomINT, outputLocation, outputDir, id);
		overlayTexture(bottom, bottomINT, xLocation, yLocation, outputLocation, outputDir, id);
		return bottomINT;
	}

	private static void saveImage(BufferedImage image, String outputLocation, String outputDir, String id) throws IOException {
		File outputFile = RelativeFileHelper.getAssetLocation("/"+id +"/textures/" + outputLocation + ".png");
		File dir = RelativeFileHelper.getAssetLocation("/"+id +"/textures/"+ outputDir);
		Files.createDirectories(dir.toPath());
		ImageIO.write(image, "png", outputFile);
	}

	public static BufferedImage convertImageToIntDataType(BufferedImage image){
		BufferedImage imageINT = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = imageINT.createGraphics();
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				int pixelIn = image.getRGB(j, i);
				int alphaIn = (pixelIn >> 24) & 0xff;
				int redOut = (pixelIn >> 16) & 0xff;
				int greenOut = (pixelIn >> 8) & 0xff;
				int blueOut = (pixelIn) & 0xff;
				g.setColor(new Color(redOut, greenOut, blueOut, alphaIn));
				g.fillRect(j,i,1,1);
			}
		}
		return imageINT;
	}

	public static BufferedImage maskImage(BufferedImage mask, BufferedImage overlay, int x, int y, String outputLocation, String outputDir, String id) throws IOException {
		Graphics2D g = mask.createGraphics();
		for (int i = 0; i < mask.getHeight(); i++) {
			for (int j = 0; j < mask.getWidth(); j++) {
				int pixelIn = mask.getRGB(j, i);
				int alphaIn = (pixelIn >> 24) & 0xff;
				int pixelOut = overlay.getRGB(j%overlay.getWidth(), i%overlay.getHeight());
				int redOut = (pixelOut >> 16) & 0xff;
				int greenOut = (pixelOut >> 8) & 0xff;
				int blueOut = (pixelOut) & 0xff;
				g.setColor(new Color(redOut, greenOut, blueOut, alphaIn));
				g.fillRect(j+x,i+y,1,1);
			}
		}
		saveImage(mask,outputLocation,outputDir,id);
		return mask;
	}
}
