package de.pnku.more_variants_pale_oak_backport.mixin.more_lectern_variants;

import de.pnku.mlv.init.MlvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MlvItemInit.class)
public abstract class MlvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_LECTERN_ITEM = registerPaleOakLecternItem();

    @Shadow
    private static void registerItem(BlockItem lectern, Item lecternAfter) {}

    @Unique
    private static BlockItem registerPaleOakLecternItem() {
        BlockItem lecternItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.LECTERN, WOOD_TYPE), new Item.Properties());
        registerItem(lecternItem, Items.LECTERN);
        PaleOakVariantHolder.setItem(VariantType.LECTERN, WOOD_TYPE, lecternItem);
        return lecternItem;
    }
}
