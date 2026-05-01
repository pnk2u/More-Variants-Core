package de.pnku.more_variants_core.mixin.more_crafter_variants;

import de.pnku.mcrv.init.McrvItemInit;
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

@Mixin(McrvItemInit.class)
public abstract class McrvItemInitMixin {
    @Shadow
    private static void registerCrafterItem(BlockItem crafter) {}

    @Unique
    private static void registerCrafterItemVariants(List<WoodType> woodTypes) {
        VariantType crafterType = VariantType.CRAFTER;
        for (WoodType woodType : woodTypes) {
            BlockItem crafterItem = new BlockItem(MoreVariantHolder.getBlock(crafterType, woodType), new Item.Properties());
            registerCrafterItem(crafterItem);
            MoreVariantHolder.setItem(crafterType, woodType, crafterItem);
        }
    }

    @Inject(method = "registerCrafterItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtTail(CallbackInfo ci) {
        registerCrafterItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
