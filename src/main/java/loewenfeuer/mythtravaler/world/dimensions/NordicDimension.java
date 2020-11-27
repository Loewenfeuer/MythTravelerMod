package loewenfeuer.mythtravaler.world.dimensions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.CheckerboardBiomeProvider;
import net.minecraft.world.biome.provider.CheckerboardBiomeProviderSettings;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.EndChunkGenerator;
import net.minecraft.world.gen.EndGenerationSettings;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.NetherChunkGenerator;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class NordicDimension extends Dimension
{

	public NordicDimension(World worldIn, DimensionType typeIn)
	{
		super(worldIn, typeIn);
	}

	@Override
	public ChunkGenerator<? extends GenerationSettings> createChunkGenerator()
	{
		WorldType worldtype = this.world.getWorldInfo().getGenerator();

		// ChunkGeneratorTypes
		ChunkGeneratorType<NetherGenSettings, NetherChunkGenerator> chunkgeneratortype2 = ChunkGeneratorType.CAVES;
		ChunkGeneratorType<EndGenerationSettings, EndChunkGenerator> chunkgeneratortype3 = ChunkGeneratorType.FLOATING_ISLANDS;
		ChunkGeneratorType<OverworldGenSettings, OverworldChunkGenerator> chunkgeneratortype4 = ChunkGeneratorType.SURFACE;

		// BiomeProvider
		BiomeProviderType<OverworldBiomeProviderSettings, OverworldBiomeProvider> biomeprovidertype1 = BiomeProviderType.VANILLA_LAYERED;
		BiomeProviderType<CheckerboardBiomeProviderSettings, CheckerboardBiomeProvider> biomeprovidertype2 = BiomeProviderType.CHECKERBOARD;

		BiomeProvider biomeprovider = null;
		JsonElement jsonelement = Dynamic.convert(NBTDynamicOps.INSTANCE, JsonOps.INSTANCE, this.world.getWorldInfo().getGeneratorOptions());
		JsonObject jsonobject = jsonelement.getAsJsonObject();
		JsonObject jsonobject1 = jsonobject.getAsJsonObject("biome_source");
//		if (jsonobject1 != null && jsonobject1.has("type") && jsonobject1.has("options"))
//		{
//			BiomeProviderType<?, ?> biomeprovidertype3 = Registry.BIOME_SOURCE_TYPE
//					.getOrDefault(new ResourceLocation(jsonobject1.getAsJsonPrimitive("type").getAsString()));
//			JsonObject jsonobject2 = jsonobject1.getAsJsonObject("options");
//			Biome[] abiome = new Biome[]
//			{ Biomes.OCEAN };
//			if (jsonobject2.has("biomes"))
//			{
//				JsonArray jsonarray = jsonobject2.getAsJsonArray("biomes");
//				abiome = jsonarray.size() > 0 ? new Biome[jsonarray.size()] : new Biome[]
//				{ Biomes.OCEAN };
//
//				for (int i = 0; i < jsonarray.size(); ++i)
//				{
//					abiome[i] = Registry.BIOME.getValue(new ResourceLocation(jsonarray.get(i).getAsString()))
//							.orElse(Biomes.OCEAN);
//				}
//			}
//
//			if (BiomeProviderType.CHECKERBOARD == biomeprovidertype3)
//			{
//				int j = jsonobject2.has("size") ? jsonobject2.getAsJsonPrimitive("size").getAsInt() : 2;
//				CheckerboardBiomeProviderSettings checkerboardbiomeprovidersettings = biomeprovidertype2
//						.createSettings().setBiomes(abiome).setSize(j);
//				biomeprovider = biomeprovidertype2.create(checkerboardbiomeprovidersettings);
//			}
//
//			if (BiomeProviderType.VANILLA_LAYERED == biomeprovidertype3)
//			{
//				OverworldBiomeProviderSettings overworldbiomeprovidersettings1 = biomeprovidertype1.createSettings()
//						.setGeneratorSettings(new OverworldGenSettings()).setWorldInfo(this.world.getWorldInfo());
//				biomeprovider = biomeprovidertype1.create(overworldbiomeprovidersettings1);
//			}
//		}

		BlockState blockstate = Blocks.STONE.getDefaultState();
		BlockState blockstate1 = Blocks.WATER.getDefaultState();
		JsonObject jsonobject3 = jsonobject.getAsJsonObject("chunk_generator");
		if (jsonobject3 != null && jsonobject3.has("options"))
		{
			JsonObject jsonobject4 = jsonobject3.getAsJsonObject("options");
			if (jsonobject4.has("default_block"))
			{
				String s = jsonobject4.getAsJsonPrimitive("default_block").getAsString();
				blockstate = Registry.BLOCK.getOrDefault(new ResourceLocation(s)).getDefaultState();
			}

			if (jsonobject4.has("default_fluid"))
			{
				String s1 = jsonobject4.getAsJsonPrimitive("default_fluid").getAsString();
				blockstate1 = Registry.BLOCK.getOrDefault(new ResourceLocation(s1)).getDefaultState();
			}
		}

		if (jsonobject3 != null && jsonobject3.has("type"))
		{
			ChunkGeneratorType<?, ?> chunkgeneratortype5 = Registry.CHUNK_GENERATOR_TYPE
					.getOrDefault(new ResourceLocation(jsonobject3.getAsJsonPrimitive("type").getAsString()));
			if (ChunkGeneratorType.CAVES == chunkgeneratortype5)
			{
				NetherGenSettings nethergensettings = chunkgeneratortype2.createSettings();
				nethergensettings.setDefaultBlock(blockstate);
				nethergensettings.setDefaultFluid(blockstate1);
				return chunkgeneratortype2.create(this.world, biomeprovider, nethergensettings);
			}

			if (ChunkGeneratorType.FLOATING_ISLANDS == chunkgeneratortype5)
			{
				EndGenerationSettings endgenerationsettings = chunkgeneratortype3.createSettings();
				endgenerationsettings.setSpawnPos(new BlockPos(0, 64, 0));
				endgenerationsettings.setDefaultBlock(blockstate);
				endgenerationsettings.setDefaultFluid(blockstate1);
				return chunkgeneratortype3.create(this.world, biomeprovider, endgenerationsettings);
			}
		}

		OverworldGenSettings overworldgensettings1 = chunkgeneratortype4.createSettings();
		overworldgensettings1.setDefaultBlock(blockstate);
		overworldgensettings1.setDefaultFluid(blockstate1);
		return chunkgeneratortype4.create(this.world, biomeprovider, overworldgensettings1);
	}

	@Override
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid)
	{
		return null;
	}

	@Override
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid)
	{
		return null;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	@Override
	public Vec3d getFogColor(float celestialAngle, float partialTicks)
	{
		return null;
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	public boolean doesXZShowFog(int x, int z)
	{
		return false;
	}

}
