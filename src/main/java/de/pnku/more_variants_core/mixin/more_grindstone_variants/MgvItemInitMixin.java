package de.pnku.more_variants_core.mixin.more_grindstone_variants;

import de.pnku.mgv.init.MgvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.GrindstoneType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MgvItemInit.class)
public abstract class MgvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

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

    @Shadow
    private static void registerItem(BlockItem grindstone, Item grindstoneAfter) {}

    @Unique
    private static BlockItem registerPaleOakGrindstone(GrindstoneType grindstoneType) {
        BlockItem blockItem = new BlockItem(MoreVariantHolder.getBlock(grindstoneType, WOOD_TYPE), new Item.Properties());
        registerItem(blockItem, Items.GRINDSTONE);
        MoreVariantHolder.setItem(grindstoneType, WOOD_TYPE, blockItem);
        return blockItem;
    }
}
