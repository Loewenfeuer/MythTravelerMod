package loewenfeuer.mythtravaler.world.dimensions.generation;

import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;

public abstract class MTChunkGeneratorBase extends NoiseChunkGenerator<MTGenerationSettings>
{

	private static final float[] field_222576_h = Util.make(new float[25], (afloat) ->
	{
		for (int i = -2; i <= 2; ++i)
		{
			for (int j = -2; j <= 2; ++j)
			{
				float f = 10.0F / MathHelper.sqrt((float) (i * i + j * j) + 0.2F);
				afloat[i + 2 + (j + 2) * 5] = f;
			}
		}

	});

	private final OctavesNoiseGenerator depthNoise;
	private final boolean isAmplified;
	private boolean shouldGenerateBedrock = true;

	public MTChunkGeneratorBase(IWorld world, MTBiomeProvider provider, MTGenerationSettings settings,
			boolean shouldGenerateBedrock)
	{
		this(world, provider, settings);

		this.shouldGenerateBedrock = shouldGenerateBedrock;
	}

	public MTChunkGeneratorBase(IWorld world, MTBiomeProvider provider, MTGenerationSettings settings)
	{
		super(world, provider, 4, 8, 256, settings, true);
		this.randomSeed.skip(2620);
		this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 15);
		this.isAmplified = world.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
	}

	@Override
	protected void fillNoiseColumn(double[] noiseY, int noiseX, int noiseZ)
	{
		double d0 = (double) 684.412F;
		double d1 = (double) 684.412F;
		double d2 = 8.555149841308594D;
		double d3 = 4.277574920654297D;
		int i = -10;
		int j = 3;
		this.func_222546_a(noiseY, noiseX, noiseZ, d0, d1, d2, d3, j, i);
	}

	@Override
	protected double func_222545_a(double depth, double noise, int y)
	{
		double d1 = ((double) y - (8.5D + depth * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / noise;
		if (d1 < 0.0D)
		{
			d1 *= 4.0D;
		}

		return d1;
	}

	@Override
	protected double[] getBiomeNoiseColumn(int x, int z)
	{
		double[] adouble = new double[2];
		float f = 0.0F;
		float f1 = 0.0F;
		float f2 = 0.0F;
		int j = this.getSeaLevel();
		float f3 = ((MTBiomeProvider) this.biomeProvider).getBiomeForNoiseGen(x, j, z).getDepth();

		for (int k = -2; k <= 2; ++k)
		{
			for (int l = -2; l <= 2; ++l)
			{
				Biome biome = ((MTBiomeProvider) this.biomeProvider).getBiomeForNoiseGen(x + k, j, z + l);
				float f4 = biome.getDepth();
				float f5 = biome.getScale();
				if (this.isAmplified && f4 > 0.0F)
				{
					f4 = 1.0F + f4 * 2.0F;
					f5 = 1.0F + f5 * 4.0F;
				}

				float f6 = field_222576_h[k + 2 + (l + 2) * 5] / (f4 + 2.0F);
				if (biome.getDepth() > f3)
				{
					f6 /= 2.0F;
				}

				f += f5 * f6;
				f1 += f4 * f6;
				f2 += f6;
			}
		}

		f = f / f2;
		f1 = f1 / f2;
		f = f * 0.9F + 0.1F;
		f1 = (f1 * 4.0F - 1.0F) / 8.0F;
		adouble[0] = (double) f1 + this.getNoiseDepthAt(x, z);
		adouble[1] = (double) f;
		return adouble;
	}

	private double getNoiseDepthAt(int p_222574_1_, int p_222574_2_)
	{
		double d0 = this.depthNoise.getValue((double) (p_222574_1_ * 200), 10.0D, (double) (p_222574_2_ * 200), 1.0D,
				0.0D, true) * 65535.0D / 8000.0D;
		if (d0 < 0.0D)
		{
			d0 = -d0 * 0.3D;
		}

		d0 = d0 * 3.0D - 2.0D;
		if (d0 < 0.0D)
		{
			d0 = d0 / 28.0D;
		} else
		{
			if (d0 > 1.0D)
			{
				d0 = 1.0D;
			}

			d0 = d0 / 40.0D;
		}

		return d0;
	}

	@Override
	public int getGroundHeight()
	{
		return this.world.getSeaLevel() + 2;
	}

	public boolean shouldGenerateBedrock()
	{
		return false;
	}

}
