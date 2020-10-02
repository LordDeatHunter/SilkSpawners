package wraith.silkspawners;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.util.collection.DefaultedList;

public class SpawnerRenameListener implements ScreenHandlerListener {

    @Override
    public void onHandlerRegistered(ScreenHandler handler, DefaultedList<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            if (Items.SPAWNER.equals(stack.getItem())) {
                SpawnerRename.rename(stack);
            }
        }
    }

    @Override
    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
        if (Items.SPAWNER.equals(stack.getItem())) {
            SpawnerRename.rename(stack);
        }
    }

    @Override
    public void onPropertyUpdate(ScreenHandler handler, int property, int value) { }
}
