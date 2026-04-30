package de.pnku.more_variants_core.mixin.compat;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SimpleModule.class)
public abstract class SimpleModuleMixin {
    @WrapMethod(method = "isEntryAlreadyRegistered(Ljava/lang/String;Lnet/minecraft/resources/ResourceLocation;Lnet/mehvahdjukaar/moonlight/api/set/BlockType;Lnet/minecraft/core/Registry;)Z")
    protected boolean wrappedIsEntryAlreadyRegistered(String entrySetId, ResourceLocation blockId, BlockType blockType, Registry<?> registry, Operation<Boolean> original) {
        return original.call(entrySetId, blockId, blockType, registry);
    }
}
