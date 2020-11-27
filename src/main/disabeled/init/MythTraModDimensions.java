package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.NordicModDimension;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.OldEgyptModDimension;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModDimensions
{
	//BiomeProviderTypes
	
	
	// ModDimensions
	
	@ObjectHolder(MythTravalerMain.MOD_ID + ":nordic_dimension")
	public static final ModDimension NORDIC_MOD_DIMENSION = null;
	
	@ObjectHolder(MythTravalerMain.MOD_ID + ":oldegypt_dimension")
	public static final ModDimension OLDEGYPT_MOD_DIMENSION = null;
	
	
	@SubscribeEvent()
	public static void onDimensionRegistryEvent(RegistryEvent.Register<ModDimension> event)
	{
		event.getRegistry().register(new NordicModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":nordic_dimension"));
		event.getRegistry().register(new OldEgyptModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":oldegypt_dimension"));
	}
}
