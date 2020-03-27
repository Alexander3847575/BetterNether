package paulevs.betternether.biomes;

import java.util.Random;

import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.BiomeParticleConfig;
import paulevs.betternether.BlocksHelper;
import paulevs.betternether.registers.BlocksRegister;
import paulevs.betternether.registers.SoundsRegister;
import paulevs.betternether.structures.StructureType;
import paulevs.betternether.structures.plants.StructureGrayMold;
import paulevs.betternether.structures.plants.StructureMedBrownMushroom;
import paulevs.betternether.structures.plants.StructureMedRedMushroom;
import paulevs.betternether.structures.plants.StructureOldBrownMushrooms;
import paulevs.betternether.structures.plants.StructureOldRedMushrooms;
import paulevs.betternether.structures.plants.StructureRedMold;
import paulevs.betternether.structures.plants.StructureVanillaMushroom;
import paulevs.betternether.structures.plants.StructureWallBrownMushroom;
import paulevs.betternether.structures.plants.StructureWallRedMushroom;

public class OldFungiwoods extends NetherBiome
{
	public OldFungiwoods(String name)
	{
		super(new BiomeDefenition(name)
				.setColor(166, 38, 95)
				.setLoop(SoundsRegister.AMBIENT_MUSHROOM_FOREST)
				.setAdditions(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS)
				.setParticleConfig(new BiomeParticleConfig(
						ParticleTypes.MYCELIUM,
						0.1F,
						(random) -> { return 0.0; },
						(random) -> { return 0.0; },
						(random) -> { return 0.0; })));
		this.setNoiseDensity(0.5F);
		addStructure("old_red_mushrooms", new StructureOldRedMushrooms(), StructureType.FLOOR, 0.1F, false);
		addStructure("old_brown_mushrooms", new StructureOldBrownMushrooms(), StructureType.FLOOR, 0.1F, false);
		addStructure("large_red_mushroom", new StructureMedRedMushroom(), StructureType.FLOOR, 0.12F, true);
		addStructure("large_brown_mushroom", new StructureMedBrownMushroom(), StructureType.FLOOR, 0.12F, true);
		addStructure("vanilla_mushrooms", new StructureVanillaMushroom(), StructureType.FLOOR, 0.5F, false);
		addStructure("red_mold", new StructureRedMold(), StructureType.FLOOR, 0.9F, true);
		addStructure("gray_mold", new StructureGrayMold(), StructureType.FLOOR, 0.9F, true);
		addStructure("wall_red_mushroom", new StructureWallRedMushroom(), StructureType.WALL, 0.9F, true);
		addStructure("wall_brown_mushroom", new StructureWallBrownMushroom(), StructureType.WALL, 0.9F, true);
	}

	@Override
	public void genSurfColumn(IWorld world, BlockPos pos, Random random)
	{
		BlocksHelper.setWithoutUpdate(world, pos, BlocksRegister.NETHER_MYCELIUM.getDefaultState());
	}
}