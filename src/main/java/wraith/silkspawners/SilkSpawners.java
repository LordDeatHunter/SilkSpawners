package wraith.silkspawners;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.SetNameLootFunction;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class SilkSpawners implements ModInitializer {

    @Override
    public void onInitialize() {
        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if ((new Identifier("blocks/spawner")).equals(id)) {
                System.out.println("Injecting into that shit");
                FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .withEntry(ItemEntry.builder(Items.SPAWNER).build())
                        .withFunction(CopyNbtLootFunction.builder(CopyNbtLootFunction.Source.BLOCK_ENTITY)
                                .withOperation("SpawnData", "BlockEntityTag.SpawnData", CopyNbtLootFunction.Operator.REPLACE)
                                .withOperation("SpawnPotentials", "BlockEntityTag.SpawnPotentials", CopyNbtLootFunction.Operator.REPLACE).build()
                        )
                        .withCondition(MatchToolLootCondition.builder(
                                ItemPredicate.Builder.create()
                                        .tag(FabricToolTags.PICKAXES)
                                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1)))).build());
                supplier.withPool(builder.build());
            }
        });
    }

}
