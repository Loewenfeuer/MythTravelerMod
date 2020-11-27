package loewenfeuer.mythtravaler.world.dimensions;

import java.util.function.BiFunction;

import loewenfeuer.mythtravaler.MythTravalerMain;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class MythTraModDimension extends ModDimension //implements IURegistryType 
{
	public MythTraModDimension()
	{
		getFactory();
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory()
	{
		return OldEgyptDimension::new;
	}
	
	
//	protected final String name;
//	
//	private final BiFunction<World, DimensionType, ? extends Dimension> function;
//	
//	public MythTraModDimension(String name, BiFunction<World, DimensionType, ? extends Dimension> function) {
//		this.name = name;
//		this.function = function;
//		this.setRegistryName(MythTravalerMain.MOD_ID, name);
//	}
//	
//	
//	public String getEntryName() {
//		return name;
//	}
//	
//	@Override
//	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
//		return function;
//	}

}
