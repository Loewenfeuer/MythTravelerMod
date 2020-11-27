package loewenfeuer.mythtravaler.init;

import java.util.function.Supplier;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTGenerationSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptChunkGenerator;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptGenSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.IChunkGeneratorFactory;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModChunkGeneratorTypes<C extends GenerationSettings, T extends ChunkGenerator<C>>
		extends net.minecraftforge.registries.ForgeRegistryEntry<ChunkGeneratorType<?, ?>>
		implements IChunkGeneratorFactory<C, T>
{

	public static final ChunkGeneratorType<MTGenerationSettings, OldEgyptChunkGenerator> OLD_EGYPT_SURFACE = register(
			"old_egypt_surface", OldEgyptChunkGenerator::new, OldEgyptGenSettings::new, true);
	private final IChunkGeneratorFactory<C, T> factory;
	private final boolean isOptionForBuffetWorld;
	private final Supplier<C> settings;

	@SuppressWarnings("deprecation")
	private static <C extends GenerationSettings, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> register(
			String key, IChunkGeneratorFactory<C, T> factoryIn, Supplier<C> settingsIn, boolean canUseForBuffet)
	{
		return Registry.register(Registry.CHUNK_GENERATOR_TYPE, location(key),
				new ChunkGeneratorType<>(factoryIn, canUseForBuffet, settingsIn));
	}

	public MythTraModChunkGeneratorTypes(IChunkGeneratorFactory<C, T> p_i49953_1_, boolean p_i49953_2_,
			Supplier<C> p_i49953_3_)
	{
		this.factory = p_i49953_1_;
		this.isOptionForBuffetWorld = p_i49953_2_;
		this.settings = p_i49953_3_;
	}

	@Override
	public T create(World p_create_1_, BiomeProvider p_create_2_, C p_create_3_)
	{
		return this.factory.create(p_create_1_, p_create_2_, p_create_3_);
	}

	public C createSettings()
	{
		return (C) (this.settings.get());
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isOptionForBuffetWorld()
	{
		return this.isOptionForBuffetWorld;
	}

	private static ResourceLocation location(String name)
	{
		return new ResourceLocation(MythTravalerMain.MOD_ID, name);
	}

}
