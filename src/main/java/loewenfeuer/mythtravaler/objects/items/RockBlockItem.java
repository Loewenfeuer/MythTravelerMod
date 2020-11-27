package loewenfeuer.mythtravaler.objects.items;

import loewenfeuer.mythtravaler.init.MythTraModItemGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class RockBlockItem extends BlockItem
{
	public RockBlockItem(Block block)
	{
		super(block, new Properties().group(MythTraModItemGroups.OLD_EGYPT));
		setRegistryName(block.getRegistryName());
	}

}
