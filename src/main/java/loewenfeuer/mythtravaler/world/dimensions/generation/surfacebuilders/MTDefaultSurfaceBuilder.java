package loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTChunkGeneratorBase;
import loewenfeuer.mythtravaler.world.dimensions.generation.MTGenerationSettings;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public abstract class MTDefaultSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig>
{
	public static final BlockState DEAD_SAND = MythTraModBlocks.DEAD_SAND_BLOCK.getDefaultState();
	public static final BlockState DEAD_SANDSTONE = MythTraModBlocks.DEAD_SANDSTONE_BLOCK.getDefaultState();
	public static final SurfaceBuilderConfig DEAD_SAND_SAND_SAND_CONFIG = new SurfaceBuilderConfig(DEAD_SAND, DEAD_SAND,
			DEAD_SAND);
	public static final SurfaceBuilderConfig DEAD_SAND_CONFIG = new SurfaceBuilderConfig(DEAD_SAND, DEAD_SANDSTONE,
			DEAD_SAND);
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNE = register("dune",
			new OldEgyptSurfaceBuilder(SurfaceBuilderConfig::deserialize));
	public static final SurfaceBuilder<SurfaceBuilderConfig> DUNE_HILL = register("dune_hill",
			new OLDEgyptHillSurfaceBuilder(SurfaceBuilderConfig::deserialize));

	public MTDefaultSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config)
	{
		super(config);
	}

	@SuppressWarnings("unchecked")
	private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builderIn)
	{
		return (F) (Registry.<SurfaceBuilder<?>>register(Registry.SURFACE_BUILDER, key, builderIn));
	}

	@Override
	public abstract void buildSurface(Random rand, IChunk primer, Biome biome, int x, int z, int startheight,
			double noiseVal, BlockState defaultBlock, BlockState defaultFluid, int sealevel, long seed,
			SurfaceBuilderConfig config);

	// Copy of super's generateBiomeTerrain, relevant edits noted.
	// protected void genTwilightBiomeTerrain(World world, Random rand, ChunkPrimer
	// primer, int x, int z, double noiseVal) {
	protected abstract void genBiomeTerrain(Random rand, IChunk primer, Biome biome, int x, int z, int startHeight,
			double noiseVal, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle,
			BlockState bottom, int sealevel);

	// TODO: Re-evaluate
	private static boolean shouldGenerateBedrock(World world)
	{
		MTChunkGeneratorBase generator = MTGenerationSettings.getChunkGenerator(world);
		return generator == null || generator.shouldGenerateBedrock();
	}
}