package de.pnku.more_variants_core.mixin.more_composter_variants;

import de.pnku.mcmv.init.McmvItemInit;
import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.WoodType;
import de.pnku.more_variants_core.util.MoreVariantHolder.VariantType;
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

@Mixin(McmvItemInit.class)
public abstract class McmvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem composter, Item composterAfter) {}

    @Unique
    private static void registerComposterItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            BlockItem composterItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.COMPOSTER, woodType), new Item.Properties());
            registerItem(composterItem, Items.COMPOSTER);
            MoreVariantHolder.setItem(VariantType.COMPOSTER, woodType, composterItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterCrafterItemsAtTail(CallbackInfo ci) {
        registerComposterItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
