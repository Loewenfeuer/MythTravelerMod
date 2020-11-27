package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders.OLDEgyptHillSurfaceBuilder;
import loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders.OldEgyptSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MTSurfaceBuilders
{
	// Biomes are registered before surface builders and need the raw objects. So
	// don't use DeferredRegister here.
	public static final SurfaceBuilder<SurfaceBuilderConfig> OLD_EGYPT_DUNE = new OldEgyptSurfaceBuilder(
			SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> OLD_EGYPT_DUNE_HILL = new OLDEgyptHillSurfaceBuilder(
			SurfaceBuilderConfig::deserialize);

	@SubscribeEvent
	public static void register(RegistryEvent.Register<SurfaceBuilder<?>> evt)
	{
		evt.getRegistry().register(OLD_EGYPT_DUNE.setRegistryName(MythTravalerMain.MOD_ID, "old_egypt_dune"));
		evt.getRegistry().register(OLD_EGYPT_DUNE_HILL.setRegistryName(MythTravalerMain.MOD_ID, "old_egypt_dune_hill"));
	}
}