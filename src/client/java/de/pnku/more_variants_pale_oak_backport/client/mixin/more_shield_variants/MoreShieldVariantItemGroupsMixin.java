package de.pnku.more_variants_pale_oak_backport.client.mixin.more_shield_variants;

import de.pnku.lolmsv.item.MoreShieldVariantItemGroups;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MoreShieldVariantItemGroups.class)
public abstract class MoreShieldVariantItemGroupsMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Inject(method = "registerMoreShieldVariantItemGroups", at = @At("HEAD"), remap = false)
    private static void injectedRegisterMoreShieldVariantItemGroupsAtHead(CallbackInfo ci) {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries -> entries.addAfter(Items.SHIELD, PaleOakVariantHolder.getItem(PaleOakVariantHolder.SHIELD_FAMILY, WOOD_TYPE)));
    }
}
