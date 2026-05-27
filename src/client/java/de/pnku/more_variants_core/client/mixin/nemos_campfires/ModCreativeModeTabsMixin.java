package de.pnku.more_variants_core.client.mixin.nemos_campfires;

import com.nemonotfound.nemoscampfires.item.ModCreativeModeTabs;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.CampfireType;
import de.pnku.more_variants_core.util.MoreVariantMod;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ModCreativeModeTabs.class)
public abstract class ModCreativeModeTabsMixin {
    @Unique
    private static void registerCampfireVariantsToCreativeModeTab(List<MoreVariantWoodType> woodTypes, ResourceKey<CreativeModeTab> creativeModeTabKey) {
        ItemGroupEvents.modifyEntriesEvent(creativeModeTabKey).register(content -> {
            for (MoreVariantWoodType woodType : woodTypes) {
                for (CampfireType campfireType : CampfireType.values()) {
                    content.addAfter(Items.SOUL_CAMPFIRE, MoreVariantHolder.getItem(campfireType, woodType));
                }
            }
        });
    }

    @Inject(method = "registerNemosCampfiresCreativeModeTab", at = @At("TAIL"), remap = false)
    private static void injectedRegisterNemosCampfiresCreativeModeTabAtTail(CallbackInfo ci) {
        registerCampfireVariantsToCreativeModeTab(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes(), BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(BuiltInRegistries.CREATIVE_MODE_TAB.get(new ResourceLocation(MoreVariantMod.CAMPFIRES.modId(), MoreVariantMod.CAMPFIRES.modId()))).orElse(CreativeModeTabs.FUNCTIONAL_BLOCKS));
    }

    @Inject(method = "modifyFunctionalBlocksCreativeModeTab", at = @At("HEAD"), remap = false)
    private static void injectedModifyFunctionalBlocksCreativeModeTabAtHead(CallbackInfo ci) {
        registerCampfireVariantsToCreativeModeTab(MoreVariantWoodTypeHolder.getMoreVariantWoodTypes(), CreativeModeTabs.FUNCTIONAL_BLOCKS);
    }
}
