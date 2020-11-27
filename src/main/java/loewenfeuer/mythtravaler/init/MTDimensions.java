package loewenfeuer.mythtravaler.init;

import io.netty.buffer.Unpooled;
import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.OldEgyptDimension;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTBiomeProvider;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTBiomeProviderSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTGenerationSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptBiomeProvider;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptChunkGenerator;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptGenSettings;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.OldEgyptModDimension;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MTDimensions
{
	public static final DeferredRegister<BiomeProviderType<?, ?>> OLD_EGYPT_BIOME_PROVIDER_TYPES = new DeferredRegister<>(ForgeRegistries.BIOME_PROVIDER_TYPES, MythTravalerMain.MOD_ID);
	public static final DeferredRegister<ChunkGeneratorType<?, ?>> OLD_EGYPT_CHUNK_GENERATOR_TYPES = new DeferredRegister<>(ForgeRegistries.CHUNK_GENERATOR_TYPES, MythTravalerMain.MOD_ID);
	public static final DeferredRegister<ModDimension> MOD_DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, MythTravalerMain.MOD_ID);
	
	
	public static final RegistryObject<BiomeProviderType<MTBiomeProviderSettings, MTBiomeProvider>> OLD_EGYPT_BIOME_PROVIDER = OLD_EGYPT_BIOME_PROVIDER_TYPES.register("old_egypt_biome_provider", () -> new BiomeProviderType<>(OldEgyptBiomeProvider::new, MTBiomeProviderSettings::new));

	public static final RegistryObject<ChunkGeneratorType<MTGenerationSettings, OldEgyptChunkGenerator>> OLD_EGYPT_CHUNK_GEN = OLD_EGYPT_CHUNK_GENERATOR_TYPES.register("old_egypt_chunk_gen", () -> new ChunkGeneratorType<>(OldEgyptChunkGenerator::new, true, OldEgyptGenSettings::new));
	
	
	//ModDimensions
	private static final RegistryObject<ModDimension> OLD_EGYPT_DIMENSION = MOD_DIMENSIONS.register("old_egypt", () -> ModDimension.withFactory(OldEgyptDimension::new));
	
	
	public static DimensionType nordicDimension;
	public static DimensionType oldEgyptDimension;
	
	//ModDimensions
	@SubscribeEvent
	public static void registerModDimension(final RegisterDimensionsEvent e)
	{
		ResourceLocation oldEgyptRL = new ResourceLocation(MythTravalerMain.MOD_ID, "oldegypt_dimension");
		oldEgyptDimension = DimensionManager.registerOrGetDimension(oldEgyptRL, OLD_EGYPT_DIMENSION.get(), new PacketBuffer(Unpooled.buffer()), true);
		DimensionManager.keepLoaded(oldEgyptDimension, false);
	}
	
	@SubscribeEvent
	public static void onDimensionRegistryEvent(RegistryEvent.Register<ModDimension> event)
	{
		event.getRegistry().register(new OldEgyptModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":old_egypt_mod_dimension"));
	}
}
