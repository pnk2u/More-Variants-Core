package de.pnku.more_variants_core.mixin.more_grindstone_variants;

import de.pnku.mgv.init.MgvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.GrindstoneType;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.WoodTypeHolder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MgvItemInit.class)
public abstract class MgvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem grindstone, Item grindstoneAfter) {}

    @Unique
    private static void registerGrindstoneItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            for (GrindstoneType grindstoneType : GrindstoneType.values()) {
                BlockItem blockItem = new BlockItem(MoreVariantHolder.getBlock(grindstoneType, woodType), new Item.Properties());
                registerItem(blockItem, Items.GRINDSTONE);
                MoreVariantHolder.setItem(grindstoneType, woodType, blockItem);
            }
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterGrindstoneItemsAtTail(CallbackInfo ci) {
        registerGrindstoneItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
