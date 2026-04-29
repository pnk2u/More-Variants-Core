package de.pnku.more_variants_core.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class WoodType {
    private final String namespace;
    private final String name;
    private final String idString;
    private final MapColor mapColor;
    private final Block planksBlock;

    WoodType(String name, MapColor mapColor, Block planksBlock) {
        this("minecraft", name, mapColor, planksBlock);
    }

    WoodType(String namespace, String name, MapColor mapColor, Block planksBlock) {
        this.namespace = namespace;
        this.name = name;
        this.idString = namespace + ":" + name;
        this.mapColor = mapColor;
        this.planksBlock = planksBlock;
    }

    public String idString() {
        return idString;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public Block getPlanksBlock() {
        return planksBlock;
    }
}

