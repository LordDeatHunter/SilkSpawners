package wraith.silkspawners;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class SilkSpawners implements ModInitializer {

    @Override
    public void onInitialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if ((new Identifier("blocks/spawner")).equals(id)) {
                FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .withEntry(ItemEntry.builder(Items.SPAWNER).build())
                    .withFunction(CopyNbtLootFunction.builder(ContextLootNbtProvider.BLOCK_ENTITY)
                        .withOperation("SpawnData", "BlockEntityTag.SpawnData")
                        .withOperation("SpawnPotentials", "BlockEntityTag.SpawnPotentials").build()
                    )
                    .withCondition(MatchToolLootCondition.builder(
                        ItemPredicate.Builder.create()
                            .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1)))).build());
                supplier.withPool(builder.build());
            }
        });
    }

}
