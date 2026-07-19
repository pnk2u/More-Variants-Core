package de.pnku.more_variants_core.mixin.mstv.more_weapon_variants;

import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder.BiomeSpawnItemVariant;
import de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil;
import net.minecraft.world.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static de.pnku.more_variants_core.util.MoreVariantHolder.MoreVariantType.STICK;
import static de.pnku.more_variants_core.util.MoreVariantHolder.WeaponType.BOW;
import static de.pnku.more_variants_core.util.MoreVariantHolder.WeaponType.IRON_SWORD;
import static de.pnku.more_variants_core.util.MoreVariantHolder.getItem;
import static de.pnku.mstv_mweaponv.util.BiomeSpawnItemUtil.*;

@Mixin(BiomeSpawnItemUtil.class)
public abstract class BiomeSpawnItemUtilMixin {

    @Inject(method = "initMaps", at = @At(value = "FIELD", target = "Lde/pnku/mstv_mweaponv/util/BiomeSpawnItemUtil;initialized:Z", shift = At.Shift.BEFORE, opcode = Opcodes.PUTSTATIC), remap = false)
    private static void initMaps(CallbackInfo ci) {
        for (MoreVariantWoodType woodType : MoreVariantWoodTypeHolder.getMoreVariantWoodTypes()) {
            Item bowItem = getItem(BOW, woodType);
            Item swordItem = getItem(IRON_SWORD, woodType);
            Item stickItem = getItem(STICK, woodType);
            addBowVariant(bowItem, stickItem);
            addSwordVariant(swordItem, stickItem);
        }
        for (BiomeSpawnItemVariant variant : MoreVariantWoodTypeHolder.getBiomeSpawnItemVariants()) {
            Item mainStickItem = getItem(STICK, variant.mainWoodType());
            Item altStickItem = variant.altWoodType() != null ? getItem(STICK, variant.altWoodType()) : null;
            addWoodVariant(variant.biomeIds(), mainStickItem, variant.mainProb(), altStickItem, variant.altProb());
        }
    }
}
