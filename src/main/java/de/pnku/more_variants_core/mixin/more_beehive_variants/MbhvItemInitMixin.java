package de.pnku.more_variants_core.mixin.more_beehive_variants;

import de.pnku.mbhv.init.MbhvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MbhvItemInit.class)
public abstract class MbhvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_BEEHIVE_ITEM = registerPaleOakBeehiveItem();

    @Shadow
    private static void registerBeehiveItem(BlockItem beehiveItem, Item beehiveAfter) {}

    @Unique
    private static BlockItem registerPaleOakBeehiveItem() {
        BlockItem beehiveItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.BEEHIVE, WOOD_TYPE), new Item.Properties());
        registerBeehiveItem(beehiveItem, Items.BEEHIVE);
        MoreVariantHolder.setItem(VariantType.BEEHIVE, WOOD_TYPE, beehiveItem);
        return beehiveItem;
    }
}
