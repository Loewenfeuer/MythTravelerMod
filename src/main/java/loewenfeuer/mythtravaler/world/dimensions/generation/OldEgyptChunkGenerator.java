package loewenfeuer.mythtravaler.world.dimensions.generation;

import java.util.Random;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OctavesNoiseGenerator;

public class OldEgyptChunkGenerator extends MTChunkGeneratorBase
{
	private final OctavesNoiseGenerator depthNoise;
	private final BiomeProvider biomeProvider;
	private final Random ran;
	
	
	public OldEgyptChunkGenerator(IWorld world, BiomeProvider provider, MTGenerationSettings settings)
	{
		super(world, (MTBiomeProvider) provider, settings);
		this.biomeProvider = provider;
		this.randomSeed.skip(42);
		this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 9);
		this.ran = new Random();
	}
	
	//###########################################################################################################
	
	@Override
	public void generateSurface(IChunk chunk)
	{
		BlockState bedrock = Blocks.BEDROCK.getDefaultState();
		BlockState stone = MythTraModBlocks.DEAD_SANDSTONE_BLOCK.getDefaultState();
		ChunkPos chunkpos = chunk.getPos();

		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		int x;
		int z;

		for (x = 0; x < 16; x++)
		{
			for (z = 0; z < 16; z++)
			{
				chunk.setBlockState(pos.setPos(x, 0, z), bedrock, false);
			}
		} 

		for (x = 0; x < 16; x++)
		{
			for (z = 0; z < 16; z++)
			{
				
				int realx = chunkpos.x * 16 + x;
				int realz = chunkpos.z * 16 + z;
				//int height = (int) ((50 + ((Math.sin(realx / 40.0f) * 3 + Math.cos(realz / 40.0f) * 3) + 64) * mult - 64 * mult));
				int height = surfaceFunction(realx, realz);
				for (int y = 1; y < height; y++)
				{
					chunk.setBlockState(pos.setPos(x, y, z), stone, false);
				}
				
			}
		}

	}
	
	//makes it easier to manipulate each parameter
	private int surfaceFunction(int realx, int realz)
	{
		int y = 50;
		
		double sinX = Math.sin(realx / 40.0f) * 4;
		double cosZ = Math.cos(realz / 40.0f) * 4;
		
		double vec = Math.sqrt(Math.pow(realx, 2) + Math.pow(realz, 2));
		double mult =(3 * (Math.log(vec + 1)/ Math.log(100)) + 1/(vec + 1));
		//double mult =(Math.log10(vec + 1) + 1/(vec + 1));
		
		int yOffs = (int) ((sinX + cosZ) * mult);
		if(yOffs <= 0) return y;
		else return y + yOffs;
	}

	@Override
	public void makeBase(IWorld worldIn, IChunk chunkIn)
	{
		
	}

	@Override
	public int getGroundHeight()
	{
		return world.getSeaLevel() + 1;
	}

}