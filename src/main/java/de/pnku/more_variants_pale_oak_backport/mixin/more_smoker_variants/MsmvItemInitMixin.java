package de.pnku.more_variants_pale_oak_backport.mixin.more_smoker_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.SmokerType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
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
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

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
        BlockItem smokerItem = new BlockItem(PaleOakVariantHolder.getBlock(VariantType.SMOKER, WOOD_TYPE, smokerType), new Item.Properties());
        registerSmokerItem(smokerItem, Items.SMOKER);
        PaleOakVariantHolder.setItem(VariantType.SMOKER, WOOD_TYPE, smokerType, smokerItem);
        return smokerItem;
    }
}
