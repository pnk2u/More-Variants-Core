package de.pnku.more_variants_pale_oak_backport.mixin.more_crafter_variants;

import de.pnku.mcrv.init.McrvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McrvItemInit.class)
public abstract class McrvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CRAFTER_ITEM = registerPaleOakCrafterItem();

    @Shadow
    private static void registerCrafterItem(BlockItem crafter) {}

    @Unique
    private static BlockItem registerPaleOakCrafterItem() {
        BlockItem crafterItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.CRAFTER, WOOD_TYPE), new Item.Properties());
        registerCrafterItem(crafterItem);
        PaleOakVariantHolder.setItem(VariantType.CRAFTER, WOOD_TYPE, crafterItem);
        return crafterItem;
    }
}
