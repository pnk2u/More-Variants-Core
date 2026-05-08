package de.pnku.more_variants_core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class MoreVariantWoodType {
    private final String namespace;
    private final String name;
    private final String idString;
    private final MapColor mapColor;
    private final ResourceLocation planksId;
    private final int id;

    public MoreVariantWoodType(String name, MapColor mapColor, ResourceLocation planksId, int id) {
        this("minecraft", name, mapColor, planksId, id);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, ResourceLocation planksId, int id) {
        this.namespace = namespace;
        this.name = name;
        this.idString = namespace + ":" + name;
        this.mapColor = mapColor;
        this.planksId = planksId;
        this.id = id;
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

    public ResourceLocation getPlanksId() {
        return planksId;
    }

    public Block getPlanksBlock() {
        return BuiltInRegistries.BLOCK.get(planksId);
    }

    public Item getPlanksItem() {
        return BuiltInRegistries.ITEM.get(planksId);
    }

    public int getIntId() {
        return id;
    }
}

