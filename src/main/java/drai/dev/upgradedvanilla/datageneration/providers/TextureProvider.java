package drai.dev.upgradedvanilla.datageneration.providers;

import drai.dev.upgradedvanilla.helpers.*;
import net.minecraft.data.*;

import java.io.*;

public class TextureProvider{
	public static void run(){
		TextureHelper.getRequiredTextures().forEach(runnable -> runnable.run());
	}
}
