package de.pnku.more_variants_core.mixin.more_lectern_variants;

import de.pnku.mlv.init.MlvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MlvItemInit.class)
public abstract class MlvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_LECTERN_ITEM = registerPaleOakLecternItem();

    @Shadow
    private static void registerItem(BlockItem lectern, Item lecternAfter) {}

    @Unique
    private static BlockItem registerPaleOakLecternItem() {
        BlockItem lecternItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.LECTERN, WOOD_TYPE), new Item.Properties());
        registerItem(lecternItem, Items.LECTERN);
        MoreVariantHolder.setItem(VariantType.LECTERN, WOOD_TYPE, lecternItem);
        return lecternItem;
    }
}
