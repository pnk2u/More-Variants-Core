package de.pnku.more_variants_core.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItemGroups;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MoreShieldVariantItemGroups.class)
public abstract class MoreShieldVariantItemGroupsMixin {
    @Unique
    private static void registerMoreShieldItemVariantsToItemGroups(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> entries.addAfter(Items.SHIELD, MoreVariantHolder.getItem(MoreVariantType.SHIELD, woodType)));
        }
    }

    @Inject(method = "registerMoreShieldVariantItemGroups", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemGroupsAtHead(CallbackInfo ci) {
        registerMoreShieldItemVariantsToItemGroups(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
