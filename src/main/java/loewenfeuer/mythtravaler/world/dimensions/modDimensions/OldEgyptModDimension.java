package loewenfeuer.mythtravaler.world.dimensions.modDimensions;

import java.util.function.BiFunction;

import loewenfeuer.mythtravaler.world.dimensions.OldEgyptDimension;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class OldEgyptModDimension extends ModDimension //implements IURegistryType 
{
	public OldEgyptModDimension()
	{
		getFactory();
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
	{
		return OldEgyptDimension::new;
	}
}
