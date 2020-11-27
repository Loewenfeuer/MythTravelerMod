package loewenfeuer.mythtravaler.world.dimensions.generation;

import net.minecraft.block.Blocks;

public class OldEgyptGenSettings extends MTGenerationSettings
{	
	public static OldEgyptGenSettings createDefault()
	{
		OldEgyptGenSettings config = new OldEgyptGenSettings();
		config.setDefaultBlock(Blocks.DIAMOND_BLOCK.getDefaultState());
		config.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return config;
	}
}
