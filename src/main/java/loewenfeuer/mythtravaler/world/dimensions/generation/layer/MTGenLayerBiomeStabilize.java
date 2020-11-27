package loewenfeuer.mythtravaler.world.dimensions.generation.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public enum MTGenLayerBiomeStabilize implements IAreaTransformer1
{
	INSTANCE;

	private MTGenLayerBiomeStabilize()
	{
	}

	@Override
	public int func_215721_a(int x)
	{
		return x & 3;
	}

	@Override
	public int func_215722_b(int z)
	{
		return z & 3;
	}

	@Override
	public int func_215728_a(IExtendedNoiseRandom<?> iExtendedNoiseRandom, IArea iArea, int dx, int dz)
	{
		int offX = func_215721_a(dx);
		int offZ = func_215722_b(dz);
		int centerX = ((dx + offX + 1) & 0xFFFFFFFC) - offX;
		int centerZ = ((dz + offZ + 1) & 0xFFFFFFFC) - offZ;

		if (dx <= centerX + 1 && dx >= centerX - 1 && dz <= centerZ + 1 && dz >= centerZ - 1)
		{
			return centerX + 1 + (centerZ + 1) * dz;
		} else
		{
			return dx + 1 + (dz + 1) * dz;
		}
	}

}
