package de.pnku.more_variants_core.mixin.more_bookshelf_variants;

import de.pnku.more_variants_core.util.MoreVariantHolder;
import de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import io.github.lieonlion.lolmbv.init.MbvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MbvItemInit.class)
public abstract class MbvItemInitMixin {
    @Shadow
    public static void registerItem(String name, Item item, Item after) {}

    @Unique
    private static void registerBookshelfItemVariants(List<MoreVariantWoodType> woodTypes) {
        MoreVariantType bookshelfType = MoreVariantType.BOOKSHELF;
        for (MoreVariantWoodType woodType : woodTypes) {
            Item bookshelfItem = new BlockItem(MoreVariantHolder.getBlock(bookshelfType, woodType), new Item.Properties());
            registerItem(woodType.getName() + "_bookshelf", bookshelfItem, bookshelfType.getVanillaItem());
            MoreVariantHolder.setItem(bookshelfType, woodType, bookshelfItem);
        }
    }

    @Inject(method = "registerItems", at = @At("TAIL"), remap = false)
    private static void injectedRegisterItemsAtTail(CallbackInfo ci) {
        registerBookshelfItemVariants(MoreVariantWoodTypeHolder.getWoodTypes());
    }
}
