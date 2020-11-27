package loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class OLDEgyptHillSurfaceBuilder extends OldEgyptSurfaceBuilder
{

	public OLDEgyptHillSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config)
	{
		super(config);
	}

	@Override
	public void buildSurface(Random rand, IChunk primer, Biome biome, int x, int z, int startheight, double noiseVal,
			BlockState defaultBlock, BlockState defaultFluid, int sealevel, long seed, SurfaceBuilderConfig config)
	{
		BlockState topBlock = config.getTop();
		BlockState fillerBlock = config.getUnder();

		if (noiseVal > 1.75D)
		{
			topBlock = MythTraModBlocks.DEAD_SANDSTONE_BLOCK.getDefaultState();
		} else if (noiseVal > -0.95D)
		{
			topBlock = MythTraModBlocks.DEAD_SAND_BLOCK.getDefaultState();
		}

		this.genBiomeTerrain(rand, primer, biome, x, z, startheight, noiseVal, defaultBlock, defaultFluid, topBlock,
				fillerBlock, config.getUnderWaterMaterial(), sealevel);
	}

}
