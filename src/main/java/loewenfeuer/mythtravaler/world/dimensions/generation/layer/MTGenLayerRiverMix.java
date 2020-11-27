package loewenfeuer.mythtravaler.world.dimensions.generation.layer;

import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum MTGenLayerRiverMix implements IAreaTransformer2, IDimOffset0Transformer
{

	INSTANCE;

	MTGenLayerRiverMix()
	{
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, IArea area1, IArea area2, int val1, int val2)
	{
		int biomeInputs = area1.getValue(this.func_215721_a(val1), this.func_215722_b(val2));
		int riverInputs = area2.getValue(this.func_215721_a(val1), this.func_215722_b(val2));

//		int stream = Registry.BIOME.getId(MTBiomes.stream.get());
//
//		if (riverInputs == stream)
//		{
//			return riverInputs;
//		} else
//		{
			return biomeInputs;
//		}
	}
}