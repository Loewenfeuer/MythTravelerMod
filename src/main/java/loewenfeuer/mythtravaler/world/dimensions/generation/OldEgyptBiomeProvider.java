package loewenfeuer.mythtravaler.world.dimensions.generation;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.LongFunction;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import loewenfeuer.mythtravaler.init.MTBiomes;
import loewenfeuer.mythtravaler.world.dimensions.generation.layer.MTGenLayerBiomeStabilize;
import loewenfeuer.mythtravaler.world.dimensions.generation.layer.MTGenLayerBiomes;
import loewenfeuer.mythtravaler.world.dimensions.generation.layer.MTGenLayerCompanionBiomes;
import loewenfeuer.mythtravaler.world.dimensions.generation.layer.MTGenLayerKeyBiomes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.ZoomLayer;

public class OldEgyptBiomeProvider extends MTBiomeProvider // TODO Uncomplete
{
	private final Layer genBiomes;
	
	private static final Set<Biome> BIOMES = ImmutableSet.of(MTBiomes.DEAD_DESERT,
			MTBiomes.DEAD_DESERT_HILLS);


	public OldEgyptBiomeProvider(MTBiomeProviderSettings world)
	{
		super(world);
		getBiomesToSpawnIn().clear();
		getBiomesToSpawnIn().add(MTBiomes.DEAD_DESERT);
		//getBiomesToSpawnIn().add(MythTraModBiomes.DEAD_DESERT_HILLS);
		
		this.genBiomes = makeLayers(world.getSeed());
	}
	
	
	@Override
	public Set<BlockState> getSurfaceBlocks()
	{
		if(this.topBlocksCache.isEmpty())
		{
			for(Biome biome : this.BIOMES)
			{
				this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
			}
		}
		return this.topBlocksCache;
	}
	
	public Biome getBiomeForNoiseGen(int x, int y, int z)
	{
		return this.genBiomes.func_215738_a(x, z);
	}
	
	@Nullable
	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
	{
		int i = x - range >> 2;
		int j = z - range >> 2;
		int k = x + range >> 2;
		int l = z + range >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		
		Biome[] abiome = this.genBiomes.generateBiomes(i, j, i1, j1);
		BlockPos blockPos = null;
		int k1 = 0;
		
		for(int l1 = 0; l1 < i1 * j1; ++l1)
		{
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / j1 << 2;
			if(biomes.contains(abiome[l1]))
			{
				if(blockPos == null || random.nextInt(k1 + 1) == 0)
				{
					blockPos = new BlockPos(i2, 0, j2);
				}
				++k1;
			}
		}
		
		return blockPos;
	}
	
	@Override
	public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength)
	{
		int i = centerX - sideLength >> 2;
		int j = centerZ - sideLength >> 2;
		int k = centerX + sideLength >> 2;
		int l = centerZ + sideLength >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		
		Set<Biome> set = Sets.newHashSet();
		Collections.addAll(set, this.genBiomes.generateBiomes(i, j, i1, j1));
		
		return set;
	}

	@Override
	public boolean hasStructure(Structure<?> arg0)
	{
		return false;
	}
	
	private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> makeLayers(LongFunction<C> seed) {
		IAreaFactory<T> biomes = new MTGenLayerBiomes().apply(seed.apply(1L));
		biomes = MTGenLayerKeyBiomes.INSTANCE.apply(seed.apply(1000L), biomes);
		biomes = MTGenLayerCompanionBiomes.INSTANCE.apply(seed.apply(1000L), biomes);

		biomes = ZoomLayer.NORMAL.apply(seed.apply(1000L), biomes);
		biomes = ZoomLayer.NORMAL.apply(seed.apply(1001), biomes);

		biomes = MTGenLayerBiomeStabilize.INSTANCE.apply(seed.apply(700L), biomes);

		biomes = ZoomLayer.NORMAL.apply(seed.apply(1002), biomes);
		biomes = ZoomLayer.NORMAL.apply(seed.apply(1003), biomes);
		biomes = ZoomLayer.NORMAL.apply(seed.apply(1004), biomes);
		biomes = ZoomLayer.NORMAL.apply(seed.apply(1005), biomes);
		
		return biomes;
	}
	
	public static Layer makeLayers(long seed) {
		IAreaFactory<LazyArea> areaFactory = makeLayers((context) -> new LazyAreaLayerContext(25, seed, context));
		return new Layer(areaFactory);
	}
	
	@Override
	public Biome getBiome(int x, int y)
	{
		return this.genBiomes.func_215738_a(x, y);
	}

	@Override
	public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag)
	{
		return this.genBiomes.generateBiomes(x, z, width, length);
	}
}
