package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.biomes.DeadDesertBiome;
import loewenfeuer.mythtravaler.world.biomes.DeadDesertHillsBiome;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModBiomes
{
	public static final Biome DEAD_DESERT = new DeadDesertBiome();
	public static final Biome DEAD_DESERT_HILLS = new DeadDesertHillsBiome();
	
	@SubscribeEvent
	public static void register(Register<Biome> event)
	{
		final IForgeRegistry<Biome> registry = event.getRegistry();
		
		registry.register(DEAD_DESERT.setRegistryName(location("dead_desert")));
		registry.register(DEAD_DESERT_HILLS.setRegistryName(location("dead_desert_hills")));
		
		registerBiomes();
	}
	
	private static void registerBiomes()
	{
		registerBiome(DEAD_DESERT, Type.HOT, Type.DRY, Type.DEAD, Type.SANDY);
		registerBiome(DEAD_DESERT_HILLS, Type.HOT, Type.DRY, Type.DEAD, Type.HILLS, Type.SANDY);
	}
	
	private static void registerBiome(Biome biome, Type... types)
	{
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
	
	private static ResourceLocation location (String name)
	{
		return new ResourceLocation(MythTravalerMain.MOD_ID, name);
	}
}
