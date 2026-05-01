package de.pnku.more_variants_core.mixin.more_composter_variants;

import de.pnku.mcmv.init.McmvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
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

@Mixin(McmvItemInit.class)
public abstract class McmvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem composter, Item composterAfter) {}

    @Unique
    private static void registerComposterItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantHolder.MoreVariantType composterType = MoreVariantHolder.MoreVariantType.COMPOSTER;
        for (MoreVariantWoodType woodType : woodTypes) {
            BlockItem composterItem = new BlockItem(MoreVariantHolder.getBlock(composterType, woodType), new Item.Properties());
            registerItem(composterItem, composterType.getVanillaItem());
            MoreVariantHolder.setItem(composterType, woodType, composterItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtTail(CallbackInfo ci) {
        registerComposterItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
