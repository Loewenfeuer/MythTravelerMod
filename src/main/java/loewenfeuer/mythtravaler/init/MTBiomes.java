package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.biomes.DeadDesertBiome;
import loewenfeuer.mythtravaler.world.biomes.DeadDesertHillsBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MTBiomes
{
	public static final DeferredRegister<Biome> OLD_EGYPT_BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			MythTravalerMain.MOD_ID);

	public static final RegistryObject<Biome> deadDes = OLD_EGYPT_BIOMES.register("dead_desert", () -> new DeadDesertBiome());
	public static final RegistryObject<Biome> deadDesHills = OLD_EGYPT_BIOMES.register("dead_desert_hills",
			() -> new DeadDesertHillsBiome());

	public static final Biome DEAD_DESERT = new DeadDesertBiome();
	public static final Biome DEAD_DESERT_HILLS = new DeadDesertHillsBiome();

	public static final BiomeDictionary.Type OLD_EGYPT = BiomeDictionary.Type.getType("OLD_EGYPT");

	public static void addBiomeTypes()
	{
		BiomeDictionary.addTypes(deadDes.get(), OLD_EGYPT, BiomeDictionary.Type.SANDY);
		BiomeDictionary.addTypes(deadDesHills.get(), OLD_EGYPT, BiomeDictionary.Type.SANDY);
	}
	
}