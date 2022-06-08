package wraith.silkspawners.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "appendTooltip", at = @At("HEAD"))
    public void getName(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtCompound tag = stack.getNbt();
        if (stack.getItem() != Items.SPAWNER || tag == null) {
            return;
        }
        String[] entityParts = tag.getCompound("BlockEntityTag")
            .getCompound("SpawnData")
            .getCompound("entity")
            .getString("id")
            .split(":")[1]
            .split("_");
        for (int i = 0; i < entityParts.length; ++i) {
            entityParts[i] = entityParts[i].substring(0, 1).toUpperCase() + entityParts[i].substring(1);
        }
        tooltip.add(Text.literal("Type: " + String.join(" ", entityParts)));
    }

}
