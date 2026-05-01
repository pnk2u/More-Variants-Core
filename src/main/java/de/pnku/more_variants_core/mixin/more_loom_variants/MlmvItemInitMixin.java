package de.pnku.more_variants_core.mixin.more_loom_variants;

import de.pnku.mlmv.init.MlmvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MlmvItemInit.class)
public abstract class MlmvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem loom, Item loomAfter) {}

    @Unique
    private static void registerLoomItemVariants(List<WoodType> woodTypes) {
        VariantType loomType = VariantType.LOOM;
        for (WoodType woodType : woodTypes) {
            BlockItem loomItem = new BlockItem(MoreVariantHolder.getBlock(loomType, woodType), new Item.Properties());
            registerItem(loomItem, loomType.getVanillaItem());
            MoreVariantHolder.setItem(loomType, woodType, loomItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterItemsAtTail(CallbackInfo ci) {
        registerLoomItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
