package de.pnku.more_variants_pale_oak_backport.mixin.more_grindstone_variants;

import de.pnku.mgv.init.MgvItemInit;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakGrindstoneHolder;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakGrindstoneHolder.GrindstoneType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MgvItemInit.class)
public abstract class MgvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_BASALT_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.BASALT);
    @Unique
    private static final BlockItem PALE_OAK_DEEPSLATE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.DEEPSLATE);
    @Unique
    private static final BlockItem PALE_OAK_GRANITE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.GRANITE);
    @Unique
    private static final BlockItem PALE_OAK_SANDSTONE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.SANDSTONE);
    @Unique
    private static final BlockItem PALE_OAK_STONE_GRINDSTONE = registerPaleOakGrindstone(GrindstoneType.STONE);

    @Invoker("registerItem")
    public static void invokeRegisterItem(BlockItem blockItem, Item itemAfter) {
        throw new AssertionError();
    }

    @Unique
    private static BlockItem registerPaleOakGrindstone(GrindstoneType grindstoneType) {
        BlockItem blockItem = new BlockItem(PaleOakGrindstoneHolder.getBlock(WOOD_TYPE, grindstoneType), new Item.Properties());
        invokeRegisterItem(blockItem, Items.GRINDSTONE);
        PaleOakGrindstoneHolder.setItem(WOOD_TYPE, grindstoneType, blockItem);
        return blockItem;
    }
}
