package loewenfeuer.mythtravaler.init;

import loewenfeuer.mythtravaler.itemgroup.BasicItemGroup;

public class MythTraModItemGroups
{
	public static final BasicItemGroup OLD_EGYPT = new BasicItemGroup("old_egypt", () -> MythTraModBlocks.DEAD_CHISELED_SANDSTONE_BLOCK);
	public static final BasicItemGroup RELIGION = new BasicItemGroup("religion", () -> MythTraModItems.OLD_EGYPT_BOOK);
	public static final BasicItemGroup TEST = new BasicItemGroup("test", () -> MythTraModItems.OLD_EGYPT_BOOK);
}
