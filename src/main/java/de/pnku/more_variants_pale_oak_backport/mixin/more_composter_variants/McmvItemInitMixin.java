package de.pnku.more_variants_pale_oak_backport.mixin.more_composter_variants;

import de.pnku.mcmv.init.McmvItemInit;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(McmvItemInit.class)
public abstract class McmvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_COMPOSTER_ITEM = registerPaleOakComposterItem();

    @Shadow
    private static void registerItem(BlockItem composter, Item composterAfter) {}

    @Unique
    private static BlockItem registerPaleOakComposterItem() {
        BlockItem composterItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.COMPOSTER, WOOD_TYPE), new Item.Properties());
        registerItem(composterItem, Items.COMPOSTER);
        PaleOakVariantHolder.setItem(VariantType.COMPOSTER, WOOD_TYPE, composterItem);
        return composterItem;
    }
}
