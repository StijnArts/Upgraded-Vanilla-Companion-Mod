package drai.dev.upgradedvanilla.helpers;

import drai.dev.upgradedvanilla.*;
import net.fabricmc.loader.api.*;

import java.io.*;

public class RelativeFileHelper {
	public static String templateLoc = RelativeFileHelper.class.getClassLoader().getResource("templatedata")
			.toString()
			.replaceAll("%20"," ")
			.replaceAll("file:","")
			.replaceAll("build/resources/main","src/main/resources");
	public static String assetLoc = templateLoc.replaceAll("templatedata","assets");
	public static File getTemplateData(String filePath){
		return new File(templateLoc+filePath);
	}

	public static File getAssetLocation(String filePath){
		return new File(assetLoc+filePath);
	}
}
