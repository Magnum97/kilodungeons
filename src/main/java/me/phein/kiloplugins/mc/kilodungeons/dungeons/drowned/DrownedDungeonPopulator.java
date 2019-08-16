package me.phein.kiloplugins.mc.kilodungeons.dungeons.drowned;

import me.phein.kiloplugins.mc.kilodungeons.config.v0_1a.Config;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.logging.Logger;

public class DrownedDungeonPopulator extends BlockPopulator {

    private DrownedDungeonGenerator dungeonGenerator;
    private Logger logger;
    private Config config;

    public DrownedDungeonPopulator(World world, Logger logger, Config config) {
        this.dungeonGenerator = new DrownedDungeonGenerator(world, config.getDrownedDungeonTreasureChance());
        this.logger = logger;
        this.config = config;
    }

    @Override
    public void populate(@NotNull World world, @NotNull Random random, @NotNull Chunk source) {
        if (random.nextDouble() >= config.getDrownedDungeonChance()) {
            return;
        }

        int relX = random.nextInt(16);
        int relZ = random.nextInt(16);

        int absX = source.getX() * 16 + relX;
        int absZ = source.getZ() * 16 + relZ;

        switch (world.getBiome(absX, absZ)) {
            default:
                return;
            case OCEAN:
            case COLD_OCEAN:
            case DEEP_COLD_OCEAN:
            case DEEP_OCEAN:
            case DEEP_FROZEN_OCEAN:
            case DEEP_LUKEWARM_OCEAN:
            case DEEP_WARM_OCEAN:
            case FROZEN_OCEAN:
            case LUKEWARM_OCEAN:
            case WARM_OCEAN:
        }

        int absY = world.getHighestBlockYAt(absX, absZ) - 10; // minimal depth to spawn

        while (world.getBlockAt(absX, absY, absZ).getType() == Material.WATER ||
                (world.getBlockAt(absX, absY, absZ).getBlockData() instanceof Waterlogged
                        && ((Waterlogged) world.getBlockAt(absX, absY, absZ).getBlockData()).isWaterlogged()) ||
                world.getBlockAt(absX, absY, absZ).getType() == Material.KELP ||
                world.getBlockAt(absX, absY, absZ).getType() == Material.KELP_PLANT) {
            absY--;
            if (absY <= 4) return;
        }

        logger.info("Drowned Dungeon spawning at: (" + absX + ", " + absY + ", " + absZ + ")...");
        dungeonGenerator.generate(absX, absY, absZ);
        logger.info("Drowned Dungeon spawned at: (" + absX + ", " + absY + ", " + absZ + ")");
    }
}
