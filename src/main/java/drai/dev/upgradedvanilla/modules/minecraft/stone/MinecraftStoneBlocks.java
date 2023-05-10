package drai.dev.upgradedvanilla.modules.minecraft.stone;

import com.simibubi.create.foundation.data.recipe.*;

import drai.dev.upgradedvanilla.*;
import drai.dev.upgradedvanilla.blocks.*;
import drai.dev.upgradedvanilla.datageneration.recipes.*;
import drai.dev.upgradedvanilla.helpers.*;
import drai.dev.upgradedvanilla.helpers.block.*;
import drai.dev.upgradedvanilla.helpers.languages.*;
import drai.dev.upgradedvanilla.model.*;
import drai.dev.upgradedvanilla.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;

import org.apache.commons.io.*;
import org.betterx.bclib.blocks.*;
import org.betterx.betternether.blocks.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.function.*;

public class MinecraftStoneBlocks {
	public static Block cobbledStoneBlock(String material, Block stone, List<TagKey<Block>> blockTags,
										  List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cobbled_"+material, "Cobbled "+StringUtil.capitalizeWord(material),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item,stone);
					}),
				(gen,block)->{gen.add(stone,BlockLoot.createSingleItemTableWithSilkTouch(stone, block));
			                  gen.dropSelf(block);},
				blockTags, itemTags);
		ProcessingRecipeHelper.addMillingRecipe(returnBlock,(gen,item)->{gen.create("cobbled_"+material+"_from_milling",
				b -> b.duration(200).require(stone).output(returnBlock));});
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen,item)->{gen.create("cobbled_"+material+"_from_crushing",
				b -> b.duration(200).require(stone).output(returnBlock));});
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen,item)->{gen.create("gravel_from_crushing_"+"cobbled_"+material,
				b -> b.duration(200).require(returnBlock).output(Blocks.GRAVEL));});
		return returnBlock;
	}
	public static Block mossyCobbledStoneBlock(String material, Block cobbledStone, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("mossy_cobbled_"+material, "Mossy Cobbled "+StringUtil.capitalizeWord(material),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(cobbledStone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					//TODO recipe
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File cobbledStoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(cobbledStone).getPath()+".png");
				File moss = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\mossy_cobblestone.png");
				TextureHelper.overlayTexture(ImageIO.read(cobbledStoneTexture),ImageIO.read(moss), 0, 0, "block\\" + "mossy_cobbled_"+material, "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(cobbledStone,returnBlock));
		return returnBlock;
	}
	public static Block stoneBricksBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_bricks", StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					//TODO only made cutting recipe for origin block
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID, 0.3F);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\stone_bricks_normal.png");
				TextureHelper.overlayTextureLightenReverse(brickTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\stone_bricks_multiply.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\" + material + "_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block chiseledStoneBricksBlock(String material, Block stone, Block stoneBrick, TagKey<Item> stoneBrickSlabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("chiseled_"+material+"_bricks", "Chiseled "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBrick);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneBrickSlabTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_brick_slabs", FabricRecipeProvider.has(stoneBrickSlabTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID, 0.3F);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\chiseled_stone_bricks_light.png");
				TextureHelper.overlayTextureLightenReverse(brickTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\chiseled_stone_brick_shade.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\chiseled_" + material + "_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block stoneTilesBlock(String material, Block stoneBricks, Block cobbledStone, Block polishedStone, TagKey<Item> stoneBrickTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_tiles", StringUtil.capitalizeWord(material)+" Tiles",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBricks)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBricks);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,cobbledStone);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,polishedStone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneBrickTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_stone_bricks", FabricRecipeProvider.has(stoneBrickTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID, 0.3F);
				File stoneTilesBurnLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\deepslate_tiles_linear_burn.png");
				TextureHelper.overlayTextureBurnReverse(brickTexture, ImageIO.read(stoneTilesBurnLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
				File stoneTilesDarkenLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\deepslate_tiles_darken.png");
				TextureHelper.overlayTextureDarken(brickTexture, ImageIO.read(stoneTilesDarkenLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\deepslate_tiles_multiply.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\" + material + "_tiles", "block", UpgradedVanilla.ID);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block polishedStoneBricksBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("polished_"+material+"_bricks", "Polished "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\polished_"+material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage brickTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + "polished_"+material+"_bricks", "block", UpgradedVanilla.ID, 1.0F);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\stone_bricks_normal.png");
				TextureHelper.overlayTextureLightenReverse(ImageIO.read(stoneTexture), ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + "polished_"+material+"_bricks", "block", UpgradedVanilla.ID);
				File dropperFrontMultiplyLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\stone_bricks_multiply.png");
				TextureHelper.overlayTextureMultiplyReverse(brickTexture, ImageIO.read(dropperFrontMultiplyLocation), 0, 0, "block\\" + "polished_"+material+"_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedStoneBricksBlock(String material, Block stoneBrick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cracked_"+material+"_bricks", "Cracked "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer, item, stoneBrick);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_bricks.png");
				File cracks = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\cracked_stone_bricks.png");
				TextureHelper.overlayTextureMultiplyReverse(ImageIO.read(stoneBrickTexture),ImageIO.read(cracks), 0, 0, "block\\" + "cracked_"+material+"_bricks", "block", UpgradedVanilla.ID);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		return returnBlock;
	}
	public static Block mossyStoneBricksBlock(String material, Block stoneBrick, TagKey<Item> brickTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("mossy_"+material+"_bricks", "Mossy "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.VINE).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_bricks_from_vines"));
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.MOSS_BLOCK).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_bricks_from_moss"));
					//TODO byg mossy stone crafting Recipe
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stoneBrick,returnBlock));
		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_bricks.png");
				File moss = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\mossy_stone_bricks.png");
				TextureHelper.overlayTexture(ImageIO.read(stoneBrickTexture),ImageIO.read(moss), 0, 0, "block\\" + "mossy_"+material+"_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedPolishedStoneBricksBlock(String material, Block stoneBrick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cracked_"+material+"_polished_bricks", "Cracked Polished "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer, item, stoneBrick);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\polished_"+material+"_bricks.png");
				File cracks = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\cracked_stone_bricks.png");
				TextureHelper.overlayTextureMultiplyReverse(ImageIO.read(stoneBrickTexture),ImageIO.read(cracks), 0, 0, "block\\" + "cracked_"+material+"_polished_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		return returnBlock;
	}
	public static Block mossyPolishedStoneBricksBlock(String material, Block stoneBrick, TagKey<Item> brickTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("mossy_"+material+"_polished_bricks", "Mossy Polished "+StringUtil.capitalizeWord(material)+" Bricks",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.VINE).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_polished_bricks_from_vines"));
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.MOSS_BLOCK).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_polished_bricks_from_moss"));
					//TODO byg mossy stone crafting Recipe
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stoneBrick,returnBlock));
		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\polished_"+material+"_bricks.png");
				File moss = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\mossy_stone_bricks.png");
				TextureHelper.overlayTexture(ImageIO.read(stoneBrickTexture),ImageIO.read(moss), 0, 0, "block\\" + "mossy_"+material+"_polished_bricks", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedStoneTilesBlock(String material, Block stoneBrick, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("cracked_"+material+"_tiles", "Cracked "+StringUtil.capitalizeWord(material)+" Tiles",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer, item, stoneBrick);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_tiles.png");
				File cracks = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\cracked_stone_tiles.png");
				TextureHelper.overlayTextureMultiplyReverse(ImageIO.read(stoneBrickTexture),ImageIO.read(cracks), 0, 0, "block\\" + "cracked_"+material+"_tiles", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block mossyStoneTilesBlock(String material, Block stoneBrick, TagKey<Item> brickTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("mossy_"+material+"_tiles", "Mossy "+StringUtil.capitalizeWord(material)+" Tiles",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stoneBrick)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.VINE).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_tiles_from_vines"));
					ShapelessRecipeBuilder.shapeless(item).requires(brickTag).requires(Items.MOSS_BLOCK).unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(brickTag))
							.save(finishedRecipeConsumer, new ResourceLocation(UpgradedVanilla.ID,"mossy_"+material+"_tiles_from_moss"));
					//TODO byg mossy stone crafting Recipe
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stoneBrick,returnBlock));
		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+material+"_tiles.png");
				File moss = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\mossy_stone_tiles.png");
				TextureHelper.overlayTexture(ImageIO.read(stoneBrickTexture),ImageIO.read(moss), 0, 0, "block\\" + "mossy_"+material+"_tiles", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block polishedStoneBlock(String material, Block stone, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("polished_"+material, "Polished "+StringUtil.capitalizeWord(material),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stone);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("PP")
							.pattern("PP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);


		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage smoothStoneTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + "polished_"+material, "block", UpgradedVanilla.ID, 0.3F);
				File smoothStoneBurnLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\polished_stone_burn.png");
				TextureHelper.overlayTextureColorBurn(smoothStoneTexture, ImageIO.read(smoothStoneBurnLocation), 0, 0, "block\\" + "polished_"+material, "block", UpgradedVanilla.ID,1.0F);
				File smoothStoneShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\polished_stone_shading.png");
				smoothStoneTexture = TextureHelper.overlayTextureBurnReverse(smoothStoneTexture, ImageIO.read(smoothStoneShadeLocation), 0, 0, "block\\" + "polished_"+material, "block", UpgradedVanilla.ID);

				File smoothStoneHighlightLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\polished_stone_highlight.png");
				//smoothStoneTexture = TextureHelper.overlayTextureLighten(smoothStoneTexture, ImageIO.read(smoothStoneHighlightLocation),0,0,"block\\" + "polished_"+material, "block", UpgradedVanilla.ID);
				} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block smoothStoneBlock(String material, Block stone,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("smooth_"+material, "Smooth "+StringUtil.capitalizeWord(material),
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(stone)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item,stone);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage smoothStoneTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + "smooth_"+material, "block", UpgradedVanilla.ID, 0.2F);
				File smoothStoneRimLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\smooth_stone.png");
				BufferedImage smoothStoneRimTexture = TextureHelper.swapColors("block\\" + "smooth_"+material, "block", UpgradedVanilla.ID, ImageIO.read(smoothStoneRimLocation), TextureHelper.stonePresetPalette, out);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\smooth_stone.png");
				smoothStoneTexture = TextureHelper.overlayTextureLighten(smoothStoneTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + "smooth_"+material, "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smoothStoneTexture, smoothStoneRimTexture, 0, 0, "block\\" + "smooth_"+material, "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block oreSet(String material, Block cobbledStoneBlock,TagKey<Item> stoneTag ,SoundType soundType){
		Block quartzOre = oreBlock(material, "quartz", cobbledStoneBlock, stoneTag, Items.QUARTZ,  Blocks.IRON_ORE, List.of(UVCommonBlockTags.QUARTZ_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.QUARTZ_ORES), soundType);
		oreStairsBlock(material, "quartz", cobbledStoneBlock, Items.QUARTZ, quartzOre, List.of(UVCommonBlockTags.QUARTZ_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.QUARTZ_ORES));
		oreSlabBlock(material, "quartz", cobbledStoneBlock, Items.QUARTZ, quartzOre, List.of(UVCommonBlockTags.QUARTZ_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.QUARTZ_ORES));
		oreWallBlock(material, "quartz", cobbledStoneBlock, Items.QUARTZ, quartzOre, List.of(UVCommonBlockTags.QUARTZ_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.QUARTZ_ORES));
		Block lapisOre = oreBlock(material, "lapis", cobbledStoneBlock, stoneTag, Items.LAPIS_LAZULI, Blocks.LAPIS_ORE, List.of(UVCommonBlockTags.LAPIS_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.LAPIS_ORES), soundType);
		oreStairsBlock(material, "lapis", cobbledStoneBlock, Items.LAPIS_LAZULI, lapisOre, List.of(UVCommonBlockTags.LAPIS_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.LAPIS_ORES));
		oreSlabBlock(material, "lapis", cobbledStoneBlock, Items.LAPIS_LAZULI, lapisOre, List.of(UVCommonBlockTags.LAPIS_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.LAPIS_ORES));
		oreWallBlock(material, "lapis", cobbledStoneBlock, Items.LAPIS_LAZULI, lapisOre, List.of(UVCommonBlockTags.LAPIS_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.LAPIS_ORES));
		Block emeraldOre = oreBlock(material, "emerald", cobbledStoneBlock, stoneTag, Items.EMERALD, Blocks.EMERALD_ORE, List.of(UVCommonBlockTags.EMERALD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.EMERALD_ORES), soundType);
		oreStairsBlock(material, "emerald", cobbledStoneBlock, Items.EMERALD, emeraldOre, List.of(UVCommonBlockTags.EMERALD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.EMERALD_ORES));
		oreSlabBlock(material, "emerald", cobbledStoneBlock, Items.EMERALD, emeraldOre, List.of(UVCommonBlockTags.EMERALD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.EMERALD_ORES));
		oreWallBlock(material, "emerald", cobbledStoneBlock, Items.EMERALD, emeraldOre, List.of(UVCommonBlockTags.EMERALD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.EMERALD_ORES));
		Block diamondOre = oreBlock(material, "diamond", cobbledStoneBlock, stoneTag, Items.DIAMOND, Blocks.DIAMOND_ORE, List.of(UVCommonBlockTags.DIAMOND_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.DIAMOND_ORES), soundType);
		oreStairsBlock(material, "diamond", cobbledStoneBlock, Items.DIAMOND, diamondOre, List.of(UVCommonBlockTags.DIAMOND_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.DIAMOND_ORES));
		oreSlabBlock(material, "diamond", cobbledStoneBlock, Items.DIAMOND, diamondOre, List.of(UVCommonBlockTags.DIAMOND_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.DIAMOND_ORES));
		oreWallBlock(material, "diamond", cobbledStoneBlock, Items.DIAMOND, diamondOre, List.of(UVCommonBlockTags.DIAMOND_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.DIAMOND_ORES));
		Block goldOre = oreBlock(material, "gold", cobbledStoneBlock, stoneTag, Items.RAW_GOLD, Blocks.GOLD_ORE , List.of(UVCommonBlockTags.GOLD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.GOLD_ORES), soundType);
		oreStairsBlock(material, "gold", cobbledStoneBlock, Items.RAW_GOLD, goldOre, List.of(UVCommonBlockTags.GOLD_ORES, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.GOLD_ORES));
		oreSlabBlock(material, "gold", cobbledStoneBlock, Items.RAW_GOLD, goldOre, List.of(UVCommonBlockTags.GOLD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.GOLD_ORES));
		oreWallBlock(material, "gold", cobbledStoneBlock, Items.RAW_GOLD, goldOre, List.of(UVCommonBlockTags.GOLD_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.GOLD_ORES));
		Block ironOre = oreBlock(material, "iron", cobbledStoneBlock, stoneTag, Items.RAW_IRON, Blocks.IRON_ORE, List.of(UVCommonBlockTags.IRON_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.IRON_ORES), soundType);
		oreStairsBlock(material, "iron", cobbledStoneBlock, Items.RAW_IRON, ironOre, List.of(UVCommonBlockTags.IRON_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.IRON_ORES));
		oreSlabBlock(material, "iron", cobbledStoneBlock, Items.RAW_IRON, ironOre, List.of(UVCommonBlockTags.IRON_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.IRON_ORES));
		oreWallBlock(material, "iron", cobbledStoneBlock, Items.RAW_IRON, ironOre, List.of(UVCommonBlockTags.IRON_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.IRON_ORES));
		Block copperOre = oreBlock(material, "copper", cobbledStoneBlock, stoneTag, Items.RAW_COPPER, Blocks.COPPER_ORE, List.of(UVCommonBlockTags.COPPER_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COPPER_ORES), soundType);
		oreStairsBlock(material, "copper", cobbledStoneBlock, Items.RAW_COPPER, copperOre, List.of(UVCommonBlockTags.COPPER_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COPPER_ORES));
		oreSlabBlock(material, "copper", cobbledStoneBlock, Items.RAW_COPPER, copperOre, List.of(UVCommonBlockTags.COPPER_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COPPER_ORES));
		oreWallBlock(material, "copper", cobbledStoneBlock, Items.RAW_COPPER, copperOre, List.of(UVCommonBlockTags.COPPER_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COPPER_ORES));
		Block coalOre = oreBlock(material, "coal", cobbledStoneBlock, stoneTag, Items.COAL, Blocks.COAL_ORE, List.of(UVCommonBlockTags.COAL_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COAL_ORES), soundType);
		oreStairsBlock(material, "coal", cobbledStoneBlock, Items.COAL, coalOre, List.of(UVCommonBlockTags.COAL_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COAL_ORES));
		oreSlabBlock(material, "coal", cobbledStoneBlock, Items.COAL, coalOre, List.of(UVCommonBlockTags.COAL_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COAL_ORES));
		oreWallBlock(material, "coal", cobbledStoneBlock, Items.COAL, coalOre, List.of(UVCommonBlockTags.COAL_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.COAL_ORES));
		Block redstoneOre = redstoneOreBlock(material, "redstone", cobbledStoneBlock, stoneTag, Items.REDSTONE, Blocks.REDSTONE_ORE, List.of(UVCommonBlockTags.REDSTONE_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.REDSTONE_ORES), soundType);
		redstoneOreStairsBlock(material, "redstone", cobbledStoneBlock, Items.REDSTONE, redstoneOre, List.of(UVCommonBlockTags.REDSTONE_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.REDSTONE_ORES));
		redstoneOreSlabBlock(material, "redstone", cobbledStoneBlock, Items.REDSTONE, redstoneOre, List.of(UVCommonBlockTags.REDSTONE_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.REDSTONE_ORES));
		redstoneOreWallBlock(material, "redstone", cobbledStoneBlock, Items.REDSTONE, redstoneOre, List.of(UVCommonBlockTags.REDSTONE_ORES, BlockTags.MINEABLE_WITH_PICKAXE), List.of(UVCommonItemTags.REDSTONE_ORES));
		return coalOre;
	}
	public static Block oreBlock(String material, String mineral, Block cobbledStoneBlock, TagKey<Item> stoneTag, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore", StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore",
				UpgradedVanilla.ID, new Block(FabricBlockSettings.copy(baseOre).sound(soundType)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{blockModelGenerators.createTrivialCube((Block) block);},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag).define('M', rawMineralItem)
							.pattern("PMP")
							.pattern("MPM")
							.pattern("PMP")
							.unlockedBy("has_raw_" + mineral, FabricRecipeProvider.has(rawMineralItem)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File ore = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\"+mineral+"_ore.png");
				BufferedImage oreTexture = TextureHelper.overlayTexture(ImageIO.read(stoneTexture),ImageIO.read(ore), 0, 0, "block\\" + material+"_"+mineral+"_ore", "block", UpgradedVanilla.ID);
				File shade = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\"+mineral+"_ore_shade.png");
				TextureHelper.overlayTextureMultiplyReverse(oreTexture,ImageIO.read(shade), 0, 0, "block\\" + material+"_"+mineral+"_ore", "block", UpgradedVanilla.ID, 0.3F);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block oreStairsBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_stairs",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Stairs",
				UpgradedVanilla.ID, new StairBlock(cobbledStoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(baseOre)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre);
					ShapedRecipeBuilder.shaped(item, 4).define('P', baseOre)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" +material+"_"+ mineral + "_ore", FabricRecipeProvider.has(baseOre)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});
		return returnBlock;
	}
	public static Block oreSlabBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_slab",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(baseOre)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(baseOre)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',baseOre)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(baseOre)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,1.25f, 250);});
		return returnBlock;
	}
	public static Block oreWallBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_wall",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(baseOre)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre);
					ShapedRecipeBuilder.shaped(item,6).define('P',baseOre)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_" +material+"_"+ mineral + "_ore", FabricRecipeProvider.has(baseOre))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});
		return returnBlock;
	}
	public static Block redstoneOreBlock(String material, String mineral, Block cobbledStoneBlock, TagKey<Item> stoneTag, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore", StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore",
				UpgradedVanilla.ID, new RedStoneOreBlock(FabricBlockSettings.copy(baseOre).sound(soundType)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					blockModelGenerators.createTrivialCube((Block) block);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag).define('M', rawMineralItem)
							.pattern("PMP")
							.pattern("MPM")
							.pattern("PMP")
							.unlockedBy("has_raw_" + mineral, FabricRecipeProvider.has(rawMineralItem)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File ore = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\"+mineral+"_ore.png");
				BufferedImage oreTexture = TextureHelper.overlayTexture(ImageIO.read(stoneTexture),ImageIO.read(ore), 0, 0, "block\\" + material+"_"+mineral+"_ore", "block", UpgradedVanilla.ID);
				File shade = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\"+mineral+"_ore_shade.png");
				TextureHelper.overlayTextureMultiplyReverse(oreTexture,ImageIO.read(shade), 0, 0, "block\\" + material+"_"+mineral+"_ore", "block", UpgradedVanilla.ID, 0.3F);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block redstoneOreStairsBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_stairs",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Stairs",
				UpgradedVanilla.ID, new StairBlock(cobbledStoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(cobbledStoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre);
					ShapedRecipeBuilder.shaped(item, 4).define('P', baseOre)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" +material+"_"+ mineral + "_ore", FabricRecipeProvider.has(baseOre)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});
		return returnBlock;
	}
	public static Block redstoneOreSlabBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_slab",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(cobbledStoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(baseOre)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',baseOre)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(baseOre)).save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem))));},
				blockTags, itemTags);
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,1.25f, 250);});
		return returnBlock;
	}
	public static Block redstoneOreWallBlock(String material, String mineral, Block cobbledStoneBlock, Item rawMineralItem, Block baseOre, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material+"_"+mineral+"_ore_wall",
				StringUtil.capitalizeWord(material)+" "+StringUtil.capitalizeWord(mineral)+" Ore Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(cobbledStoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(baseOre);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,baseOre);
					ShapedRecipeBuilder.shaped(item,6).define('P',baseOre)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_" +material+"_"+ mineral + "_ore", FabricRecipeProvider.has(baseOre))
							.save(finishedRecipeConsumer);
				}),
				(gen,block)->{gen.add(block,BlockLoot.createSilkTouchDispatchTable(block, BlockLoot.applyExplosionDecay(block, LootItem.lootTableItem(rawMineralItem)
						.apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)))));},
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addCrushingRecipe(returnBlock,(gen, item)->{gen.ore(cobbledStoneBlock, ()->returnBlock,()->rawMineralItem,2.25f, 350);});
		return returnBlock;
	}
	public static Block stoneStairsBlock(String material, Block stoneBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block stoneSlabBlock(String material, Block stoneBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block smoothStoneSlabBlock(String material, Block stoneBlock, TagKey<Item> smoothStoneTag,List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags, File out) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe("smooth_"+ material + "_slab", "Smooth "+ StringUtil.capitalizeWord(material) + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMapping2 = TextureMapping.column(new ResourceLocation(UpgradedVanilla.ID,"block/smooth_"+material+"_slab_side"), textureMapping.get(TextureSlot.TOP));
					ResourceLocation resourceLocation = ModelTemplates.SLAB_BOTTOM.create((Block) block, textureMapping2, blockModelGenerators.modelOutput);
					ResourceLocation resourceLocation2 = ModelTemplates.SLAB_TOP.create((Block) block, textureMapping2, blockModelGenerators.modelOutput);
					ResourceLocation resourceLocation3 = ModelTemplates.CUBE_COLUMN.createWithOverride((Block) block, "_double", textureMapping2, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, resourceLocation, resourceLocation2, resourceLocation3));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',smoothStoneTag)
							.pattern("PPP")
							.unlockedBy("has_smooth_"+material+"_stone", FabricRecipeProvider.has(smoothStoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+".png");
				File stoneColor = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ material+"_color.png");
				BufferedImage smoothStoneTexture = TextureHelper.overlayTexture(ImageIO.read(stoneColor),ImageIO.read(stoneTexture), 0, 0, "block\\" + "smooth_"+material+"_slab_side", "block", UpgradedVanilla.ID, 0.2F);
				File smoothStoneRimLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\smooth_stone_slab_side.png");
				BufferedImage smoothStoneRimTexture = TextureHelper.swapColors("block\\" + "smooth_"+material+"_slab_side", "block", UpgradedVanilla.ID, ImageIO.read(smoothStoneRimLocation), TextureHelper.stonePresetPalette, out);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\smooth_stone_slab_side.png");
				smoothStoneTexture = TextureHelper.overlayTextureLighten(smoothStoneTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + "smooth_"+material+"_slab_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(smoothStoneTexture, smoothStoneRimTexture, 0, 0, "block\\" + "smooth_"+material+"_slab_side", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block stoneFenceBlock(String material, Block stoneBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block stoneFenceGateBlock(String material, Block stoneBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block stoneButtonBlock(String material, Block stoneBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");
				TextureHelper.flipTexture(ImageIO.read(stoneBrickTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block stonePressurePlateBlock(String material, Block stoneBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block stoneTrapdoorBlock(String material, Block stoneBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		TextureHelper.addTexture(() -> {
			try {
				File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");
				TextureHelper.flipTexture(ImageIO.read(stoneBrickTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block stoneWallBlock(String material, Block stoneBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					//TODO stonecutting recipe
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block crackedStoneTileStairsBlock(String material, Block stoneBlock, Block stairsBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer,Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs"+"_from_crafting");
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, stairsBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);

				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block crackedStoneTileSlabBlock(String material, Block stoneBlock, Block slabBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, slabBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block crackedStoneTileFenceBlock(String material, Block stoneBlock, Block fenceBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneTileFenceGateBlock(String material, Block stoneBlock, Block fenceGateBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceGateBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneTileButtonBlock(String material, Block stoneBlock, Block buttonBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, buttonBlock);
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			File stoneTileTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");

			try {
				TextureHelper.flipTexture(ImageIO.read(stoneTileTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedStoneTilePressurePlateBlock(String material, Block stoneBlock, Block pressurePlateBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, pressurePlateBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneTileTrapdoorBlock(String material, Block stoneBlock, Block trapdoorBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, trapdoorBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block crackedStoneTileWallBlock(String material, Block stoneBlock, Block wallBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, wallBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block mossyStoneTileStairsBlock(String material, Block stoneBlock, Block stairsBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer,Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs"+"_from_crafting");
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);

				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stairsBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileSlabBlock(String material, Block stoneBlock, Block slabBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, slabBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(slabBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileFenceBlock(String material, Block stoneBlock, Block fenceBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileFenceGateBlock(String material, Block stoneBlock, Block fenceGateBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceGateBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceGateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileButtonBlock(String material, Block stoneBlock, Block buttonBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, buttonBlock);
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			File stoneTileTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");

			try {
				TextureHelper.flipTexture(ImageIO.read(stoneTileTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(buttonBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTilePressurePlateBlock(String material, Block stoneBlock, Block pressurePlateBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, pressurePlateBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(pressurePlateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileTrapdoorBlock(String material, Block stoneBlock, Block trapdoorBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, trapdoorBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(trapdoorBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneTileWallBlock(String material, Block stoneBlock, Block wallBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, wallBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(wallBlock,returnBlock));
		return returnBlock;
	}
	public static Block crackedStoneBrickStairsBlock(String material, Block stoneBlock, Block stairsBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer,Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs"+"_from_crafting");
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, stairsBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);

				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block crackedStoneBrickSlabBlock(String material, Block stoneBlock, Block slabBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		return BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, slabBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
	}
	public static Block crackedStoneBrickFenceBlock(String material, Block stoneBlock, Block fenceBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneBrickFenceGateBlock(String material, Block stoneBlock, Block fenceGateBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceGateBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneBrickButtonBlock(String material, Block stoneBlock, Block buttonBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, buttonBlock);
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");

			try {
				TextureHelper.flipTexture(ImageIO.read(stoneBrickTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block crackedStoneBrickPressurePlateBlock(String material, Block stoneBlock, Block pressurePlateBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, pressurePlateBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		return returnBlock;
	}
	public static Block crackedStoneBrickTrapdoorBlock(String material, Block stoneBlock, Block trapdoorBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, trapdoorBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block crackedStoneBrickWallBlock(String material, Block stoneBlock, Block wallBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, wallBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		return returnBlock;
	}
	public static Block mossyCobbledStoneStairsBlock(String material, Block stoneBlock, Block stairsBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer,Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs"+"_from_crafting");
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);

				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stairsBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneSlabBlock(String material, Block stoneBlock, Block slabBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, slabBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(slabBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneFenceBlock(String material, Block stoneBlock, Block fenceBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneFenceGateBlock(String material, Block stoneBlock, Block fenceGateBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceGateBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceGateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneButtonBlock(String material, Block stoneBlock, Block buttonBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, buttonBlock);
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			File CobbledStoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");

			try {
				TextureHelper.flipTexture(ImageIO.read(CobbledStoneTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(buttonBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStonePressurePlateBlock(String material, Block stoneBlock, Block pressurePlateBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, pressurePlateBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(pressurePlateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneTrapdoorBlock(String material, Block stoneBlock, Block trapdoorBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, trapdoorBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(trapdoorBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyCobbledStoneWallBlock(String material, Block stoneBlock, Block wallBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, wallBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(wallBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickStairsBlock(String material, Block stoneBlock, Block stairsBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs",  sourceCapitalized.trim()+ " Stairs",
				UpgradedVanilla.ID, new StairBlock(stoneBlock.defaultBlockState(), FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
					blockModelGenerators.delegateItemModel((Block) block, straight);
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 4).define('P', stoneTag)
							.pattern("P  ")
							.pattern("PP ")
							.pattern("PPP")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer,Registry.BLOCK.getKey(stoneBlock).getPath() + "_stairs"+"_from_crafting");
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);

				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(stairsBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickSlabBlock(String material, Block stoneBlock, Block slabBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_slab", sourceCapitalized.trim() + " Slab",
				UpgradedVanilla.ID, new SlabBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
							ModelLocationUtils.getModelLocation(stoneBlock)));
					blockModelGenerators.delegateItemModel((Block) block,bottom);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, slabBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock,2);
					ShapedRecipeBuilder.shaped(item,6).define('P',stoneTag)
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(slabBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickFenceBlock(String material, Block stoneBlock, Block fenceBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence", sourceCapitalized.trim() + " Fence",
				UpgradedVanilla.ID, new FenceBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,3).define('P',stoneTag).define('S',slabTag)
							.pattern("PSP")
							.pattern("PSP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickFenceGateBlock(String material, Block stoneBlock, Block fenceGateBlock, TagKey<Item> slabTag, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}

		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_fence_gate", sourceCapitalized.trim() + " Fence Gate",
				UpgradedVanilla.ID, new FenceGateBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_BUILDING_BLOCKS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall));

				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, fenceGateBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag).define('S',slabTag)
							.pattern("SPS")
							.pattern("SPS")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag)).save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(fenceGateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickButtonBlock(String material, Block stoneBlock, Block buttonBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_button", sourceCapitalized.trim() + " Button",
				UpgradedVanilla.ID, new StoneButtonBlock(FabricBlockSettings.copyOf(stoneBlock).noCollision()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
					blockModelGenerators.delegateItemModel((Block) block,inventory);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, buttonBlock);
					ShapelessRecipeBuilder.shapeless(item,1).requires(stoneTag)
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		TextureHelper.addTexture(() -> {
			File stoneBrickTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\"+Registry.BLOCK.getKey(stoneBlock).getPath()+".png");

			try {
				TextureHelper.flipTexture(ImageIO.read(stoneBrickTexture), "block\\" + Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(buttonBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickPressurePlateBlock(String material, Block stoneBlock, Block pressurePlateBlock, TagKey<Item> stoneTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_pressure_plate", sourceCapitalized.trim() + " Pressure Plate",
				UpgradedVanilla.ID, new StonePressurePlateBlock(stoneBlock), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
					blockModelGenerators.delegateItemModel((Block) block,up);
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, pressurePlateBlock);
					ShapedRecipeBuilder.shaped(item,1).define('P',stoneTag)
							.pattern("PP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(stoneTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(pressurePlateBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickTrapdoorBlock(String material, Block stoneBlock, Block trapdoorBlock, TagKey<Item> slabTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_trapdoor", sourceCapitalized.trim() + " Trapdoor",
				UpgradedVanilla.ID, new TrapDoorBlock(FabricBlockSettings.copyOf(stoneBlock).noOcclusion()), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					TextureMapping textureMappingOpen = new TextureMapping()
							.put(TextureSlot.TEXTURE,new ResourceLocation(UpgradedVanilla.ID,"block/"+Registry.BLOCK.getKey(stoneBlock).getPath()+"_flipped"));
					ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMappingOpen, blockModelGenerators.modelOutput);
					ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, bottom);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, trapdoorBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,2).define('P',slabTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stones", FabricRecipeProvider.has(slabTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(trapdoorBlock,returnBlock));
		return returnBlock;
	}
	public static Block mossyStoneBrickWallBlock(String material, Block stoneBlock, Block wallBlock, TagKey<Item> blockTag, List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags) {
		String sourceCapitalized = "";
		for (String segment :Registry.BLOCK.getKey(stoneBlock).getPath().split("_")) {
			sourceCapitalized = sourceCapitalized +" "+StringUtil.capitalizeWord(segment);
		}
		Block returnBlock = BlockHandler.registerBlockWithRecipe(Registry.BLOCK.getKey(stoneBlock).getPath() + "_wall", sourceCapitalized.trim() + " Wall",
				UpgradedVanilla.ID, new WallBlock(FabricBlockSettings.copyOf(stoneBlock)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators,block)->{
					TextureMapping textureMapping = TextureMapping.cube(stoneBlock);
					ResourceLocation inventory = ModelTemplates.WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation post = ModelTemplates.WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation tallSide = ModelTemplates.WALL_TALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					ResourceLocation lowSide = ModelTemplates.WALL_LOW_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
					blockModelGenerators.delegateItemModel((Block) block, inventory);
					blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide,tallSide));
				},
				((finishedRecipeConsumer, item) -> {
					UVRecipeProvider.smeltingResultFromBase(finishedRecipeConsumer,item, wallBlock);
					FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer,item,stoneBlock);
					ShapedRecipeBuilder.shaped(item,6).define('P',blockTag)
							.pattern("PPP")
							.pattern("PPP")
							.unlockedBy("has_"+material+"_stone_slabs", FabricRecipeProvider.has(blockTag))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);
		BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
		ProcessingRecipeHelper.addWashingRecipe(returnBlock,(gen,itemLike)->gen.convert(wallBlock,returnBlock));
		return returnBlock;
	}
	public static Block furnaceBlock(String material, Block cobbledStone, TagKey<Item> stoneBlocks, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_furnace", StringUtil.capitalizeWord(material) + " Furnace",
				UpgradedVanilla.ID, new FurnaceBlock(FabricBlockSettings.copyOf(Blocks.FURNACE).sounds(soundType)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingOff = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.FURNACE.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					TextureMapping textureMappingOn = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_front_on"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocationOn = UpgradedVanillaModelTemplates.FURNACE_ON.create((Block) block, textureMappingOn, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, resourceLocationOn, resourceLocation))
							.with(BlockModelGenerators.createHorizontalFacingDispatch()));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('S', stoneBlocks)
							.pattern("SSS")
							.pattern("S S")
							.pattern("SSS")
							.unlockedBy("has_" + material + "_stone", FabricRecipeProvider.has(stoneBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(cobbledStone).getPath()+".png");
			File furnaceFrontTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\furnace_front.png");
			File furnaceFrontOnTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\furnace_front_on.png");
			File furnaceTopTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\furnace_top.png");
			File furnaceSideTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\furnace_side.png");
			try {
				BufferedImage furnaceFrontTexture = TextureHelper.swapColors("block\\" + material + "_furnace_front", "block", UpgradedVanilla.ID, ImageIO.read(furnaceFrontTextureLocation), TextureHelper.stonePresetPalette, out);
				File furnaceFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\furnace_front.png");
				TextureHelper.overlayTexture(furnaceFrontTexture, ImageIO.read(furnaceFrontOverlayLocation), 0, 0, "block\\" + material + "_furnace_front", "block", UpgradedVanilla.ID);

				BufferedImage furnaceFrontOnTexture =TextureHelper.swapColors("block\\" + material + "_furnace_front_on", "block", UpgradedVanilla.ID, ImageIO.read(furnaceFrontOnTextureLocation), TextureHelper.stonePresetPalette, out);
				File furnaceFrontOnOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\furnace_front_on.png");
				TextureHelper.overlayTexture(furnaceFrontOnTexture, ImageIO.read(furnaceFrontOnOverlayLocation), 0, 0, "block\\" + material + "_furnace_front_on", "block", UpgradedVanilla.ID);

				BufferedImage furnaceTopTexture =TextureHelper.swapColors("block\\" + material + "_furnace_top", "block", UpgradedVanilla.ID, ImageIO.read(furnaceTopTextureLocation), TextureHelper.stonePresetPalette, out);
				File furnaceFrontTopMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\furnace_top.png");
				BufferedImage furnaceTopStoneTexture = TextureHelper.maskImage(ImageIO.read(furnaceFrontTopMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + material + "_furnace_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(furnaceTopTexture, furnaceTopStoneTexture, 0, 0, "block\\" + material + "_furnace_top", "block", UpgradedVanilla.ID);

				BufferedImage furnaceSideTexture =TextureHelper.swapColors("block\\" + material + "_furnace_side", "block", UpgradedVanilla.ID, ImageIO.read(furnaceSideTextureLocation), TextureHelper.stonePresetPalette, out);
				File furnaceFrontSideMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\furnace_side.png");
				BufferedImage furnaceSideStoneTexture = TextureHelper.maskImage(ImageIO.read(furnaceFrontSideMaskLocation),ImageIO.read(stoneTexture), 0,0, "block\\" + material + "_furnace_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(furnaceSideTexture, furnaceSideStoneTexture, 0, 0, "block\\" + material + "_furnace_side", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block dispenserBlock(String material, Block furnace, Block cobbledStone, TagKey<Item> cobbledStoneBlocks, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_dispenser", StringUtil.capitalizeWord(material) + " Dispenser",
				UpgradedVanilla.ID, new DispenserBlock(FabricBlockSettings.copyOf(Blocks.DISPENSER).sounds(soundType)), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingHorizontal = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_dispenser_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(Registry.BLOCK.getKey(furnace).getNamespace(), "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocationHorizontal = ModelTemplates.CUBE_ORIENTABLE.create((Block) block, textureMappingHorizontal, blockModelGenerators.modelOutput);
					TextureMapping textureMappingVertical = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_dispenser_front_vertical"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(Registry.BLOCK.getKey(furnace).getNamespace(), "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocationVertical = ModelTemplates.CUBE_ORIENTABLE_VERTICAL.create((Block) block, textureMappingVertical, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(PropertyDispatch.property(BlockStateProperties.FACING).select(Direction.DOWN, Variant.variant()
									.with(VariantProperties.MODEL, resourceLocationVertical).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
									.select(Direction.UP, Variant.variant().with(VariantProperties.MODEL, resourceLocationVertical))
									.select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal))
									.select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(Direction.SOUTH, Variant.variant()
											.with(VariantProperties.MODEL, resourceLocationHorizontal).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('S', cobbledStoneBlocks).define('R',UVCommonItemTags.REDSTONE_DUSTS).define('B',Items.BOW)
							.pattern("SSS")
							.pattern("SBS")
							.pattern("SRS")
							.unlockedBy("has_bow", FabricRecipeProvider.has(Items.BOW))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(cobbledStone).getPath()+".png");
				File dispenserFrontTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\dispenser_front.png");
				File dispenserFrontVerticalTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\dispenser_front_vertical.png");

				BufferedImage dispenserFrontTexture = TextureHelper.swapColors("block\\" + material + "_dispenser_front", "block", UpgradedVanilla.ID, ImageIO.read(dispenserFrontTextureLocation), TextureHelper.stonePresetPalette, out);
				File dispenserFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\dispenser_front.png");
				TextureHelper.overlayTextureDarken(dispenserFrontTexture, ImageIO.read(dispenserFrontOverlayLocation), 0, 0, "block\\" + material + "_dispenser_front", "block", UpgradedVanilla.ID);

				BufferedImage dispenserFrontVerticalTexture =TextureHelper.swapColors("block\\" + material + "_dispenser_front_vertical", "block", UpgradedVanilla.ID, ImageIO.read(dispenserFrontVerticalTextureLocation), TextureHelper.stonePresetPalette, out);
				File dispenserFrontVerticalMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\dispenser_front_vertical.png");
				BufferedImage dispenserFrontVerticalMaskedTexture = TextureHelper.maskImage(ImageIO.read(dispenserFrontVerticalMaskLocation),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_dispenser_front_vertical", "block", UpgradedVanilla.ID);
				dispenserFrontVerticalTexture = TextureHelper.overlayTexture(dispenserFrontVerticalTexture, dispenserFrontVerticalMaskedTexture, 0, 0, "block\\" + material + "_dispenser_front_vertical", "block", UpgradedVanilla.ID);
				File dispenserFrontVerticalOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\dispenser_front_vertical.png");
				TextureHelper.overlayTextureDarken(dispenserFrontVerticalTexture, ImageIO.read(dispenserFrontVerticalOverlayLocation), 0, 0, "block\\" + material + "_dispenser_front_vertical", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block dropperBlock(String material, Block furnace, Block cobbledStone, TagKey<Item> cobbledStoneBlocks, List<TagKey<Block>> blockTags,
									   List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_dropper", StringUtil.capitalizeWord(material) + " Dropper",
				UpgradedVanilla.ID, new DropperBlock(FabricBlockSettings.copyOf(Blocks.DROPPER).sounds(soundType)), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingHorizontal = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_dropper_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(Registry.BLOCK.getKey(furnace).getNamespace(), "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocationHorizontal = ModelTemplates.CUBE_ORIENTABLE.create((Block) block, textureMappingHorizontal, blockModelGenerators.modelOutput);
					TextureMapping textureMappingVertical = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_dropper_front_vertical"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(Registry.BLOCK.getKey(furnace).getNamespace(), "block/"+material + "_furnace_top"));
					ResourceLocation resourceLocationVertical = ModelTemplates.CUBE_ORIENTABLE_VERTICAL.create((Block) block, textureMappingVertical, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(PropertyDispatch.property(BlockStateProperties.FACING).select(Direction.DOWN, Variant.variant()
											.with(VariantProperties.MODEL, resourceLocationVertical).with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
									.select(Direction.UP, Variant.variant().with(VariantProperties.MODEL, resourceLocationVertical))
									.select(Direction.NORTH, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal))
									.select(Direction.EAST, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)).select(Direction.SOUTH, Variant.variant()
											.with(VariantProperties.MODEL, resourceLocationHorizontal).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
									.select(Direction.WEST, Variant.variant().with(VariantProperties.MODEL, resourceLocationHorizontal)
											.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('S', cobbledStoneBlocks).define('R',UVCommonItemTags.REDSTONE_DUSTS)
							.pattern("SSS")
							.pattern("S S")
							.pattern("SRS")
							.unlockedBy("has_bow", FabricRecipeProvider.has(Items.BOW))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(cobbledStone).getPath()+".png");
				File dropperFrontTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\dropper_front.png");
				File dropperFrontVerticalTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\dropper_front_vertical.png");
				BufferedImage dropperFrontTexture = TextureHelper.swapColors("block\\" + material + "_dropper_front", "block", UpgradedVanilla.ID, ImageIO.read(dropperFrontTextureLocation), TextureHelper.stonePresetPalette, out);
				File dropperFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\dropper_front.png");
				TextureHelper.overlayTextureDarken(dropperFrontTexture, ImageIO.read(dropperFrontOverlayLocation), 0, 0, "block\\" + material + "_dropper_front", "block", UpgradedVanilla.ID);

				BufferedImage dropperFrontVerticalTexture =TextureHelper.swapColors("block\\" + material + "_dropper_front_vertical", "block", UpgradedVanilla.ID, ImageIO.read(dropperFrontVerticalTextureLocation), TextureHelper.stonePresetPalette, out);
				File dropperFrontVerticalMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\dropper_front_vertical.png");
				BufferedImage dropperFrontVerticalMaskedTexture = TextureHelper.maskImage(ImageIO.read(dropperFrontVerticalMaskLocation),ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_dropper_front_vertical", "block", UpgradedVanilla.ID);
				dropperFrontVerticalTexture = TextureHelper.overlayTexture(dropperFrontVerticalTexture, dropperFrontVerticalMaskedTexture, 0, 0, "block\\" + material + "_dropper_front_vertical", "block", UpgradedVanilla.ID);
				File dropperFrontVerticalOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\dropper_front_vertical.png");
				TextureHelper.overlayTextureDarken(dropperFrontVerticalTexture, ImageIO.read(dropperFrontVerticalOverlayLocation), 0, 0, "block\\" + material + "_dropper_front_vertical", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block blastFurnaceBlock(String material, Block furnace, Block stone, TagKey<Item> smoothStoneBlocks, List<TagKey<Block>> blockTags,
									 List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_blast_furnace", StringUtil.capitalizeWord(material) + " Blast Furnace",
				UpgradedVanilla.ID, new BlastFurnaceBlock(FabricBlockSettings.copyOf(Blocks.BLAST_FURNACE).sounds(soundType)), CreativeModeTab.TAB_DECORATIONS,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingOff = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_top"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.FURNACE.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					TextureMapping textureMappingOn = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_front_on"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_blast_furnace_top"));
					ResourceLocation resourceLocationOn = UpgradedVanillaModelTemplates.FURNACE_ON.create((Block) block, textureMappingOn, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, resourceLocationOn, resourceLocation))
							.with(BlockModelGenerators.createHorizontalFacingDispatch()));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('S', smoothStoneBlocks).define('I', UVCommonItemTags.IRON_INGOTS).define('F',furnace)
							.pattern("III")
							.pattern("IFI")
							.pattern("SSS")
							.unlockedBy("has_" + material + "_smooth_stone", FabricRecipeProvider.has(smoothStoneBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(stone).getPath()+".png");
				File blast_furnaceFrontTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\blast_furnace_front.png");
				File blast_furnaceFrontOnTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\blast_furnace_front_on.png");
				File blast_furnaceTopTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\blast_furnace_top.png");
				File blast_furnaceSideTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\blast_furnace_side.png");
				FileUtils.copyFile(new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\blast_furnace_front_on.png.mcmeta"),
						new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\create-fabric-addon-template-1.19\\" +
								"create-fabric-addon-template-1.19\\src\\main\\resources\\assets\\upgradedvanilla\\textures\\block\\" + material + "_blast_furnace_front_on.png.mcmeta"));

				BufferedImage blast_furnaceFront_onTexture = TextureHelper.swapColors("block\\" + material + "_blast_furnace_front_on", "block", UpgradedVanilla.ID, ImageIO.read(blast_furnaceFrontOnTextureLocation), TextureHelper.stonePresetPalette, out);
				File blast_furnaceFront_onMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front_on_shade.png");
				BufferedImage blast_furnaceStoneFront_onTexture = TextureHelper.maskImage(ImageIO.read(blast_furnaceFront_onMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_blast_furnace_front_on", "block", UpgradedVanilla.ID);
				File blast_furnaceFront_onShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front_on_shade.png");
				blast_furnaceStoneFront_onTexture= TextureHelper.overlayTextureMultiply(blast_furnaceStoneFront_onTexture, ImageIO.read(blast_furnaceFront_onShadeLocation), 0, 0, "block\\" + material + "_blast_furnace_front_on", "block", UpgradedVanilla.ID);
				blast_furnaceFront_onTexture = TextureHelper.overlayTexture(blast_furnaceFront_onTexture, blast_furnaceStoneFront_onTexture, 0, 0, "block\\" + material + "_blast_furnace_front_on", "block", UpgradedVanilla.ID);
				File blast_furnaceFront_onOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front_on.png");
				TextureHelper.overlayTexture(blast_furnaceFront_onTexture, ImageIO.read(blast_furnaceFront_onOverlayLocation), 0, 0, "block\\" + material + "_blast_furnace_front_on", "block", UpgradedVanilla.ID);

				BufferedImage blast_furnaceFrontTexture = TextureHelper.swapColors("block\\" + material + "_blast_furnace_front", "block", UpgradedVanilla.ID, ImageIO.read(blast_furnaceFrontTextureLocation), TextureHelper.stonePresetPalette, out);
				File blast_furnaceFrontMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front_shade.png");
				BufferedImage blast_furnaceStoneFrontTexture = TextureHelper.maskImage(ImageIO.read(blast_furnaceFrontMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_blast_furnace_front", "block", UpgradedVanilla.ID);
				File blast_furnaceFrontShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front_shade.png");
				blast_furnaceStoneFrontTexture= TextureHelper.overlayTextureMultiply(blast_furnaceStoneFrontTexture, ImageIO.read(blast_furnaceFrontShadeLocation), 0, 0, "block\\" + material + "_blast_furnace_front", "block", UpgradedVanilla.ID);
				blast_furnaceFrontTexture = TextureHelper.overlayTexture(blast_furnaceFrontTexture, blast_furnaceStoneFrontTexture, 0, 0, "block\\" + material + "_blast_furnace_front", "block", UpgradedVanilla.ID);
				File blast_furnaceFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_front.png");
				TextureHelper.overlayTexture(blast_furnaceFrontTexture, ImageIO.read(blast_furnaceFrontOverlayLocation), 0, 0, "block\\" + material + "_blast_furnace_front", "block", UpgradedVanilla.ID);

				BufferedImage blast_furnaceSideTexture = TextureHelper.swapColors("block\\" + material + "_blast_furnace_side", "block", UpgradedVanilla.ID, ImageIO.read(blast_furnaceSideTextureLocation), TextureHelper.stonePresetPalette, out);
				File blast_furnaceSideMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_side_shade.png");
				BufferedImage blast_furnaceStoneSideTexture = TextureHelper.maskImage(ImageIO.read(blast_furnaceSideMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_blast_furnace_side", "block", UpgradedVanilla.ID);
				File blast_furnaceSideShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_side_shade.png");
				blast_furnaceStoneSideTexture= TextureHelper.overlayTextureMultiply(blast_furnaceStoneSideTexture, ImageIO.read(blast_furnaceSideShadeLocation), 0, 0, "block\\" + material + "_blast_furnace_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(blast_furnaceSideTexture, blast_furnaceStoneSideTexture, 0, 0, "block\\" + material + "_blast_furnace_side", "block", UpgradedVanilla.ID);

				BufferedImage blast_furnaceTopTexture = TextureHelper.swapColors("block\\" + material + "_blast_furnace_top", "block", UpgradedVanilla.ID, ImageIO.read(blast_furnaceTopTextureLocation), TextureHelper.stonePresetPalette, out);
				File blast_furnaceTopMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_top_shade.png");
				BufferedImage blast_furnaceStoneTopTexture = TextureHelper.maskImage(ImageIO.read(blast_furnaceTopMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_blast_furnace_top", "block", UpgradedVanilla.ID);
				File blast_furnaceTopShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\blast_furnace_top_shade.png");
				blast_furnaceStoneTopTexture= TextureHelper.overlayTextureMultiply(blast_furnaceStoneTopTexture, ImageIO.read(blast_furnaceTopShadeLocation), 0, 0, "block\\" + material + "_blast_furnace_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(blast_furnaceTopTexture, blast_furnaceStoneTopTexture, 0, 0, "block\\" + material + "_blast_furnace_top", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
	public static Block observerBlock(String material, Block stone, TagKey<Item> cobbledStoneBlocks, List<TagKey<Block>> blockTags,
										  List<TagKey<Item>> itemTags, File out, SoundType soundType) {
		Block returnBlock = BlockHandler.registerBlockWithRecipe(material + "_observer", StringUtil.capitalizeWord(material) + " Observer",
				UpgradedVanilla.ID, new ObserverBlock(FabricBlockSettings.copyOf(Blocks.OBSERVER).sounds(soundType)), CreativeModeTab.TAB_REDSTONE,
				(blockModelGenerators, block) -> {
					TextureMapping textureMappingOff = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_top"))
							.put(TextureSlot.BOTTOM,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_back"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_front"));
					ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.OBSERVER.create((Block) block, textureMappingOff, blockModelGenerators.modelOutput);
					TextureMapping textureMappingOn = new TextureMapping()
							.put(TextureSlot.FRONT,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_front"))
							.put(TextureSlot.SIDE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_side"))
							.put(TextureSlot.TOP,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_top"))
							.put(TextureSlot.BOTTOM,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_back_on"))
							.put(TextureSlot.PARTICLE,new ResourceLocation(UpgradedVanilla.ID, "block/"+material + "_observer_front"));
					ResourceLocation resourceLocationOn = UpgradedVanillaModelTemplates.OBSERVER_ON.create((Block) block, textureMappingOn, blockModelGenerators.modelOutput);
					blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
							.with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.POWERED, resourceLocationOn, resourceLocation)).with(BlockModelGenerators.createFacingDispatch()));
				},
				((finishedRecipeConsumer, item) -> {
					ShapedRecipeBuilder.shaped(item, 1).define('S', cobbledStoneBlocks).define('R',UVCommonItemTags.REDSTONE_DUSTS).define('Q',UVCommonItemTags.QUARTZ)
							.pattern("SSS")
							.pattern("RRQ")
							.pattern("SSS")
							.unlockedBy("has_" + material + "_cobbled_stone", FabricRecipeProvider.has(cobbledStoneBlocks))
							.save(finishedRecipeConsumer);
				}),
				BlockLoot::dropSelf,
				blockTags, itemTags);

		TextureHelper.addTexture(() -> {
			try {
				File stoneTexture = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Palletes\\"+ Registry.BLOCK.getKey(stone).getPath()+".png");
				File observerFrontTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\observer_front.png");
				File observerBackOnTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\observer_back_on.png");
				File observerBackTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\observer_back.png");
				File observerTopTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\observer_top.png");
				File observerSideTextureLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Textures\\block\\observer_side.png");
				BufferedImage observerBackOnTexture = TextureHelper.swapColors("block\\" + material + "_observer_back_on", "block", UpgradedVanilla.ID, ImageIO.read(observerBackOnTextureLocation), TextureHelper.stonePresetPalette, out);
				File observerBack_onMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\observer_back_on.png");
				BufferedImage observerStoneBack_onTexture = TextureHelper.maskImage(ImageIO.read(observerBack_onMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_observer_back_on", "block", UpgradedVanilla.ID);
				File observerBack_onShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_back_on_shade.png");
				observerStoneBack_onTexture= TextureHelper.overlayTextureDarken(observerStoneBack_onTexture, ImageIO.read(observerBack_onShadeLocation), 0, 0, "block\\" + material + "_observer_back_on", "block", UpgradedVanilla.ID);
				observerBackOnTexture = TextureHelper.overlayTexture(observerBackOnTexture, observerStoneBack_onTexture, 0, 0, "block\\" + material + "_observer_back_on", "block", UpgradedVanilla.ID);
				File observerBack_onOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_back_on.png");
				TextureHelper.overlayTexture(observerBackOnTexture, ImageIO.read(observerBack_onOverlayLocation), 0, 0, "block\\" + material + "_observer_back_on", "block", UpgradedVanilla.ID);

				BufferedImage observerFrontTexture = TextureHelper.swapColors("block\\" + material + "_observer_front", "block", UpgradedVanilla.ID, ImageIO.read(observerFrontTextureLocation), TextureHelper.stonePresetPalette, out);
				File observerFrontMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\observer_front.png");
				BufferedImage observerStoneFrontTexture = TextureHelper.maskImage(ImageIO.read(observerFrontMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_observer_front", "block", UpgradedVanilla.ID);
				File observerFrontShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_front_shade.png");
				observerStoneFrontTexture= TextureHelper.overlayTextureDarken(observerStoneFrontTexture, ImageIO.read(observerFrontShadeLocation), 0, 0, "block\\" + material + "_observer_front", "block", UpgradedVanilla.ID);
				observerFrontTexture = TextureHelper.overlayTexture(observerFrontTexture, observerStoneFrontTexture, 0, 0, "block\\" + material + "_observer_front", "block", UpgradedVanilla.ID);
				File observerFrontOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_front.png");
				TextureHelper.overlayTexture(observerFrontTexture, ImageIO.read(observerFrontOverlayLocation), 0, 0, "block\\" + material + "_observer_front", "block", UpgradedVanilla.ID);

				BufferedImage observerSideTexture = TextureHelper.swapColors("block\\" + material + "_observer_side", "block", UpgradedVanilla.ID, ImageIO.read(observerSideTextureLocation), TextureHelper.stonePresetPalette, out);
				File observerSideMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\observer_side.png");
				BufferedImage observerStoneSideTexture = TextureHelper.maskImage(ImageIO.read(observerSideMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_observer_side", "block", UpgradedVanilla.ID);
				File observerSideShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_side_shade.png");
				observerStoneSideTexture= TextureHelper.overlayTextureDarken(observerStoneSideTexture, ImageIO.read(observerSideShadeLocation), 0, 0, "block\\" + material + "_observer_side", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(observerSideTexture, observerStoneSideTexture, 0, 0, "block\\" + material + "_observer_side", "block", UpgradedVanilla.ID);

				BufferedImage observerBackTexture = TextureHelper.swapColors("block\\" + material + "_observer_back", "block", UpgradedVanilla.ID, ImageIO.read(observerBackTextureLocation), TextureHelper.stonePresetPalette, out);
				File observerBackMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\observer_back.png");
				BufferedImage observerStoneBackTexture = TextureHelper.maskImage(ImageIO.read(observerBackMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_observer_back", "block", UpgradedVanilla.ID);
				File observerBackShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_back_shade.png");
				observerStoneBackTexture= TextureHelper.overlayTextureDarken(observerStoneBackTexture, ImageIO.read(observerBackShadeLocation), 0, 0, "block\\" + material + "_observer_back", "block", UpgradedVanilla.ID);
				observerBackTexture = TextureHelper.overlayTexture(observerBackTexture, observerStoneBackTexture, 0, 0, "block\\" + material + "_observer_back", "block", UpgradedVanilla.ID);
				File observerBackOverlayLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_back.png");
				TextureHelper.overlayTexture(observerBackTexture, ImageIO.read(observerBackOverlayLocation), 0, 0, "block\\" + material + "_observer_back", "block", UpgradedVanilla.ID);

				BufferedImage observerTopTexture = TextureHelper.swapColors("block\\" + material + "_observer_top", "block", UpgradedVanilla.ID, ImageIO.read(observerTopTextureLocation), TextureHelper.stonePresetPalette, out);
				File observerTopMaskLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Masks\\observer_top.png");
				BufferedImage observerStoneTopTexture = TextureHelper.maskImage(ImageIO.read(observerTopMaskLocation), ImageIO.read(stoneTexture), 0, 0, "block\\" + material + "_observer_top", "block", UpgradedVanilla.ID);
				File observerTopShadeLocation = new File("C:\\Users\\stijn\\Desktop\\Upgraded-Vanilla-Companion-Mod\\src\\main\\resources\\templatedata\\Stone\\Overlays\\block\\observer_top_shade.png");
				observerStoneTopTexture= TextureHelper.overlayTextureDarken(observerStoneTopTexture, ImageIO.read(observerTopShadeLocation), 0, 0, "block\\" + material + "_observer_top", "block", UpgradedVanilla.ID);
				TextureHelper.overlayTexture(observerTopTexture, observerStoneTopTexture, 0, 0, "block\\" + material + "_observer_top", "block", UpgradedVanilla.ID);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return returnBlock;
	}
}
