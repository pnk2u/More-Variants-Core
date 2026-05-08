package de.pnku.more_variants_core.mixin.more_grindstone_variants;

import de.pnku.mgv.init.MgvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.GrindstoneType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static de.pnku.more_variants_core.util.MoreVariantRegistryHelper.whenBlockRegistered;

@Mixin(MgvItemInit.class)
public abstract class MgvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem grindstone, Item grindstoneAfter) {}

    @Unique
    private static void registerGrindstoneItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (GrindstoneType grindstoneType : GrindstoneType.values()) {
                ResourceLocation grindstoneBlockId = MoreVariantHolder.getRegistrationId(grindstoneType, woodType);
                whenBlockRegistered(grindstoneBlockId, block -> {
                    BlockItem grindstoneItem = new BlockItem(MoreVariantHolder.getBlock(grindstoneType, woodType), new Item.Properties());
                    registerItem(grindstoneItem, grindstoneType.getVanillaItem());
                    MoreVariantHolder.setItem(grindstoneType, woodType, grindstoneItem);
                });
            }
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterGrindstoneItemsAtTail(CallbackInfo ci) {
        registerGrindstoneItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
