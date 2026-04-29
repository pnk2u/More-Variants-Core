package de.pnku.more_variants_core.mixin.more_smoker_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.SmokerType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypes;
import de.pnku.msmv.init.MsmvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MsmvItemInit.class)
public abstract class MsmvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodTypes.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_COBBLESTONE_SMOKER_ITEM = registerPaleOakSmokerItem(SmokerType.COBBLESTONE);
    @Unique
    private static final BlockItem PALE_OAK_BLACKSTONE_SMOKER_ITEM = registerPaleOakSmokerItem(SmokerType.BLACKSTONE);
    @Unique
    private static final BlockItem PALE_OAK_DEEPSLATE_SMOKER_ITEM = registerPaleOakSmokerItem(SmokerType.DEEPSLATE);

    @Shadow
    private static void registerSmokerItem(BlockItem smoker, Item smokerAfter) {}

    @Unique
    private static BlockItem registerPaleOakSmokerItem(SmokerType smokerType) {
        BlockItem smokerItem = new BlockItem(MoreVariantHolder.getBlock(smokerType, WOOD_TYPE), new Item.Properties());
        registerSmokerItem(smokerItem, Items.SMOKER);
        MoreVariantHolder.setItem(smokerType, WOOD_TYPE, smokerItem);
        return smokerItem;
    }
}
