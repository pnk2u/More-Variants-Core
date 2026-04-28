package de.pnku.more_variants_pale_oak_backport.mixin.more_chest_variants;

import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.VariantType;
import de.pnku.more_variants_pale_oak_backport.util.PaleOakVariantHolder.ChestType;
import de.pnku.more_variants_pale_oak_backport.util.WoodType;
import io.github.lieonlion.mcv.init.McvItemInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(McvItemInit.class)
public abstract class McvItemInitMixin {
    @Unique
    private static final WoodType WOOD_TYPE = WoodType.PALE_OAK;

    @Unique
    private static final BlockItem PALE_OAK_CHEST_ITEM = createPaleOakChestItem(ChestType.CHEST);
    @Unique
    private static final BlockItem PALE_OAK_TRAPPED_CHEST_ITEM = createPaleOakChestItem(ChestType.TRAPPED_CHEST);

    @Shadow
    private static void registerItem(BlockItem chest, BlockItem trappedChest, Item chestAfter, Item trappedChestAfter) {}

    @Inject(method = "registerItems", at = @At("HEAD"), remap = false)
    private static void injectedRegisterItemsAtHead(CallbackInfo ci) {
        registerItem(PALE_OAK_CHEST_ITEM, PALE_OAK_TRAPPED_CHEST_ITEM, Items.CHEST, Items.TRAPPED_CHEST);
    }

    @Unique
    private static BlockItem createPaleOakChestItem(ChestType chestType) {
        BlockItem chestItem = new BlockItem(PaleOakVariantHolder.getBlock(chestType, WOOD_TYPE), new Item.Properties());
        PaleOakVariantHolder.setItem(chestType, WOOD_TYPE, chestItem);
        return chestItem;
    }
}
