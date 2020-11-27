package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.objects.items.BasicItem;
import loewenfeuer.mythtravaler.objects.items.ReligionBookItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MythTraModItems
{
	public static final Item OLD_EGYPT_BOOK = new ReligionBookItem();			//new OldEgyptReligionEffect());
	public static final Item NORDIC_BOOK = new ReligionBookItem();				//new NordicReligionEffect());
	public static final Item OLYMPIC_BOOK = new ReligionBookItem();				//new OlympicReligionEffect());
	
	//Test items
	public static final Item TEST_ITEM = new BasicItem();
	
	@SubscribeEvent
	public static void register(Register<Item> event)
	{
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		registry.register(NORDIC_BOOK.setRegistryName(MythTravalerMain.MOD_ID, "nordic_book"));
		registry.register(OLD_EGYPT_BOOK.setRegistryName(MythTravalerMain.MOD_ID, "old_egypt_book"));
		registry.register(OLYMPIC_BOOK.setRegistryName(MythTravalerMain.MOD_ID, "olympic_book"));
		
		registry.register(TEST_ITEM.setRegistryName(MythTravalerMain.MOD_ID, "test_item"));
	}
}
