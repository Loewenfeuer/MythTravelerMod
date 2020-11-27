package loewenfeuer.mythtravaler;

import loewenfeuer.mythtravaler.init.MTBiomes;
import loewenfeuer.mythtravaler.init.MTDimensions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MythTravalerMain.MOD_ID)
public class MythTravalerMain
{
	public static final String MOD_ID = "mythtramod";
	
	public MythTravalerMain()
	{
		IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
		
		MythTravalerMain.register(modbus);
	}
	
	
	private static void register(IEventBus modbus)
	{
		//Biomes
		MTBiomes.OLD_EGYPT_BIOMES.register(modbus);
		
		//BiomeProviderTypes
		MTDimensions.OLD_EGYPT_BIOME_PROVIDER_TYPES.register(modbus);
		
		//ChunkGeneratorTypes
		MTDimensions.OLD_EGYPT_CHUNK_GENERATOR_TYPES.register(modbus);
		
	}
	
}