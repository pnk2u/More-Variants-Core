package de.pnku.more_variants_core.mixin.more_beehive_variants;

import de.pnku.mbhv.init.MbhvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
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

@Mixin(MbhvItemInit.class)
public abstract class MbhvItemInitMixin {
    @Shadow
    private static void registerBeehiveItem(BlockItem beehiveItem, Item beehiveAfter) {}

    @Unique
    private static void registerBeehiveItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            BlockItem beehiveItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.BEEHIVE, woodType), new Item.Properties());
            registerBeehiveItem(beehiveItem, Items.BEEHIVE);
            MoreVariantHolder.setItem(VariantType.BEEHIVE, woodType, beehiveItem);
        }
    }

    @Inject(method = "registerBeehiveItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterBeehiveItemsAtTail(CallbackInfo ci) {
        registerBeehiveItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
