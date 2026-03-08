package io.github.rcneg.bossesdelight.tags;

import io.github.rcneg.bossesdelight.BossesDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> COLD_SOURCES = modItemTag("cold_sources");
    public static final TagKey<Block> TRAY_COLD_SOURCES = modItemTag("tray_cold_sources");

    private static TagKey<Block> modItemTag(String path) {
        return BlockTags.create(new ResourceLocation(BossesDelight.MODID, path));
    }
}