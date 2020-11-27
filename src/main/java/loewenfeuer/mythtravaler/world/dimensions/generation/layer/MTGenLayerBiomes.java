package loewenfeuer.mythtravaler.world.dimensions.generation.layer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import loewenfeuer.mythtravaler.init.MTBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraftforge.event.RegistryEvent.Register;

public class MTGenLayerBiomes implements IAreaTransformer0
{

	private static final int RARE_BIOME_CHANCE = 15;

	protected static final List<Supplier<Supplier<Biome>>> commonBiomes = Arrays.asList(() -> MTBiomes.deadDes);
	protected static final List<Supplier<Supplier<Biome>>> rareBiomes = Arrays.asList(() -> MTBiomes.deadDesHills);

	public MTGenLayerBiomes()
	{
		
	}

	@Override
	public int apply(INoiseRandom iNoiseRandom, int i, int i1)
	{
		if (iNoiseRandom.random(RARE_BIOME_CHANCE) == 0)
		{
			// make rare biome
			return Registry.BIOME.getId(getRandomBiome(iNoiseRandom, rareBiomes));
		} else
		{
			// make common biome
			return Registry.BIOME.getId(getRandomBiome(iNoiseRandom, commonBiomes));
		}
	}

	private Biome getRandomBiome(INoiseRandom random, List<Supplier<Supplier<Biome>>> biomes)
	{
		return biomes.get(random.random(biomes.size())).get().get();
	}
}
