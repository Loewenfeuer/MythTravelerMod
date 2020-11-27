package loewenfeuer.mythtravaler.objects.items;

import loewenfeuer.mythtravaler.init.MythTraModItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
//import net.minecraft.item.KnowledgeBookItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ReligionBookItem extends WrittenBookItem
{
//	public final Effect effect;
	
//	public ReligionBookItem(Effect effect)
//	{
//		super(new Properties().group(MythTraModItemGroups.RELIGION).rarity(Rarity.UNCOMMON));
//		this.effect = effect;
//	}
	
	public ReligionBookItem()
	{
		super(new Properties().group(MythTraModItemGroups.RELIGION).rarity(Rarity.UNCOMMON));
	}

	@OnlyIn(Dist.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	/**
	 * Checks isDamagable and if it cannot be stacked
	 */
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

	/**
	 * Called to trigger the item's "innate" right click behavior.
	 */
//	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
//	{
//		ItemStack itemstack = player.getHeldItem(hand);
//		player.openBook(itemstack, hand);
//		player.addStat(Stats.ITEM_USED.get(this));
//		player.addPotionEffect(new EffectInstance(effect, -1));
//		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
//	}

}

//KnowlegeBookItem
//WrittenBookItem