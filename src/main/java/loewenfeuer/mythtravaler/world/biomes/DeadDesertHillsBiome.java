package loewenfeuer.mythtravaler.world.biomes;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import loewenfeuer.mythtravaler.world.dimensions.generation.surfacebuilders.MTDefaultSurfaceBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DeadDesertHillsBiome extends Biome
{
	public DeadDesertHillsBiome()
	{
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, MTDefaultSurfaceBuilder.DEAD_SAND_CONFIG)
				.precipitation(Biome.RainType.NONE)
				.category(Category.DESERT)
				.depth(1.0F)
				.scale(0.4F)
				.temperature(4.0F)
				.downfall(0.0F)
				.waterColor(0x040000)
				.waterFogColor(0x050000)
				.parent((String)null)
				);
		
		
		DefaultBiomeFeatures.addDeadBushes(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		
		this.addCarver(GenerationStage.Carving.AIR, Biome.createCarver(WorldCarver.CAVE, new ProbabilityConfig(0.14285715F)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.FOSSIL, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_PASSTHROUGH, new ChanceConfig(64)));
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(32)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, MythTraModBlocks.DEAD_SANDSTONE_GOLD_ORE.getDefaultState(), 8), Placement.COUNT_RANGE, new CountRangeConfig(6, 0, 0, 16)));
	    this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, MythTraModBlocks.DEAD_SANDSTONE_LAPIS_ORE.getDefaultState(), 16), Placement.COUNT_DEPTH_AVERAGE, new DepthAverageConfig(8, 12, 12)));
	    //this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, MythTraModBlocks.DEAD_SANDSTONE_BLOCK.getDefaultState(), 128), Placement.COUNT_RANGE, new CountRangeConfig(64, 60, 0, 256)));
	    
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 16, 1, 2));
	    this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 4, 1, 1));
		
	}
}
