package wraith.silkspawners.mixin;

import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.SpawnerBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnerBlock.class)
public class SpawnerBlockMixin {

    @Inject(method = "onStacksDropped", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockWithEntity;onStacksDropped(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER), cancellable = true)
    public void cancelXP(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        if (FabricToolTags.PICKAXES.contains(stack.getItem()) && EnchantmentHelper.get(stack).containsKey(Enchantments.SILK_TOUCH)) {
            ci.cancel();
        }
    }

}
