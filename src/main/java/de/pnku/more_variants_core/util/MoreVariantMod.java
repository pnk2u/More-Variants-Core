package de.pnku.more_variants_core.util;

public enum MoreVariantMod {
    ANIMAL_FEEDING_TROUGHS("more_composter_variants.more_feeding_trough_variants", "lolmcmv-aft"),
    BARRELS("more_barrel_variants"),
    BEDS("more_bed_variants","quad-lolmbdv"),
    BEEHIVES("more_beehive_variants","quad-lolmbhv"),
    BOOKSHELVES("more_bookshelf_variants", "lolmbv"),
    CAMPFIRES("nemos_campfires", "nemos-campfires"),
    CARTOGRAPHY_TABLES("more_cartography_tables", "lolmcgt"),
    CHESTS("more_chest_variants", "lolmcv"),
    CHISELED_BOOKSHELVES("more_chiseled_bookshelf_variants", "lolmcbv"),
    COMPOSTERS("more_composter_variants", "lolmcmv"),
    CRAFTERS("more_crafter_variants", "quad-lolmcrv"),
    CRAFTING_TABLES("more_crafting_tables", "lolmct"),
    FLETCHING_TABLES("more_fletching_tables", "lolmft"),
    GRINDSTONES("more_grindstone_variants", "lolmgv"),
    JUKEBOX_NOTEBLOCKS("more_jukebox_noteblock_variants", "quad-lolmjnv"),
    LECTERNS("more_lectern_variants", "lolmlv"),
    LOOMS("more_loom_variants", "lolmlmv"),
    SHIELDS("more_shield_variants", "lolmsv"),
    SMITHING_TABLES("more_smithing_tables", "lolmst"),
    SMOKERS("more_smoker_variants", "quad-lolmsmv"),
    WOODCUTTERS("more_nemos_woodcutter_variants"),
    // MStV(+)
    STICKS("mstv.base", "mstv-base"),
    ARMOR_STANDS("mstv.more_armor_stand_variants", "mstv-masv"),
    FISHING_RODS("mstv.more_fishing_rod_variants", "mstv-mfrv"),
    FRAMES("mstv.more_frame_variants", "mstv-mframev"),
    RAILS("mstv.more_rail_variants", "quad-mstv-mrailv"),
    TOOLS("mstv.more_tool_variants", "mstv-mtoolv"),
    TORCHES("mstv.more_torch_variants", "quad-mstv-mtv"),
    WEAPONS("mstv.more_weapon_variants", "mstv-mweaponv"),
    LADDERS("mstv.nemos_more_ladder_variants", "nemos-moreladdervariants");

    private final String mixinSubPackage;
    private final String modId;

    MoreVariantMod(String mixinSubPackage, String modId) {
        this.mixinSubPackage = mixinSubPackage;
        this.modId = modId;
    }

    MoreVariantMod(String id) {
        this.mixinSubPackage = id;
        this.modId = id;
    }

    public String mixinSubPackage() {
        return mixinSubPackage;
    }
    public String modId() {
        return modId;
    }
}
