package loewenfeuer.mythtravaler.objects.items;

import loewenfeuer.mythtravaler.init.MTDimensions;
import loewenfeuer.mythtravaler.init.MythTraModItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BasicItem extends Item
{

	public BasicItem()
	{
		super(new Properties().group(MythTraModItemGroups.TEST).maxStackSize(1).rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		if(!MTDimensions.OLD_EGYPT_BIOME_PROVIDER.isPresent() && !MTDimensions.OLD_EGYPT_CHUNK_GEN.isPresent())
			System.out.println("!!!!!!!!!!!!!!!! BiomeProvider und ChunkGenerator not registered !!!!!!!!!!!!!!!!");
		else System.out.println("check!");
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}