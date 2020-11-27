package loewenfeuer.mythtravaler.world.dimensions.generation;

import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class MTBiomeProviderSettings implements IBiomeProviderSettings
{

	private long seed;
	private WorldInfo worldInfo;
	private MTGenerationSettings generatorSettings;

	public long getSeed()
	{
		return seed;
	}

	public WorldInfo getWorldInfo()
	{
		return this.worldInfo;
	}

	public MTBiomeProviderSettings setWorldInfo(WorldInfo info)
	{
		this.worldInfo = info;
		return this;
	}

	public MTBiomeProviderSettings setGeneratorSettings(MTGenerationSettings p_205441_1_)
	{
		this.generatorSettings = p_205441_1_;
		return this;
	}

	public MTGenerationSettings getGeneratorSettings()
	{
		return this.generatorSettings;
	}
}
