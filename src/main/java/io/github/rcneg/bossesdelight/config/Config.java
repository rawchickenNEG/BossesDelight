package io.github.rcneg.bossesdelight.config;

import io.github.rcneg.bossesdelight.BossesDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = BossesDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    public static ForgeConfigSpec COMMON_CONFIG;
    // COMMON
    public static ForgeConfigSpec.BooleanValue COLLECT_FEAST;
    public static ForgeConfigSpec.BooleanValue OBSIDIAN_ONION_CONVERTING;
    public static ForgeConfigSpec.IntValue GLARE_SODA_LEVEL;
    public static ForgeConfigSpec.IntValue ANIMA_DRINK_LEVEL;

    public static List<Block> CRYSTAL_FRUIT_PLANTABLE_BLOCKS, OBSIDIAN_ONION_PLANTABLE_BLOCKS;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> CRYSTAL_FRUIT_WHITELIST_BLOCKS;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> OBSIDIAN_ONION_WHITELIST_BLOCKS;


    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.push("Gauntlet Item");
        COLLECT_FEAST = COMMON_BUILDER.comment("If it's true, the Gauntlet Item will able to collect Feast Block even consumed.")
                .comment("Incontrovertibly it's a BUG! If you make this TRUE, it's no different from cheating.")
                .comment("I made this BUG to another item with that feature and make it using more reasonable.")
                .define("ShouldCollectFeastBlock", false);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Crystal Fruits");
        CRYSTAL_FRUIT_WHITELIST_BLOCKS = COMMON_BUILDER
                .comment("Define which blocks allow Crystal Fruits to plant on, leave space will use the default value.")
                .defineListAllowEmpty("CrystalFruitPlantableBlocks",
                        List.of("minecraft:bedrock", "minecraft:budding_amethyst"), Config::validateBlockName);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Glare Soda");
        GLARE_SODA_LEVEL = COMMON_BUILDER.comment("Modify the max effect level of Glare Soda that can be disorganized.")
                .defineInRange("DisorganizeMaxLevel", 5, 1, 255);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Anima Ice Drink");
        ANIMA_DRINK_LEVEL = COMMON_BUILDER.comment("Modify the max effect level of Glare Soda that can be upgraded to.")
                .defineInRange("UpgradeMaxLevel", 5, 1, 255);
        COMMON_BUILDER.pop();
        COMMON_BUILDER.push("Obsidian Onions");
        OBSIDIAN_ONION_WHITELIST_BLOCKS = COMMON_BUILDER
                .comment("Define which blocks allow Obsidian Onions to plant on, leave space will use the default value.")
                .defineListAllowEmpty("ObsidianOnionPlantableBlocks",
                        List.of("minecraft:obsidian", "minecraft:crying_obsidian"), Config::validateBlockName);
        OBSIDIAN_ONION_CONVERTING = COMMON_BUILDER.comment("Define if Obsidian Onions converting planted blocks to Crying Obsidian.")
                .define("ShouldObsidianOnionConverting", true);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    private static boolean validateBlockName(final Object obj)
    {
        return obj instanceof final String name && ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(name));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        CRYSTAL_FRUIT_PLANTABLE_BLOCKS = CRYSTAL_FRUIT_WHITELIST_BLOCKS.get().stream()
                .map(name -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name))).toList();
        OBSIDIAN_ONION_PLANTABLE_BLOCKS = OBSIDIAN_ONION_WHITELIST_BLOCKS.get().stream()
                .map(name -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name))).toList();
    }
}