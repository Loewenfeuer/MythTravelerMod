package loewenfeuer.mythtravaler.init;

import java.util.function.Function;
import java.util.function.Supplier;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTBiomeProvider;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTBiomeProviderSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTGenerationSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptBiomeProvider;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptChunkGenerator;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptGenSettings;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.NordicModDimension;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.OldEgyptModDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.IChunkGeneratorFactory;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MTDimensions
{
	//BiomeProviderType
	public static final MTBiomeProviderType<MTBiomeProviderSettings, OldEgyptBiomeProvider> OLD_EGYPT_LAYERED = register("old_egypt_layered", OldEgyptBiomeProvider::new, MTBiomeProviderSettings::new);

	//ChunkGenTypes
	public static final MTChunkGeneratorType<MTGenerationSettings, OldEgyptChunkGenerator> OLD_EGYPT_SURFACE = register("old_egypt_surface", OldEgyptChunkGenerator::new, OldEgyptGenSettings::new, true);
	
	//ModDimensions
	@ObjectHolder(MythTravalerMain.MOD_ID + ":nordic_dimension")
	public static final ModDimension NORDIC_MOD_DIMENSION = null;
		
	@ObjectHolder(MythTravalerMain.MOD_ID + ":oldegypt_dimension")
	public static final ModDimension OLDEGYPT_MOD_DIMENSION = null;	
	
	
	//BiomeProvider
	@SuppressWarnings("static-access")
	public final BiomeProviderType<MTBiomeProviderSettings, OldEgyptBiomeProvider> OLD_EGYPT_BIOME_PROVIDER = OLD_EGYPT_LAYERED.register("old_egypt__biome_provider", OldEgyptBiomeProvider::new, MTBiomeProviderSettings::new);
	
	//ChunkGenerators
	@SuppressWarnings("static-access")
	public static final ChunkGeneratorType<MTGenerationSettings, OldEgyptChunkGenerator> OLD_EGYPT_CHUNK_GEN = OLD_EGYPT_SURFACE.register("old_egypt_chunk_gen", OldEgyptChunkGenerator::new, MTGenerationSettings::new, false);
	
	
	public static DimensionType nordicDimension;
	public static DimensionType oldEgyptDimension;
	
	public static ResourceLocation nordicDimensionRL;
	
	//ModDimensions
	@SubscribeEvent()
	public static void onDimensionRegistryEvent(RegistryEvent.Register<ModDimension> event)
	{
		event.getRegistry().register(new NordicModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":nordic_dimension"));
		event.getRegistry().register(new OldEgyptModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":oldegypt_dimension"));
		
		//nordicDimension = DimensionManager.registerOrGetDimension(new ResourceLocation(MythTravalerMain.MOD_ID + ":nordic_dimension"), type, data, hasSkyLight);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//BiomeProviderType
	private static <C extends IBiomeProviderSettings, T extends MTBiomeProvider> MTBiomeProviderType<C, T> register(String key, Function<C, T> functionIn, Supplier<C> settingsIn)
	{
		return (MTBiomeProviderType<C, T>) Registry.register(Registry.BIOME_SOURCE_TYPE, key, new BiomeProviderType<>(functionIn, settingsIn));
	}
	
	
	//ChunkGenTypes
	@SuppressWarnings("deprecation")
	private static <C extends GenerationSettings, T extends ChunkGenerator<C>> MTChunkGeneratorType<C, T> register(String key, IChunkGeneratorFactory<C, T> factoryIn, Supplier<C> settingsIn, boolean canUseForBuffet)
	{
		return (MTChunkGeneratorType<C, T>) Registry.register(Registry.CHUNK_GENERATOR_TYPE, location(key), new ChunkGeneratorType<>(factoryIn, canUseForBuffet, settingsIn));
	}
	
	private static ResourceLocation location(String name)
	{
		return new ResourceLocation(MythTravalerMain.MOD_ID, name);
	}
	
	
	
	public static class MTBiomeProviderType <C extends IBiomeProviderSettings, T extends BiomeProvider> extends BiomeProviderType <C, T >
	{
		protected static <C extends IBiomeProviderSettings, T extends MTBiomeProvider> BiomeProviderType<C, T> register(String key, Function<C, T> functionIn, Supplier<C> settingsIn)
		{
			return Registry.register(Registry.BIOME_SOURCE_TYPE, key, new BiomeProviderType<>(functionIn, settingsIn));
		}

		public MTBiomeProviderType(Function p_i50002_1_, Supplier p_i50002_2_)
		{
			super(p_i50002_1_, p_i50002_2_);
		}

	}
	
	
	public static class MTChunkGeneratorType<C extends GenerationSettings, T extends ChunkGenerator<C>> extends ChunkGeneratorType<C, T> implements IChunkGeneratorFactory<C, T>
	{

		@SuppressWarnings("deprecation")
		protected static <C extends GenerationSettings, T extends ChunkGenerator<C>> ChunkGeneratorType<C, T> register(String key, IChunkGeneratorFactory<C, T> factoryIn, Supplier<C> settingsIn, boolean canUseForBuffet)
		{
			return Registry.register(Registry.CHUNK_GENERATOR_TYPE, location(key), new ChunkGeneratorType<>(factoryIn, canUseForBuffet, settingsIn));
		}

		public MTChunkGeneratorType(IChunkGeneratorFactory<C, T> p_i49953_1_, boolean p_i49953_2_, Supplier<C> p_i49953_3_)
		{
			
			super(p_i49953_1_, p_i49953_2_, p_i49953_3_);
		}
	}
}
