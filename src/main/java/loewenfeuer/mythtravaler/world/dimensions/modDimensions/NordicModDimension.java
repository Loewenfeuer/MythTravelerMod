package loewenfeuer.mythtravaler.world.dimensions.modDimensions;

import java.util.function.BiFunction;

import loewenfeuer.mythtravaler.world.dimensions.NordicDimension;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class NordicModDimension extends ModDimension //implements IURegistryType 
{
	public NordicModDimension()
	{
		getFactory();
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
	{
		return NordicDimension::new;
	}
}
