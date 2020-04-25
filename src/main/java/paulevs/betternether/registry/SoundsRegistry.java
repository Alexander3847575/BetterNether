package paulevs.betternether.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import paulevs.betternether.BetterNether;

public class SoundsRegistry
{
	public static final SoundEvent MOB_FIREFLY_FLY = register("betternether.mob.firefly.fly");
	public static final SoundEvent MOB_JELLYFISH = register("betternether.mob.jellyfish");
	public static final SoundEvent MOB_NAGA_IDLE = register("betternether.mob.naga_idle");
	public static final SoundEvent MOB_NAGA_ATTACK = register("betternether.mob.naga_attack");
	
	private static SoundEvent register(String id)
	{
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(new Identifier(BetterNether.MOD_ID, id)));
	}
	
	public static void register() {}
}
