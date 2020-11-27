package loewenfeuer.mythtravaler.init;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import loewenfeuer.mythtravaler.MythTravalerMain;
import loewenfeuer.mythtravaler.commands.CommandTpDim;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@EventBusSubscriber(modid = MythTravalerMain.MOD_ID, bus = Bus.MOD)
public class MTCommands
{
	public static void register(CommandDispatcher<CommandSource> dispatcher)
	{
		LiteralCommandNode<CommandSource> cmdTut = dispatcher
				.register(Commands.literal(MythTravalerMain.MOD_ID).then(CommandTpDim.register(dispatcher)));

		dispatcher.register(Commands.literal("mt").redirect(cmdTut));
	}

	@SubscribeEvent
	public void serverLoad(FMLServerStartingEvent event)
	{
		register(event.getCommandDispatcher());
	}
}
