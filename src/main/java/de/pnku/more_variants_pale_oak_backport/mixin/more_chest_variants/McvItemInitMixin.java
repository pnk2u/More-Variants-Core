package de.pnku.more_variants_pale_oak_backport.mixin.more_chest_variants;

import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakChestHolder;
import de.pnku.more_variants_pale_oak_backport.mixin.holder.PaleOakChestHolder.ChestType;
import io.github.lieonlion.mcv.init.McvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(McvItemInit.class)
public abstract class McvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CHEST_ITEM = registerPaleOakChestItem();
    @Unique
    private static final BlockItem PALE_OAK_TRAPPED_CHEST_ITEM = registerPaleOakTrappedChestItem();

    @Invoker("registerItem")
    @SuppressWarnings("SameParameterValue")
    private static void invokeRegisterItem(BlockItem chestItem, BlockItem trappedChestItem, Item chestAfter, Item trappedChestAfter) {
        throw new AssertionError();
    }

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        invokeRegisterItem(PALE_OAK_CHEST_ITEM, PALE_OAK_TRAPPED_CHEST_ITEM, Items.CHEST, Items.TRAPPED_CHEST);
    }

    @Unique
    private static BlockItem registerPaleOakChestItem() {
        BlockItem chestItem = new BlockItem(PaleOakChestHolder.getBlock(WOOD_TYPE, ChestType.CHEST), new Item.Properties());
        PaleOakChestHolder.setItem(WOOD_TYPE, ChestType.CHEST, chestItem);
        return chestItem;
    }

    @Unique
    private static BlockItem registerPaleOakTrappedChestItem() {
        BlockItem trappedChestItem = new BlockItem(PaleOakChestHolder.getBlock(WOOD_TYPE, ChestType.TRAPPED_CHEST), new Item.Properties());
        PaleOakChestHolder.setItem(WOOD_TYPE, ChestType.TRAPPED_CHEST, trappedChestItem);
        return trappedChestItem;
    }
}
