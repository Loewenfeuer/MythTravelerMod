package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.objects.blocks.DeadSandstoneOre;
//import loewenfeuer.mythtravaler.objects.blocks.WorldsPortalBlock;
//import loewenfeuer.mythtravaler.objects.items.BasicItem;
import loewenfeuer.mythtravaler.objects.items.RockBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModBlocks
{
	//Stone
	public static final Block DEAD_SAND_BLOCK = new SandBlock(27035050, Block.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND));
	public static final Block DEAD_SANDSTONE_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(0.8F));
	public static final Block DEAD_CHISELED_SANDSTONE_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(0.8F));
	public static final Block DEAD_CUT_SANDSTONE_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(0.8F));
	public static final Block DEAD_SANDSTONE_SLAB = new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F));
	//public static final Block DEAD_SANDSTONE_STAIRS = new StairsBlock(DEAD_SANDSTONE_BLOCK.getDefaultState(), Block.Properties.from(DEAD_SANDSTONE_BLOCK));
	public static final Block DEAD_SANDSTONE_WALL = new WallBlock(Block.Properties.from(DEAD_SANDSTONE_BLOCK));
	public static final Block DEAD_CUT_SANDSTONE_SLAB = new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F));
	//public static final Block DEAD_CUT_SANDSTONE_STAIRS = new StairsBlock(DEAD_SANDSTONE_BLOCK.getDefaultState(), Block.Properties.from(DEAD_CUT_SANDSTONE_BLOCK));
	public static final Block DEAD_CUT_SANDSTONE_WALL = new WallBlock(Block.Properties.from(DEAD_CUT_SANDSTONE_BLOCK));
	
	//Ore
	public static final Block DEAD_SANDSTONE_GOLD_ORE = new DeadSandstoneOre();
	public static final Block DEAD_SANDSTONE_LAPIS_ORE = new DeadSandstoneOre();
	
	//Portals
//	public static final Block WORLDS_PORTAL = new WorldsPortalBlock();
	
	
	@SubscribeEvent
	public static void register(Register<Block> event)
	{
		final IForgeRegistry<Block> registry = event.getRegistry();
		
		//Stone
		registry.register(DEAD_SAND_BLOCK.setRegistryName(location("dead_sand_block")));
		registry.register(DEAD_SANDSTONE_BLOCK.setRegistryName(location("dead_sandstone_block")));
		registry.register(DEAD_CHISELED_SANDSTONE_BLOCK.setRegistryName(location("dead_chiseled_sandstone_block")));
		registry.register(DEAD_CUT_SANDSTONE_BLOCK.setRegistryName(location("dead_cut_sandstone_block")));
		registry.register(DEAD_SANDSTONE_SLAB.setRegistryName(location("dead_sandstone_slab")));
		registry.register(DEAD_SANDSTONE_WALL.setRegistryName(location("dead_sandstone_wall")));
		registry.register(DEAD_CUT_SANDSTONE_SLAB.setRegistryName(location("dead_cut_sandstone_slab")));
		registry.register(DEAD_CUT_SANDSTONE_WALL.setRegistryName(location("dead_cut_sandstone_wall")));
		
		//Ore
		registry.register(DEAD_SANDSTONE_GOLD_ORE.setRegistryName(location("dead_sandstone_gold_ore")));
		registry.register(DEAD_SANDSTONE_LAPIS_ORE.setRegistryName(location("dead_sandstone_lapis_ore")));
		
		//Portals
//		registry.register(WORLDS_PORTAL.setRegistryName(location("worlds_portal")));
	}
	
	@SubscribeEvent
	public static void registerBlockItem(Register<Item> event)
	{
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(new RockBlockItem(DEAD_SAND_BLOCK));
		registry.register(new RockBlockItem(DEAD_SANDSTONE_BLOCK));
		registry.register(new RockBlockItem(DEAD_CHISELED_SANDSTONE_BLOCK));
		registry.register(new RockBlockItem(DEAD_CUT_SANDSTONE_BLOCK));
		registry.register(new RockBlockItem(DEAD_SANDSTONE_SLAB));
		registry.register(new RockBlockItem(DEAD_SANDSTONE_WALL));
		registry.register(new RockBlockItem(DEAD_CUT_SANDSTONE_SLAB));
		registry.register(new RockBlockItem(DEAD_CUT_SANDSTONE_WALL));
		registry.register(new RockBlockItem(DEAD_SANDSTONE_GOLD_ORE));
		registry.register(new RockBlockItem(DEAD_SANDSTONE_LAPIS_ORE));
		
//		registry.register(new RockBlockItem(WORLDS_PORTAL));
	}
	
	private static ResourceLocation location (String name)
	{
		return new ResourceLocation(MythTravalerMain.MOD_ID, name);
	}
}