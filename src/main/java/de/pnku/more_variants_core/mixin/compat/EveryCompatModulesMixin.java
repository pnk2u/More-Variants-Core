package de.pnku.more_variants_core.mixin.compat;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import de.pnku.more_barrel_variants.compat.ec.MoreBarrelEveryCompat;
import de.pnku.more_variants_core.util.MoreVariantWoodType;
import de.pnku.more_variants_core.util.MoreVariantWoodTypeHolder;
import net.mehvahdjukaar.every_compat.modules.lieonlion.MoreChestVariantsModule;
import net.mehvahdjukaar.every_compat.modules.lieonlion.MoreCraftingTablesModule;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;

import java.util.List;

@Pseudo
@Mixin(value = {MoreBarrelEveryCompat.MoreBarrelECModule.class, MoreChestVariantsModule.class, MoreCraftingTablesModule.class},
        targets = {
//                "de.pnku.more_bed_variants.compat.ec.MoreBedEveryCompat.MoreBedECModule.class",
//                "de.pnku.more_beehive_variants.compat.ec.MoreBeehiveEveryCompat.MoreBeehiveECModule.class",
//                "de.pnku.more_cartography_tables.compat.ec.MoreCartographyTableEveryCompat.MoreCartographyTableECModule.class",
//                "de.pnku.more_chiseled_bookshelf_variants.compat.ec.MoreChiseledBookshelfEveryCompat.MoreChiseledBookshelfECModule.class",
//                "de.pnku.more_composter_variants.compat.ec.MoreComposterEveryCompat.MoreComposterECModule.class",
//                "de.pnku.more_crafter_variants.compat.ec.MoreCrafterEveryCompat.MoreCrafterECModule.class",
//                "de.pnku.more_fletching_tables.compat.ec.MoreFletchingTableEveryCompat.MoreFletchingTableECModule.class",
//                "de.pnku.more_grindstone_variants.compat.ec.MoreGrindstoneEveryCompat.MoreGrindstoneECModule.class",
//                "de.pnku.more_jukebox_noteblock_variants.compat.ec.MoreJukeboxNoteblockEveryCompat.MoreJukeboxNoteblockECModule.class",
//                "de.pnku.more_lectern_variants.compat.ec.MoreLecternEveryCompat.MoreLecternECModule.class",
//                "de.pnku.more_loom_variants.compat.ec.MoreLoomEveryCompat.MoreLoomECModule.class",
//                "de.pnku.more_smithing_tables.compat.ec.MoreSmithingTableEveryCompat.MoreSmithingTableECModule.class",
//                "de.pnku.more_smoker_variants.compat.ec.MoreSmokerEveryCompat.MoreSmokerECModule.class"
        }, remap = false)
public abstract class EveryCompatModulesMixin extends SimpleModuleMixin {
    @Override
    public boolean wrappedIsEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry, Operation<Boolean> original) {
        List<MoreVariantWoodType> woodTypes = MoreVariantWoodTypeHolder.getMoreVariantWoodTypes();
        for (MoreVariantWoodType woodType : woodTypes) {
            if (blockId.getPath().contains(woodType.getName())) {
                return true;
            }
        }
        return original.call(entrySetId, blockId, blockType, registry);
    }
}
