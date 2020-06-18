package net.cheatcodemedia.flatworldgenerator.generator;

import net.cheatcodemedia.flatworldgenerator.FlatWorldGenerator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Chunk
 * This class is the generator for all flat chunks. All chunks should be identical.
 */
public class Chunk extends ChunkGenerator {
    FlatWorldGenerator instance;
    int max = 32;

    Material surface = Material.GRASS;
    Material fill = Material.DIRT;
    Material bottom = Material.BEDROCK;

    /**
     * Instantiate when a template was provided.
     */
    public Chunk(String template) {
        this.instance = FlatWorldGenerator.getInstance();

        this.max = this.instance.getConfig().getInt("height");
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        // Create the chunk
        ChunkData chunk = createChunkData(world);
        world.setBiome(chunkX, chunkZ, Biome.VOID);

        if (world.getEnvironment() == World.Environment.NETHER) {
            this.fill = Material.NETHERRACK;
            this.surface = Material.NETHERRACK;
        } else if (world.getEnvironment() == World.Environment.THE_END) {
            this.fill = Material.ENDER_STONE;
            this.surface = Material.ENDER_STONE;
        }

        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++) {
                int y = 0;
                while (y <= max) {
                    if (y == 0) {
                        chunk.setBlock(x, y, z, this.bottom);
                    } else if (y == max) {
                        chunk.setBlock(x, y, z, this.surface);
                    } else {
                        chunk.setBlock(x, y, z, this.fill);
                    }
                    y++;
                }
            }


        // Return the chunk.
        return chunk;
    }
}
