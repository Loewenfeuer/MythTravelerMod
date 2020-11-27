package loewenfeuer.mythtravaler.world.dimensions.generation.layer;

import loewenfeuer.mythtravaler.init.MTBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum MTGenLayerCompanionBiomes implements ICastleTransformer
{

	INSTANCE;

	MTGenLayerCompanionBiomes()
	{
	}

	@Override
	public int apply(INoiseRandom noise, int up, int left, int down, int right, int center)
	{
		int deaddes = Registry.BIOME.getId(MTBiomes.deadDes.get());
		int deaddeshills = Registry.BIOME.getId(MTBiomes.deadDesHills.get());

		if (isKey(deaddes, center, right, left, up, down))
		{
			return deaddeshills;
		} else if (isKey(deaddeshills, center, right, left, up, down))
		{
			return deaddes;
		} else
		{
			return deaddes;
		}
	}

	/**
	 * Returns true if any of the surrounding biomes is the specified biome
	 */
	boolean isKey(int biome, int center, int right, int left, int up, int down)
	{
		return center != biome && (right == biome || left == biome || up == biome || down == biome);
	}
}