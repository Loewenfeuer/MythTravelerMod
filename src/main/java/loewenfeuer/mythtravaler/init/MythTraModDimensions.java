package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.world.dimensions.modDimensions.OldEgyptModDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModDimensions
{
	public static final ResourceLocation OLD_EGYPT_DIMENSION_RL = new ResourceLocation(MythTravalerMain.MOD_ID,
			"old_egypt_dimension");

	@ObjectHolder(MythTravalerMain.MOD_ID + ":old_egypt_dimension")
	public static final ModDimension OLD_EGYPT_MOD_DIMENSION = null;

	public static DimensionType OLD_EGYPT_DIMENSION_TYPE;

	@SubscribeEvent
	public static void register(Register<ModDimension> event)
	{
		event.getRegistry()
				.register(new OldEgyptModDimension().setRegistryName(MythTravalerMain.MOD_ID + ":old_egypt_dimension"));
	}
}
