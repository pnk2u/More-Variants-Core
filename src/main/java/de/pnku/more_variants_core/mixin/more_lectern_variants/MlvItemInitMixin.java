package de.pnku.more_variants_core.mixin.more_lectern_variants;

import de.pnku.mlv.init.MlvItemInit;
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

@Mixin(MlvItemInit.class)
public abstract class MlvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem lectern, Item lecternAfter) {}

    @Unique
    private static void registerLecternItemVariants(List<WoodType> woodTypes) {
        for (WoodType woodType : woodTypes) {
            BlockItem lecternItem = new BlockItem(MoreVariantHolder.getBlock(VariantType.LECTERN, woodType), new Item.Properties());
            registerItem(lecternItem, Items.LECTERN);
            MoreVariantHolder.setItem(VariantType.LECTERN, woodType, lecternItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterItemsAtTail(CallbackInfo ci) {
        registerLecternItemVariants(WoodTypeHolder.getWoodTypes());
    }
}
