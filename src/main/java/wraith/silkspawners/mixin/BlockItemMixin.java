package wraith.silkspawners.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.item.BlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Redirect(method = "writeNbtToBlockEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BlockEntity;copyItemDataRequiresOperator()Z"))
    private static boolean writeTagToBlockEntity(BlockEntity blockEntity) {
        return blockEntity.copyItemDataRequiresOperator() && !(blockEntity instanceof MobSpawnerBlockEntity);
    }

}
