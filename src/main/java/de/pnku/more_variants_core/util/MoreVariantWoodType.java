package de.pnku.more_variants_core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class MoreVariantWoodType {
    private final String namespace;
    private final String name;
    private final String idString;
    private final MapColor mapColor;
    private final ResourceLocation planksBlockId;

    public MoreVariantWoodType(String name, MapColor mapColor, ResourceLocation planksBlockId) {
        this("minecraft", name, mapColor, planksBlockId);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, ResourceLocation planksBlockId) {
        this.namespace = namespace;
        this.name = name;
        this.idString = namespace + ":" + name;
        this.mapColor = mapColor;
        this.planksBlockId = planksBlockId;
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

    public ResourceLocation getPlanksBlockId() {
        return planksBlockId;
    }

    public Block getPlanksBlock() {
        return BuiltInRegistries.BLOCK.get(planksBlockId);
    }
}

