package wraith.silkspawners;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class SpawnerRename {
    public static final SpawnerRenameListener LISTENER = new SpawnerRenameListener();

    public static void rename(ItemStack stack) {
        NbtCompound tag = stack.getTag();
        if (tag == null) {
            return;
        }
        String[] entityParts = tag.getCompound("BlockEntityTag").getCompound("SpawnData").getString("id").split(":")[1].split("_");
        for (int i = 0; i < entityParts.length; ++i) {
            entityParts[i] = entityParts[i].substring(0, 1).toUpperCase() + entityParts[i].substring(1);
        }
        String entityName = String.join(" ", entityParts);
        stack.setCustomName(new LiteralText(entityName + " Spawner").formatted(Formatting.YELLOW));
    }

}
