package loewenfeuer.mythtravaler.world.dimensions.generation;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.server.ServerWorld;

public class MTGenerationSettings extends OverworldGenSettings
{
	
	@Nullable
	public static MTChunkGeneratorBase getChunkGenerator(World world)
	{
		if (world instanceof ServerWorld)
		{
			@SuppressWarnings("resource")
			ChunkGenerator<?> chunkGenerator = ((ServerWorld) world).getChunkProvider().generator;
			return chunkGenerator instanceof MTChunkGeneratorBase ? (MTChunkGeneratorBase) chunkGenerator : null;
		}
		return null;
	}

	public static MTGenerationSettings createDefault()
	{
		MTGenerationSettings config = new MTGenerationSettings();
		config.setDefaultBlock(Blocks.DIAMOND_BLOCK.getDefaultState());
		config.setDefaultFluid(Blocks.LAVA.getDefaultState());
		return config;
	}

	public int getBiomeSize()
	{
		return 5;
	}

	public int getRiverSize()
	{
		return 8;
	}

	public int getBiomeId()
	{
		return -1;
	}

	@Override
	public int getBedrockFloorHeight()
	{
		return 0;
	}

//	public OldEgyptGenSettings setWorldInfo(WorldInfo worldInfo)
//	{
//		return null;
//	}
	
	@Override
	public BlockState getDefaultFluid()
	{
		return Blocks.LAVA.getDefaultState();
	}
}
