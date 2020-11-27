package loewenfeuer.mythtravaler.world.dimensions;

import loewenfeuer.mythtravaler.world.dimensions.generation.MTBiomeProviderSettings;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptBiomeProvider;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptChunkGenerator;
import loewenfeuer.mythtravaler.world.dimensions.generation.OldEgyptGenSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;

public class OldEgyptDimension extends OverworldDimension
{
	
	public OldEgyptDimension(World world, DimensionType type)
	{
		super(world, type);
		this.doesWaterVaporize = true;
	}
	
	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}
	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	public SleepResult canSleepAt(PlayerEntity player, BlockPos pos)
	{
		return SleepResult.DENY;
	}
	
	@Override
	public ChunkGenerator<? extends GenerationSettings> createChunkGenerator()
	{
		return new OldEgyptChunkGenerator(world, new OldEgyptBiomeProvider(new MTBiomeProviderSettings()), new OldEgyptGenSettings());
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}
	
	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid)
	{
		return findSpawn(0, 0, checkValid);
	}
	
	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid)
	{
		return super.findSpawn(0, 0, checkValid);
	}
}
