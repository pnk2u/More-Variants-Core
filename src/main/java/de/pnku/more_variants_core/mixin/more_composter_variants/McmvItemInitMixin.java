package de.pnku.more_variants_core.mixin.more_composter_variants;

import de.pnku.mcmv.init.McmvItemInit;
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

@Mixin(McmvItemInit.class)
public abstract class McmvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_COMPOSTER_ITEM = registerPaleOakComposterItem();

    @Shadow
    private static void registerItem(BlockItem composter, Item composterAfter) {}

    @Unique
    private static BlockItem registerPaleOakComposterItem() {
        BlockItem composterItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.COMPOSTER, WOOD_TYPE), new Item.Properties());
        registerItem(composterItem, Items.COMPOSTER);
        MoreVariantHolder.setItem(VariantType.COMPOSTER, WOOD_TYPE, composterItem);
        return composterItem;
    }
}
