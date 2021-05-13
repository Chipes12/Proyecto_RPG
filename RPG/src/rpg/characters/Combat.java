package rpg.characters;
import java.util.List;

import rpg.specialities.Skill;

public interface Combat {
	
	public abstract int attack(Skill skill, List<Entity> ptargets);
	public abstract boolean defend(Skill skill);
	public abstract int heal(Skill skill);

}
