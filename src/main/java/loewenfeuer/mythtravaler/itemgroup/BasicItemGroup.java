package loewenfeuer.mythtravaler.itemgroup;

import java.util.function.Supplier;

import loewenfeuer.mythtravaler.MythTravalerMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class BasicItemGroup extends ItemGroup
{
	private final Supplier<IItemProvider> supplier;
	
	public BasicItemGroup(String label, Supplier<IItemProvider> supplier)
	{
		super(MythTravalerMain.MOD_ID + "." + label);
		this.supplier = supplier;
		
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack(supplier.get());
	}
}
