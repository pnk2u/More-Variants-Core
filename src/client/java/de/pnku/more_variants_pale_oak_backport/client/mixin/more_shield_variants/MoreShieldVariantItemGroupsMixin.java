package de.pnku.more_variants_pale_oak_backport.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItemGroups;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakShieldHolder;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoreShieldVariantItemGroups.class)
public abstract class MoreShieldVariantItemGroupsMixin {
    @Inject(method = "registerMoreShieldVariantItemGroups", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemGroupsAtHead(CallbackInfo ci) {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> entries.addAfter(Items.SHIELD, PaleOakShieldHolder.getItem()));
    }
}
