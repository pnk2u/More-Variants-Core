package de.pnku.more_variants_core.mixin.more_chest_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.ChestType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import io.github.lieonlion.mcv.init.McvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(McvItemInit.class)
public abstract class McvItemInitMixin {
    @Shadow
    private static void registerItem(BlockItem chest, BlockItem trappedChest, Item chestAfter, Item trappedChestAfter) {}

    @Unique
    private static void registerChestItemVariants(List<MoreVariantWoodType> woodTypes) {
        for (MoreVariantWoodType woodType : woodTypes) {
            registerItem(createChestItem(ChestType.CHEST, woodType), createChestItem(ChestType.TRAPPED_CHEST, woodType),
                    ChestType.CHEST.getVanillaItem(), ChestType.TRAPPED_CHEST.getVanillaItem());
        }
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        registerChestItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }

    @Unique
    private static BlockItem createChestItem(ChestType chestType, MoreVariantWoodType woodType) {
        BlockItem chestItem = new BlockItem(MoreVariantHolder.getBlock(chestType, woodType), new Item.Properties());
        MoreVariantHolder.setItem(chestType, woodType, chestItem);
        return chestItem;
    }
}
