package loewenfeuer.mythtravaler.handler;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.init.MythTraModDimensions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.FORGE)
public class CommonForgeEventHandler
{
  public static final ResourceLocation NORDIC_DIMENSION_TYPE_RL = new ResourceLocation(MythTravalerMain.MOD_ID, "nordic_dimension");
  public static final ResourceLocation OLDEGYPT_DIMENSION_TYPE_RL = new ResourceLocation(MythTravalerMain.MOD_ID, "old_egypt_dimension");
  
	@SubscribeEvent()
	public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event)
	{
		// the first argument here is a resource location for your dimension type
		// the second argument here is the ModDimension that your DimensionType uses
		// the third argument here is an optional PacketBuffer with extra data you want to pass
		// to your DimensionType, which is in turn passed to your Dimension
		// which allows you to define properties of your Dimension when you register this
    	// the fourth argument determines skylight for some reason
		// we'll also need to add a check to prevent the dimension from being registered more than once
//		if (DimensionType.byName(NORDIC_DIMENSION_TYPE_RL) == null && !DimensionManager.getRegistry().containsKey(new ResourceLocation(MythTravalerMain.MOD_ID,"nordic_dimension")))
//		{
//			DimensionManager.registerDimension(NORDIC_DIMENSION_TYPE_RL, MythTraModDimensions.NORDIC_MOD_DIMENSION, null, true);
//		}
		
		MythTraModDimensions.OLD_EGYPT_DIMENSION_TYPE = DimensionManager.registerOrGetDimension(OLDEGYPT_DIMENSION_TYPE_RL, MythTraModDimensions.OLD_EGYPT_MOD_DIMENSION, null, true);
		
    // this returns a DimensionType for your ResourceLocation; alternatively you can also retrieve the dimensiontype with
    // DimensionType.byName(ResourceLocation)
	}
}
