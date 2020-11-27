package loewenfeuer.mythtravaler.objects.blocks;

import java.util.Random;

import loewenfeuer.mythtravaler.init.MythTraModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DeadSandstoneOre extends Block
{

	public DeadSandstoneOre()
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(0.8F));
	}

	protected int func_220281_a(Random p_220281_1_)
	{
		return this == MythTraModBlocks.DEAD_SANDSTONE_LAPIS_ORE ? MathHelper.nextInt(p_220281_1_, 2, 5) : 0;
	}

	/**
	 * Perform side-effects from block dropping, such as creating silverfish
	 */
	@SuppressWarnings("deprecation")
	public void spawnAdditionalDrops(BlockState state, World worldIn, BlockPos pos, ItemStack stack)
	{
		super.spawnAdditionalDrops(state, worldIn, pos, stack);
	}

	@Override
	public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune,
			int silktouch)
	{
		return silktouch == 0 ? this.func_220281_a(RANDOM) : 0;
	}
}
