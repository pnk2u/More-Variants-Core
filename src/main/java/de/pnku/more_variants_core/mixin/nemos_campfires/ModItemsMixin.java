package de.pnku.more_variants_core.mixin.nemos_campfires;

import com.nemonotfound.nemoscampfires.item.ModItems;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.CampfireType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModItems.class)
public abstract class ModItemsMixin {
    @Shadow
    private static Item registerBlockItem(String path, Block campfireBlock) {
        throw new AssertionError();
    }

    @Unique
    private static void registerCampfireItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            for (CampfireType campfireType : CampfireType.values()) {
                Item campfireItem = registerBlockItem(MoreVariantHolder.getRegistrationId(campfireType, woodType).getPath(), MoreVariantHolder.getBlock(campfireType, woodType));
                MoreVariantHolder.setItem(campfireType, woodType, campfireItem);
            }
        }
    }

    @Inject(method = "registerItems", at = @At(value = "HEAD"), remap = false)
    private static void injectedRegisterCampfireItemsAtHead(CallbackInfo ci) {
        registerCampfireItemVariants(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes());
    }
}
