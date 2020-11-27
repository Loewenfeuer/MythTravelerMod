package loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class OldEgyptSurfaceBuilder extends MTDefaultSurfaceBuilder
{
	public OldEgyptSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config)
	{
		super(config);
	}

	@Override
	public void buildSurface(Random rand, IChunk primer, Biome biome, int x, int z, int startheight, double noiseVal,
			BlockState defaultBlock, BlockState defaultFluid, int sealevel, long seed, SurfaceBuilderConfig config)
	{
		this.genBiomeTerrain(rand, primer, biome, x, z, startheight, noiseVal, defaultBlock, defaultFluid,
				config.getTop(), config.getUnder(), config.getUnderWaterMaterial(), sealevel);
	}

	@Override
	protected void genBiomeTerrain(Random rand, IChunk primer, Biome biome, int x, int z, int startHeight,
			double noiseVal, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle,
			BlockState bottom, int sealevel)
	{
		int i = 30; // !!! Hardcoded to 30 !!!
		BlockState iblockstate = top;
		BlockState iblockstate1 = middle;
		int j = -1;
		int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int l = x & 15;
		int i1 = z & 15;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = (MutableBlockPos) new BlockPos(1, 1, 1);
		boolean generateBedrock = /* shouldGenerateBedrock(world) */ true; // TF - conditional bedrock gen //TODO 1.15:
																			// World is not a valid argument. Defaulting
																			// to true

		for (int j1 = startHeight; j1 >= 0; --j1)
		{ // Author's note: beginning value was 255. It is now startHeight
			blockpos$mutableblockpos.setPos(i1, j1, l);
			// TF - conditional bedrock gen
			if (generateBedrock && j1 <= rand.nextInt(5))
			{
				primer.setBlockState(blockpos$mutableblockpos, Blocks.BEDROCK.getDefaultState(), false);
			} else
			{
				BlockState iblockstate2 = primer.getBlockState(blockpos$mutableblockpos);

				// TF - use block check for air
				if (iblockstate2.getBlock() == Blocks.AIR)
				{
					// j = -1; TF - commented out? todo 1.9
				} else if (iblockstate2.getBlock() == MythTraModBlocks.DEAD_SANDSTONE_BLOCK)
				{
					if (j == -1)
					{
						if (k <= 0)
						{
							iblockstate = AIR;
							iblockstate1 = defaultBlock;
						} else if (j1 >= sealevel - 4 && j1 <= sealevel + 1)
						{
							iblockstate = top;
							iblockstate1 = middle;
						}

						// TF - use block check for air
						if (j1 < sealevel && (iblockstate == null || iblockstate.getBlock() == Blocks.AIR))
						{
							iblockstate = defaultFluid;
							blockpos$mutableblockpos.setPos(i1, j1, l);
						}

						j = k;

						if (j1 >= sealevel - 1)
						{
							primer.setBlockState(blockpos$mutableblockpos, iblockstate, false);
						} else if (j1 < sealevel - 7 - k)
						{
							iblockstate = AIR;
							iblockstate1 = defaultBlock;
							primer.setBlockState(blockpos$mutableblockpos, bottom, false);
						} else
						{
							primer.setBlockState(blockpos$mutableblockpos, iblockstate1, false);
						}
					} else if (j > 0)
					{
						--j;
						primer.setBlockState(blockpos$mutableblockpos, iblockstate1, false);

						if (j == 0 && iblockstate1.getBlock() == Blocks.SAND)
						{
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = iblockstate1 == RED_SAND ? Blocks.RED_SANDSTONE.getDefaultState()
									: Blocks.SANDSTONE.getDefaultState();
						}
					}
				}
			}
		}
	}

}
