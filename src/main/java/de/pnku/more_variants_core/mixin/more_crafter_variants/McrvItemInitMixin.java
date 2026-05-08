package de.pnku.more_variants_core.mixin.more_crafter_variants;

import de.pnku.mcrv.init.McrvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McrvItemInit.class)
public abstract class McrvItemInitMixin {
    @Shadow
    private static void registerCrafterItem(BlockItem crafter) {}

    @Unique
    private static void registerCrafterItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType crafterType = MoreVariantType.CRAFTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            BlockItem crafterItem = new BlockItem(MoreVariantHolder.getBlock(crafterType, woodType), new Item.Properties());
            registerCrafterItem(crafterItem);
            MoreVariantHolder.setItem(crafterType, woodType, crafterItem);
        }
    }

        registerCrafterItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    @Inject(method = "registerCrafterItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterCrafterItemsAtHead(CallbackInfo ci) {
    }
}
