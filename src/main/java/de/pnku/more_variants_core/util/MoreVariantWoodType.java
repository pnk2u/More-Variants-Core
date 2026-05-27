package de.pnku.more_variants_core.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class MoreVariantWoodType {
    private final ResourceLocation id;
    private final MapColor mapColor;
    private final SoundType soundType;
    private final ResourceLocation planksId;
    private final int intId;

    public MoreVariantWoodType(String name, MapColor mapColor, int intId) {
        this("minecraft", name, mapColor, SoundType.WOOD, new ResourceLocation("minecraft", name + "_planks"), intId);
    }

    public MoreVariantWoodType(String name, MapColor mapColor, SoundType soundType, int intId) {
        this("minecraft", name, mapColor, soundType, new ResourceLocation("minecraft", name + "_planks"), intId);
    }

    public MoreVariantWoodType(String name, MapColor mapColor, ResourceLocation planksId, int intId) {
        this("minecraft", name, mapColor, SoundType.WOOD, planksId, intId);
    }

    public MoreVariantWoodType(String name, MapColor mapColor, SoundType soundType, ResourceLocation planksId, int intId) {
        this("minecraft", name, mapColor, soundType, planksId, intId);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, int intId) {
        this(namespace, name, mapColor, SoundType.WOOD, ResourceLocation.tryBuild(namespace,name + "_planks"), intId);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, ResourceLocation planksId, int intId) {
        this(namespace, name, mapColor, SoundType.WOOD, planksId, intId);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, SoundType soundType, int intId) {
        this(namespace, name, mapColor, soundType, ResourceLocation.tryBuild(namespace,name + "_planks"), intId);
    }

    public MoreVariantWoodType(String namespace, String name, MapColor mapColor, SoundType soundType, ResourceLocation planksId, int intId) {
        this.id = ResourceLocation.tryBuild(namespace, name);
        this.mapColor = mapColor;
        this.soundType = soundType;
        this.planksId = planksId;
        this.intId = intId;
    }

    public ResourceLocation id() {
        return id;
    }

    public String getIdString() {
        return id.toString();
    }

    public String getNamespace() {
        return id.getNamespace();
    }

    public String getName() {
        return id.getPath();
    }

    public MapColor mapColor() {
        return mapColor;
    }

    public SoundType soundType() {
        return soundType;
    }

    public ResourceLocation planksId() {
        return planksId;
    }

    public Block getPlanksBlock() {
        return BuiltInRegistries.BLOCK.get(planksId);
    }

    public Item getPlanksItem() {
        return BuiltInRegistries.ITEM.get(planksId);
    }

    public int getIntId() {
        return intId;
    }
}

