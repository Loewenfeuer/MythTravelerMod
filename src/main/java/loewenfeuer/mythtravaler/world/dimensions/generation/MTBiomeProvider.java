package loewenfeuer.mythtravaler.world.dimensions.generation;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.feature.structure.Structure;

public abstract class MTBiomeProvider extends BiomeProvider
{
	protected Random rand;
	
	public MTBiomeProvider(MTBiomeProviderSettings world)
	{
		super();
//
//		WorldInfo worldInfo = world.getWorldInfo();
//		MTGenerationSettings mtGenSettings = world.getGeneratorSettings();
//		Layer[] alayer = new Layer[2];// LayerUtil.buildOverworldProcedure(worldInfo.getSeed(), worldInfo.getGenerator(), mtGenSettings);		TODO: figure out how to this works correctly
//		this.genBiomes = alayer[0];
//		this.biomeFactoryLayer = alayer[1];
	}
	
	public abstract Biome getBiomeForNoiseGen(int x, int y, int z);
	
	@Nullable
	@Override
	public abstract BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random);

//	@Override
//	public Biome getBiome(int x, int y)
//	{
//		return this.biomeFactoryLayer.func_215738_a(x, y);
//	}
//
//	@Override
//	public abstract Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag);
//	{
//		return this.biomeFactoryLayer.generateBiomes(x, z, width, length);
//	}

	@Override
	public abstract Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength);
	
	@Override
	public abstract Set<BlockState> getSurfaceBlocks();
	
	@Override
	public abstract boolean hasStructure(Structure<?> arg0);
}
